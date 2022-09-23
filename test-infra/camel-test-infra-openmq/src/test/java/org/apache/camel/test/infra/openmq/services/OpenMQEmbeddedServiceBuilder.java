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

package org.apache.camel.test.infra.openmq.services;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;

import javax.management.ObjectName;

import com.sun.messaging.jmq.jmsclient.runtime.ClientRuntime;
import com.sun.messaging.jmq.jmsclient.runtime.BrokerInstance;

import org.apache.activemq.Service;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerContext;
import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.SslContext;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.broker.jmx.BrokerView;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.apache.activemq.broker.region.DestinationFactory;
import org.apache.activemq.broker.region.DestinationInterceptor;
import org.apache.activemq.broker.region.policy.PolicyMap;
import org.apache.activemq.broker.scheduler.JobSchedulerStore;
import org.apache.activemq.command.OpenMQDestination;
import org.apache.activemq.network.jms.JmsConnector;
import org.apache.activemq.security.MessageAuthorizationPolicy;
import org.apache.activemq.store.PListStore;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.store.PersistenceAdapterFactory;
import org.apache.activemq.thread.TaskRunnerFactory;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.util.IOExceptionHandler;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * This is a builder class for the embedded OpenMQ BrokerService. Since it is tightly integrated into the tests of
 * some components, we need to have flexibility setting it up. Therefore, in most cases for tests that rely on purely
 * embedded OpenMQ, they can use this to wrap the broker service into the test-infra compatible service that can be
 * managed by Junit 5.
 */
public final class OpenMQEmbeddedServiceBuilder {
    private static final Logger LOG = LoggerFactory.getLogger(OpenMQEmbeddedServiceBuilder.class);

    private static final LongAdder BROKER_COUNT = new LongAdder();
    private final BrokerInstance brokerInstance;

    private OpenMQEmbeddedServiceBuilder() throws ReflectiveOperationException {
        brokerInstance = ClientRuntime.getRuntime().createBrokerInstance();
    }

    public OpenMQEmbeddedServiceBuilder withAdminView(BrokerView adminView) {
        brokerService.setAdminView(adminView);

        return this;
    }

    public OpenMQEmbeddedServiceBuilder withBrokerName(Class<?> testClass) {
        return withBrokerName(testClass.getSimpleName());
    }

    public OpenMQEmbeddedServiceBuilder withBrokerName(String brokerName) {
        brokerService.setBrokerName(brokerName);

        return this;
    }

    public OpenMQEmbeddedServiceBuilder withBrokerName(Class<?> testClass, String name) {
        return withBrokerName(testClass.getSimpleName(), name);
    }

    public OpenMQEmbeddedServiceBuilder withBrokerName(String brokerName, String name) {
        brokerService.setBrokerName(brokerName + (name != null ? "-" + name : ""));

        return this;
    }

    public OpenMQEmbeddedServiceBuilder withDataDirectory(String dataDirectory) {
        brokerService.setDataDirectory(dataDirectory);

        return this;
    }

    public OpenMQEmbeddedServiceBuilder withDataDirectoryFile(File dataDirectoryFile) {
        brokerService.setDataDirectoryFile(dataDirectoryFile);

        return this;
    }

    public OpenMQEmbeddedServiceBuilder withTmpDataDirectory(File tmpDataDirectory) {
        brokerService.setTmpDataDirectory(tmpDataDirectory);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withPersistenceFactory(PersistenceAdapterFactory persistenceFactory) {
        brokerService.setPersistenceFactory(persistenceFactory);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withDestinationFactory(DestinationFactory destinationFactory) {
        brokerService.setDestinationFactory(destinationFactory);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withPersistent(boolean persistent) {
        brokerService.setPersistent(persistent);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withPopulateJMSXUserID(boolean populateJMSXUserID) {
        brokerService.setPopulateJMSXUserID(populateJMSXUserID);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withSystemUsage(SystemUsage memoryManager) {
        brokerService.setSystemUsage(memoryManager);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withConsumerSystemUsage(SystemUsage consumerSystemUsage) {
        brokerService.setConsumerSystemUsage(consumerSystemUsage);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withProducerSystemUsage(SystemUsage producerUsageManager) {
        brokerService.setProducerSystemUsage(producerUsageManager);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withPersistenceAdapter(PersistenceAdapter persistenceAdapter) {

        try {
            brokerService.setPersistenceAdapter(persistenceAdapter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public OpenMQEmbeddedServiceBuilder withTaskRunnerFactory(TaskRunnerFactory taskRunnerFactory) {
        brokerService.setTaskRunnerFactory(taskRunnerFactory);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withPersistenceTaskRunnerFactory(TaskRunnerFactory persistenceTaskRunnerFactory) {
        brokerService.setPersistenceTaskRunnerFactory(persistenceTaskRunnerFactory);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withEnableStatistics(boolean enableStatistics) {
        brokerService.setEnableStatistics(enableStatistics);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withUseJmx(boolean useJmx) {
        brokerService.setUseJmx(useJmx);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withBrokerObjectName(ObjectName brokerObjectName) {
        brokerService.setBrokerObjectName(brokerObjectName);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withManagementContext(ManagementContext managementContext) {
        brokerService.setManagementContext(managementContext);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withNetworkConnectorURIs(String[] networkConnectorURIs) {
        brokerService.setNetworkConnectorURIs(networkConnectorURIs);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withTransportConnectorURIs(String[] transportConnectorURIs) {
        brokerService.setTransportConnectorURIs(transportConnectorURIs);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withJmsBridgeConnectors(JmsConnector[] jmsConnectors) {
        brokerService.setJmsBridgeConnectors(jmsConnectors);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withServices(Service[] services) {
        brokerService.setServices(services);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withUseLoggingForShutdownErrors(boolean useLoggingForShutdownErrors) {
        brokerService.setUseLoggingForShutdownErrors(useLoggingForShutdownErrors);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withUseShutdownHook(boolean useShutdownHook) {
        brokerService.setUseShutdownHook(useShutdownHook);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withAdvisorySupport(boolean advisorySupport) {
        brokerService.setAdvisorySupport(advisorySupport);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withAnonymousProducerAdvisorySupport(boolean anonymousProducerAdvisorySupport) {
        brokerService.setAnonymousProducerAdvisorySupport(anonymousProducerAdvisorySupport);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withTransportConnectors(List<TransportConnector> transportConnectors)
            throws Exception {
        brokerService.setTransportConnectors(transportConnectors);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withNetworkConnectors(List<?> networkConnectors) throws Exception {
        brokerService.setNetworkConnectors(networkConnectors);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withProxyConnectors(List<?> proxyConnectors) throws Exception {
        brokerService.setProxyConnectors(proxyConnectors);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withDestinationPolicy(PolicyMap policyMap) {
        brokerService.setDestinationPolicy(policyMap);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withPlugins(BrokerPlugin[] plugins) {
        brokerService.setPlugins(plugins);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withMessageAuthorizationPolicy(
            MessageAuthorizationPolicy messageAuthorizationPolicy) {
        brokerService.setMessageAuthorizationPolicy(messageAuthorizationPolicy);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withDeleteAllMessagesOnStartup(boolean deletePersistentMessagesOnStartup) {
        brokerService.setDeleteAllMessagesOnStartup(deletePersistentMessagesOnStartup);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withVmConnectorURI(URI vmConnectorURI) {
        brokerService.setVmConnectorURI(vmConnectorURI);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withShutdownOnMasterFailure(boolean shutdownOnMasterFailure) {
        brokerService.setShutdownOnMasterFailure(shutdownOnMasterFailure);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withKeepDurableSubsActive(boolean keepDurableSubsActive) {
        brokerService.setKeepDurableSubsActive(keepDurableSubsActive);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withEnableMessageExpirationOnActiveDurableSubs(
            boolean enableMessageExpirationOnActiveDurableSubs) {
        brokerService.setEnableMessageExpirationOnActiveDurableSubs(enableMessageExpirationOnActiveDurableSubs);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withUseVirtualTopics(boolean useVirtualTopics) {
        brokerService.setUseVirtualTopics(useVirtualTopics);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withUseMirroredQueues(boolean useMirroredQueues) {
        brokerService.setUseMirroredQueues(useMirroredQueues);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withDestinationInterceptors(DestinationInterceptor[] destinationInterceptors) {
        brokerService.setDestinationInterceptors(destinationInterceptors);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withDestinations(OpenMQDestination[] destinations) {
        brokerService.setDestinations(destinations);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withTempDataStore(PListStore tempDataStore) {
        brokerService.setTempDataStore(tempDataStore);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withPersistenceThreadPriority(int persistenceThreadPriority) {
        brokerService.setPersistenceThreadPriority(persistenceThreadPriority);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withUseLocalHostBrokerName(boolean useLocalHostBrokerName) {
        brokerService.setUseLocalHostBrokerName(useLocalHostBrokerName);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withProducerSystemUsagePortion(int producerSystemUsagePortion) {
        brokerService.setProducerSystemUsagePortion(producerSystemUsagePortion);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withConsumerSystemUsagePortion(int consumerSystemUsagePortion) {
        brokerService.setConsumerSystemUsagePortion(consumerSystemUsagePortion);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withSplitSystemUsageForProducersConsumers(
            boolean splitSystemUsageForProducersConsumers) {
        brokerService.setSplitSystemUsageForProducersConsumers(splitSystemUsageForProducersConsumers);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withMonitorConnectionSplits(boolean monitorConnectionSplits) {
        brokerService.setMonitorConnectionSplits(monitorConnectionSplits);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withTaskRunnerPriority(int taskRunnerPriority) {
        brokerService.setTaskRunnerPriority(taskRunnerPriority);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withDedicatedTaskRunner(boolean dedicatedTaskRunner) {
        brokerService.setDedicatedTaskRunner(dedicatedTaskRunner);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withCacheTempDestinations(boolean cacheTempDestinations) {
        brokerService.setCacheTempDestinations(cacheTempDestinations);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withTimeBeforePurgeTempDestinations(int timeBeforePurgeTempDestinations) {
        brokerService.setTimeBeforePurgeTempDestinations(timeBeforePurgeTempDestinations);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withUseTempMirroredQueues(boolean useTempMirroredQueues) {
        brokerService.setUseTempMirroredQueues(useTempMirroredQueues);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withJobSchedulerStore(JobSchedulerStore jobSchedulerStore) {
        brokerService.setJobSchedulerStore(jobSchedulerStore);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withShutdownHooks(List<Runnable> hooks) throws Exception {
        brokerService.setShutdownHooks(hooks);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withRegionBroker(Broker regionBroker) {
        brokerService.setRegionBroker(regionBroker);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withSystemExitOnShutdown(boolean systemExitOnShutdown) {
        brokerService.setSystemExitOnShutdown(systemExitOnShutdown);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withSystemExitOnShutdownExitCode(int systemExitOnShutdownExitCode) {
        brokerService.setSystemExitOnShutdownExitCode(systemExitOnShutdownExitCode);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withSslContext(SslContext sslContext) {
        brokerService.setSslContext(sslContext);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withShutdownOnSlaveFailure(boolean shutdownOnSlaveFailure) {
        brokerService.setShutdownOnSlaveFailure(shutdownOnSlaveFailure);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withWaitForSlave(boolean waitForSlave) {
        brokerService.setWaitForSlave(waitForSlave);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withWaitForSlaveTimeout(long waitForSlaveTimeout) {
        brokerService.setWaitForSlaveTimeout(waitForSlaveTimeout);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withPassiveSlave(boolean passiveSlave) {
        brokerService.setPassiveSlave(passiveSlave);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withIoExceptionHandler(IOExceptionHandler ioExceptionHandler) {
        brokerService.setIoExceptionHandler(ioExceptionHandler);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withSchedulerSupport(boolean schedulerSupport) {
        brokerService.setSchedulerSupport(schedulerSupport);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withSchedulerDirectoryFile(File schedulerDirectory) {
        brokerService.setSchedulerDirectoryFile(schedulerDirectory);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withSchedulerDirectory(String schedulerDirectory) {
        brokerService.setSchedulerDirectory(schedulerDirectory);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withSchedulePeriodForDestinationPurge(int schedulePeriodForDestinationPurge) {
        brokerService.setSchedulePeriodForDestinationPurge(schedulePeriodForDestinationPurge);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withSchedulePeriodForDiskUsageCheck(int schedulePeriodForDiskUsageCheck) {
        brokerService.setSchedulePeriodForDiskUsageCheck(schedulePeriodForDiskUsageCheck);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withDiskUsageCheckRegrowThreshold(int diskUsageCheckRegrowThreshold) {
        brokerService.setDiskUsageCheckRegrowThreshold(diskUsageCheckRegrowThreshold);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withMaxPurgedDestinationsPerSweep(int maxPurgedDestinationsPerSweep) {
        brokerService.setMaxPurgedDestinationsPerSweep(maxPurgedDestinationsPerSweep);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withBrokerContext(BrokerContext brokerContext) {
        brokerService.setBrokerContext(brokerContext);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withBrokerId(String brokerId) {
        brokerService.setBrokerId(brokerId);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withUseAuthenticatedPrincipalForJMSXUserID(
            boolean useAuthenticatedPrincipalForJMSXUserID) {
        brokerService.setUseAuthenticatedPrincipalForJMSXUserID(useAuthenticatedPrincipalForJMSXUserID);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withPopulateUserNameInMBeans(boolean value) {
        brokerService.setPopulateUserNameInMBeans(value);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withMbeanInvocationTimeout(long mbeanInvocationTimeout) {
        brokerService.setMbeanInvocationTimeout(mbeanInvocationTimeout);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withNetworkConnectorStartAsync(boolean networkConnectorStartAsync) {
        brokerService.setNetworkConnectorStartAsync(networkConnectorStartAsync);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withAllowTempAutoCreationOnSend(boolean allowTempAutoCreationOnSend) {
        brokerService.setAllowTempAutoCreationOnSend(allowTempAutoCreationOnSend);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withOfflineDurableSubscriberTimeout(long offlineDurableSubscriberTimeout) {
        brokerService.setOfflineDurableSubscriberTimeout(offlineDurableSubscriberTimeout);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withOfflineDurableSubscriberTaskSchedule(
            long offlineDurableSubscriberTaskSchedule) {
        brokerService.setOfflineDurableSubscriberTaskSchedule(offlineDurableSubscriberTaskSchedule);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withStartAsync(boolean startAsync) {
        brokerService.setStartAsync(startAsync);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withRestartAllowed(boolean restartAllowed) {
        brokerService.setRestartAllowed(restartAllowed);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withStoreOpenWireVersion(int storeOpenWireVersion) {
        brokerService.setStoreOpenWireVersion(storeOpenWireVersion);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withRejectDurableConsumers(boolean rejectDurableConsumers) {
        brokerService.setRejectDurableConsumers(rejectDurableConsumers);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withUseVirtualDestSubs(boolean useVirtualDestSubs) {
        brokerService.setUseVirtualDestSubs(useVirtualDestSubs);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withUseVirtualDestSubsOnCreation(boolean useVirtualDestSubsOnCreation) {
        brokerService.setUseVirtualDestSubsOnCreation(useVirtualDestSubsOnCreation);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withAdjustUsageLimits(boolean adjustUsageLimits) {
        brokerService.setAdjustUsageLimits(adjustUsageLimits);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withRollbackOnlyOnAsyncException(boolean rollbackOnlyOnAsyncException) {
        brokerService.setRollbackOnlyOnAsyncException(rollbackOnlyOnAsyncException);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withMaxSchedulerRepeatAllowed(int maxSchedulerRepeatAllowed) {
        brokerService.setMaxSchedulerRepeatAllowed(maxSchedulerRepeatAllowed);
        return this;
    }

    public OpenMQEmbeddedServiceBuilder withCustomSetup(Consumer<BrokerService> consumer) {
        consumer.accept(brokerService);

        return this;
    }

    public OpenMQEmbeddedServiceBuilder withTcpTransport() {
        return withTcpTransport(0);
    }

    public OpenMQEmbeddedServiceBuilder withTcpTransport(int port) {
        return withTransport("tcp://0.0.0.0:", port);
    }

    public OpenMQEmbeddedServiceBuilder withAmqpTransport(int port) {
        return withTransport("amqp://0.0.0.0:", port);
    }

    public OpenMQEmbeddedServiceBuilder withMqttTransport(int port) {
        return withTransport("mqtt://0.0.0.0:", port);
    }

    public OpenMQEmbeddedServiceBuilder withStompTransport(int port, String options) {
        return withTransport("stomp://localhost:", port, options);
    }

    public OpenMQEmbeddedServiceBuilder withTransport(String bindAddress, int port) {
        return withTransport(bindAddress, port, null);
    }

    public OpenMQEmbeddedServiceBuilder withTransport(String bindAddress, int port, String options) {
        try {
            brokerService.addConnector(bindAddress + port + (options == null ? "" : options));
        } catch (Exception e) {
            fail("Unable to add new transport: " + e.getMessage());
        }

        return this;
    }

    BrokerInstance brokerInstance() {
        return brokerInstance;
    }

    public OpenMQEmbeddedService build() {
        return new OpenMQEmbeddedService(brokerService);
    }

    @Deprecated
    public OpenMQEmbeddedService buildWithRecycle() {
        return build();
    }

    public static OpenMQEmbeddedServiceBuilder bare() {
        return new OpenMQEmbeddedServiceBuilder();
    }

    private static String generateDataDirectoryPathForInstance(String name) {
        final String dataDirectoryPath = OpenMQEmbeddedServiceBuilder.class.getResource("/").getFile() + name;

        final File dataDirectory = new File(dataDirectoryPath);
        if (dataDirectory.exists()) {
            try {
                FileUtils.deleteDirectory(dataDirectory);
            } catch (IOException e) {
                LOG.warn(
                        "Could not delete the data directory at {}: {} (the error will be ignored, but the tests are likely to fail)",
                        dataDirectoryPath, e.getMessage());
            }
        }

        return dataDirectoryPath;
    }

    private static String generateSemiUniqueBrokerName() {
        final String semiUniqueName
                = OpenMQEmbeddedServiceBuilder.class.getSimpleName() + "-" + BROKER_COUNT.longValue() + "."
                  + ThreadLocalRandom.current().nextInt(1000);

        BROKER_COUNT.increment();
        return semiUniqueName;
    }

    public static OpenMQEmbeddedServiceBuilder defaultBroker() {
        final String semiUniqueName = generateSemiUniqueBrokerName();

        return defaultBroker(semiUniqueName);
    }

    public static OpenMQEmbeddedServiceBuilder defaultBroker(String name) {
        final String dataDirectoryPath = generateDataDirectoryPathForInstance(name);

        return new OpenMQEmbeddedServiceBuilder()
                .withDeleteAllMessagesOnStartup(true)
                .withBrokerName(name)
                .withAdvisorySupport(false)
                .withUseJmx(false)
                .withDataDirectory(dataDirectoryPath);
    }

    public static OpenMQEmbeddedServiceBuilder persistentBroker() {
        String semiUniqueName = "persistent" + generateSemiUniqueBrokerName();

        BROKER_COUNT.increment();

        return persistentBroker(semiUniqueName);
    }

    public static OpenMQEmbeddedServiceBuilder persistentBroker(String name) {
        final String dataDirectoryPath = generateDataDirectoryPathForInstance(name);

        return new OpenMQEmbeddedServiceBuilder()
                .withDeleteAllMessagesOnStartup(true)
                .withBrokerName(name)
                .withAdvisorySupport(false)
                .withUseJmx(false)
                .withPersistent(true)
                .withDataDirectory(dataDirectoryPath);
    }

}
