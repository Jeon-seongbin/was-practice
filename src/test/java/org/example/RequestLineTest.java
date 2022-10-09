package org.example;

import org.example.server.httpService.RequestLine;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestLineTest {
    @Test
    void create() {
        RequestLine requestLine = new RequestLine("GET /calculator?operand1=11&operator=*&operand2=22 HTTP/1.1");
        assertThat(requestLine).isNotNull();
        assertThat(requestLine).isEqualTo(new RequestLine("GET", "/calculator" ,"operand1=11&operator=*&operand2=22"));
    }

}
