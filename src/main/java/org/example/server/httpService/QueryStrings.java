package org.example.server.httpService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryStrings {

    private List<QueryString> queryStringList = new ArrayList<>();

    public QueryStrings(String queryStringLine) {
        String[] queryStringTokens = queryStringLine.split("&");
        Arrays.stream(queryStringTokens).forEach(obj -> {
            String[] values = obj.split("=");
            if (values.length != 2) {
                throw new IllegalArgumentException("wrong queryString");
            }
            queryStringList.add(new QueryString(values[0], values[1]));
        });
    }

    public String getValue(String key) {
        return queryStringList.stream()
                .filter(obj -> obj.exists(key))
                .map(QueryString::getValue)
                .findFirst()
                .orElse(null);
    }
}
