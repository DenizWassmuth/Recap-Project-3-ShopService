public enum OrderStatus {

    PROCESSING("processing"),
    IN_DELIVERY("in_delivery"),
    COMPLETED("completed");

    private String value;
    OrderStatus(String description) {
        this.value = description;
    }
    public String getValue() {
        return value;
    }

}
