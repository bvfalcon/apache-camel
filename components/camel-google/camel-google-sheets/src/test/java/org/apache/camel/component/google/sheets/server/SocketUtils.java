package org.apache.camel.component.google.sheets.server;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketUtils {
    private SocketUtils() {
    }

    public static int findAvailableTcpPort() {
        int port = 0;
        try (ServerSocket socket = new ServerSocket(0)) {
            socket.setReuseAddress(true);
            port = socket.getLocalPort();
        } catch (IOException e) {
        }
        return port;
    }
}
