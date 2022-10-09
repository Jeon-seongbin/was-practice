package org.example.server;

import org.example.calculator.Calculator;
import org.example.server.httpService.HttpRequest;
import org.example.server.httpService.HttpResponse;
import org.example.server.httpService.QueryStrings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);
    private final Socket clientSocket;

    ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        logger.info("[ClientRequestHandler] new client {} was started.", Thread.currentThread().getName());
        System.out.println("[ClientRequestHandler] new client {} was started.");
        try (InputStream in = clientSocket.getInputStream();
             OutputStream out = clientSocket.getOutputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            DataOutputStream dos = new DataOutputStream(out);


            HttpRequest httpRequest = new HttpRequest(br);
            boolean isGetRequest = httpRequest.isGetRequest();
            boolean isMatch = httpRequest.matchPath("/calculator");
            if (isGetRequest && isMatch) {
                // 값 추출
                QueryStrings queryStrings = httpRequest.getQueryStrings();
                int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                String operator = queryStrings.getValue("operator");
                int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                int result = Calculator.calculator(operand1, operator, operand2);

                byte[] body = String.valueOf(result).getBytes();
                HttpResponse httpResponse = new HttpResponse(dos);
                httpResponse.response200Header("application/json", body.length);
                httpResponse.responseBody(body);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }
}
