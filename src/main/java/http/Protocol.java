package http;

public enum Protocol {
    HTTP("HTTP");

    private String protocol;

    Protocol(String protocol) {
        this.protocol = protocol.toUpperCase();
    }
}
