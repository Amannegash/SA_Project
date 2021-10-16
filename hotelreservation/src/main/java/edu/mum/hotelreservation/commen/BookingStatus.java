package edu.mum.hotelreservation.commen;

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
