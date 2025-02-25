/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.dataset;

import java.util.concurrent.ExecutorService;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.spi.CamelLogger;
import org.apache.camel.support.DefaultConsumer;
import org.apache.camel.support.processor.ThroughputLogger;
import org.apache.camel.util.URISupport;

/**
 * DataSet consumer.
 */
public class DataSetConsumer extends DefaultConsumer {
    private final CamelContext camelContext;
    private DataSetEndpoint endpoint;
    private Processor reporter;
    private ExecutorService executorService;
    private final boolean withIndexHeader;

    public DataSetConsumer(DataSetEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        this.camelContext = endpoint.getCamelContext();
        this.withIndexHeader = !endpoint.getDataSetIndex().equals("off");
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();

        if (reporter == null) {
            reporter = createReporter();
        }

        final DataSet dataSet = endpoint.getDataSet();
        final long preloadSize = endpoint.getPreloadSize();

        sendMessages(0, preloadSize);
        executorService = camelContext.getExecutorServiceManager().newSingleThreadExecutor(this, endpoint.getEndpointUri());

        executorService.execute(new Runnable() {
            public void run() {
                if (endpoint.getInitialDelay() > 0) {
                    try {
                        Thread.sleep(endpoint.getInitialDelay());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                sendMessages(preloadSize, dataSet.getSize());
            }
        });
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();

        if (executorService != null) {
            camelContext.getExecutorServiceManager().shutdown(executorService);
            executorService = null;
        }
    }

    /**
     * Creates a message exchange for the given index in the {@link DataSet}
     */
    protected Exchange createExchange(long messageIndex) throws Exception {
        Exchange exchange = createExchange(false);

        endpoint.getDataSet().populateMessage(exchange, messageIndex);

        if (withIndexHeader) {
            Message in = exchange.getIn();
            in.setHeader(DataSetConstants.DATASET_INDEX, messageIndex);
        }

        return exchange;
    }

    protected void sendMessages(long startIndex, long endIndex) {
        for (long i = startIndex; i < endIndex; i++) {
            Exchange exchange = null;
            try {
                exchange = createExchange(i);
                getProcessor().process(exchange);

                try {
                    long delay = endpoint.getProduceDelay();
                    if (delay > 0) {
                        Thread.sleep(delay);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                if (reporter != null) {
                    reporter.process(exchange);
                }
            } catch (Exception e) {
                handleException(e);
            } finally {
                releaseExchange(exchange, false);
            }
        }
    }

    protected ThroughputLogger createReporter() {
        // must sanitize uri to avoid logging sensitive information
        String uri = URISupport.sanitizeUri(endpoint.getEndpointUri());
        CamelLogger logger = new CamelLogger(uri);
        ThroughputLogger answer = new ThroughputLogger(logger, (int) endpoint.getDataSet().getReportCount());
        answer.setAction("Sent");
        return answer;
    }
}
