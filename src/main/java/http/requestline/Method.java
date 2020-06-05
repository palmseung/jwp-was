package http.requestline;

import java.util.Arrays;

public enum Method {
    POST("POST"),
    GET("GET");

    private String method;

    Method(String method) {
        this.method = method;
    }

    private static Method match(String requestLine){
        return Arrays.stream(values())
                .filter(m -> requestLine.startsWith(m.method))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not Found Matched HTTP method!"));
    }

    
}
