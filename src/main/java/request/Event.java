package request;

public enum Event {
    UPDATE(1, "update"),
    SAVE(2, "insert"),
    GET(3, "get"),
    DELETE(4, "delete"),
    UNKNOWN(-1, "unknown");

    private Integer code;
    private String description;

    Event(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }
}
