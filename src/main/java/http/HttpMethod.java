package http;

public enum HttpMethod {
    GET("GET"), POST("POST");

    private String method;

    HttpMethod(String method) {
        this.method = method.toUpperCase();
    }
}