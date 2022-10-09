package org.example.server.httpService;

// queryString 은 key value 하나 가지고 있음

public class QueryString {
    private final String key;
    private final String value;


    public QueryString(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public boolean exists(String key) {
        return this.key.equals(key);
    }

    public String getValue() {
        return this.value;
    }
}
