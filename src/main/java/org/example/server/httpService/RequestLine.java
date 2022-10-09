package org.example.server.httpService;

import java.util.Objects;

public class RequestLine {

    private final String method; // GET
    private final String urlPath; // /calculator

    private QueryStrings queryStrings; // operand1=11&operator=*&operand2=22

    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];
        String[] urlPathTokens = tokens[1].split("\\?");
        this.urlPath = urlPathTokens[0];
        if (2 == urlPathTokens.length) {
            this.queryStrings = new QueryStrings(urlPathTokens[1]);
        }
    }

    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryStrings = new QueryStrings(queryString);
    }


    // 객체와 객체 끼리 비교할때에는 equals 와 hashcode 가 있어야 함
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryStrings, that.queryStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryStrings);
    }

    public boolean isGetRequest() {
        return "GET".equals(this.method);
    }

    public boolean matchPath(String path) {
        return urlPath.equals(path);
    }

    public QueryStrings getQueryStrings() {
        return this.queryStrings;
    }
}
