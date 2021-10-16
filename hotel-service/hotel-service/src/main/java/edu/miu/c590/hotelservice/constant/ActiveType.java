package edu.miu.c590.hotelservice.constant;

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
