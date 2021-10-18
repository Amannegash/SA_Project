package edu.miu.guestservice.exception;

public class GuestNotFoundException extends RuntimeException {
    public GuestNotFoundException(String msg) {
        super(msg);
    }
}
