package org.example.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {
    private final int port;

    private final ExecutorService executorService = Executors.newFixedThreadPool(16);
    // 스레드 풀을 이용해서

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);


    public void start() throws IOException {
        // 서버에서 소켓을 연다
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port", port);

            // 클라이언트를 대기한다
            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");
            // 클라이언트를 기다린다
            while ((clientSocket = serverSocket.accept()) != null) { // accept
                logger.info("[CustomWebApplicationServer] client connected.");

                // 스레드 풀을 이용하여 안정적인 서비스
                executorService.execute(new ClientRequestHandler(clientSocket));

            }

        }
    }
}
