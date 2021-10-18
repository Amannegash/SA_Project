package edu.miu.guestservice.exception;

public class BookingNotAvailableException extends RuntimeException {
    public BookingNotAvailableException(String msg) {
        super(msg);
    }
}
