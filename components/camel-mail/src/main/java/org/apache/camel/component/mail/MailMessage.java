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
package org.apache.camel.component.mail;

import java.io.IOException;
import java.util.Map;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import org.apache.camel.Exchange;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.support.DefaultMessage;
import org.apache.camel.support.ExchangeHelper;
import org.apache.camel.util.ObjectHelper;

/**
 * Represents a {@link org.apache.camel.Message} for working with Mail
 */
public class MailMessage extends DefaultMessage {
    // we need a copy of the original message in case we need to workaround a charset issue when extracting
    // mail content, see more in MailBinding
    private Message originalMailMessage;
    private Message mailMessage;
    private boolean mapMailMessage;

    public MailMessage(Exchange exchange, Message message, boolean mapMailMessage) {
        super(exchange);
        this.originalMailMessage = this.mailMessage = message;
        this.mapMailMessage = mapMailMessage;
    }

    @Override
    public String toString() {
        // do not dump the mail content, as it requires live connection to the mail server
        return "MailMessage@" + ObjectHelper.getIdentityHashCode(this);
    }

    @Override
    public MailMessage copy() {
        MailMessage answer = (MailMessage) super.copy();
        answer.originalMailMessage = originalMailMessage;
        answer.mailMessage = mailMessage;
        return answer;
    }

    /**
     * Returns the original underlying Mail message
     */
    public Message getOriginalMessage() {
        return originalMailMessage;
    }

    /**
     * Returns the underlying Mail message
     */
    public Message getMessage() {
        return mailMessage;
    }

    public void setMessage(Message mailMessage) {
        if (this.originalMailMessage == null) {
            this.originalMailMessage = mailMessage;
        }
        this.mailMessage = mailMessage;
    }

    @Override
    public MailMessage newInstance() {
        MailMessage answer = new MailMessage(null, null, this.mapMailMessage);
        answer.setCamelContext(getCamelContext());
        return answer;
    }

    @Override
    protected Object createBody() {
        if (mailMessage != null) {
            MailBinding binding = ExchangeHelper.getBinding(getExchange(), MailBinding.class);
            return binding != null ? binding.extractBodyFromMail(getExchange(), this) : null;
        }
        return null;
    }

    @Override
    protected void populateInitialHeaders(Map<String, Object> map) {
        if (mailMessage != null) {
            try {
                MailBinding binding = ExchangeHelper.getBinding(getExchange(), MailBinding.class);
                if (binding != null) {
                    map.putAll(binding.extractHeadersFromMail(mailMessage, getExchange()));
                }
            } catch (MessagingException | IOException e) {
                throw new RuntimeCamelException("Error accessing headers due to: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public void copyFrom(org.apache.camel.Message that) {
        // only do a deep copy if we need to (yes when that is not a mail message, or if the mapMailMessage is true)
        boolean needCopy = !(that instanceof MailMessage) || (((MailMessage) that).mapMailMessage);
        if (needCopy) {
            super.copyFrom(that);
        } else {
            // no deep copy needed, but copy message id
            setMessageId(that.getMessageId());
        }
        if (that instanceof MailMessage) {
            MailMessage tmpMailMessage = (MailMessage) that;
            this.originalMailMessage = tmpMailMessage.originalMailMessage;
            this.mailMessage = tmpMailMessage.mailMessage;
            this.mapMailMessage = tmpMailMessage.mapMailMessage;
        }
        // cover over exchange if none has been assigned
        if (getExchange() == null) {
            setExchange(that.getExchange());
        }
    }

}
