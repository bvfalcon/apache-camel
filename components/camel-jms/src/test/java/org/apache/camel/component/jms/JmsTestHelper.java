package org.apache.camel.component.jms;

import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.plugin.ActiveMQServerBasePlugin;

public class JmsTestHelper {
    public static String protocol = "CORE";
    static String projectSubfolder = "target/";

    public static Configuration getConfig() {
        try {
            Configuration config
                    = new ConfigurationImpl().addAcceptorConfiguration(protocol, "vm://0").setSecurityEnabled(false);
            config.setBindingsDirectory(projectSubfolder + config.getBindingsDirectory() + System.nanoTime())
                    .setJournalDirectory(projectSubfolder + config.getJournalDirectory() + System.nanoTime())
                    .setLargeMessagesDirectory(projectSubfolder + config.getLargeMessagesDirectory() + System.nanoTime())
                    .setPagingDirectory(projectSubfolder + config.getPagingDirectory() + System.nanoTime());
            return config;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Configuration getPersistentConfig() {
        return getConfig().setPersistenceEnabled(true);
    }

    public static Configuration getPersistentWithPluginConfig(ActiveMQServerBasePlugin plugin) {
        Configuration config = getConfig().setPersistenceEnabled(true);
        config.registerBrokerPlugin(plugin);
        return config;
    }
}
