package com.microfinance.security.payload.response;

/**
 * Simple message response payload.
 */
public class MessageResponse {
    private String message;

    /**
     * Constructor for MessageResponse.
     *
     * @param message The message
     */
    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}