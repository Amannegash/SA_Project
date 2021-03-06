package edu.miu.hotelservice.common;

public enum ActiveType {
    YES("Y"),
    NO("N");

    private String value;

    private ActiveType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
