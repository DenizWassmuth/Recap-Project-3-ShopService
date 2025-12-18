public enum OrderStatus {

    PROCESSING("processing"),
    IN_DELIVERY("in_delivery"),
    COMPLETED("completed");

    private String value;
    OrderStatus(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

}
