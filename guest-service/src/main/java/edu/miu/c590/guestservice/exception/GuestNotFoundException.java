package edu.miu.c590.guestservice.exception;

public class GuestNotFoundException extends RuntimeException {
    public GuestNotFoundException(String msg) {
        super(msg);
    }
}
