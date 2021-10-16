package edu.miu.c590.guestservice.exception;

public class BookingNotAvailableException extends RuntimeException {
    public BookingNotAvailableException(String msg) {
        super(msg);
    }
}
