package com.example.admin.multithreading;

/**
 * Created by admin on 9/7/2017.
 */

public class MessageEvent {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageEvent(String message) {

        this.message = message;
    }
}
