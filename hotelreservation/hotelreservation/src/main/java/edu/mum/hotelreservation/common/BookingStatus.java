package edu.mum.hotelreservation.common;

public enum BookingStatus {
    BOOKED("booked"),
    COMPLETED("completed");

    private String status;

    private BookingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
