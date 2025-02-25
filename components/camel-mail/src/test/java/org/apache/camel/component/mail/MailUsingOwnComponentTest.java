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

import jakarta.mail.Message;
import org.apache.camel.CamelContext;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.jvnet.mock_javamail.Mailbox;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for CAMEL-1249
 */
public class MailUsingOwnComponentTest extends CamelTestSupport {

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext context = super.createCamelContext();

        MailConfiguration config = new MailConfiguration();
        config.configureProtocol("smtp");
        config.setUsername("james");
        config.setHost("localhost");
        config.setPort(25);
        config.setPassword("admin");
        config.setIgnoreUriScheme(true);

        MailComponent myMailbox = new MailComponent();
        myMailbox.setConfiguration(config);

        context.addComponent("mailbox", myMailbox);

        return context;
    }

    @Test
    public void testUsingOwnMailComponent() throws Exception {
        Mailbox.clearAll();

        template.sendBodyAndHeader("mailbox:foo", "Hello Mailbox", "to", "davsclaus@apache.org");

        Mailbox box = Mailbox.get("davsclaus@apache.org");
        Message msg = box.get(0);
        assertEquals("davsclaus@apache.org", msg.getRecipients(Message.RecipientType.TO)[0].toString());
    }

}
