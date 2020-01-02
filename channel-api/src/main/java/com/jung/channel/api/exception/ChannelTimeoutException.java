package com.jung.channel.api.exception;

public class ChannelTimeoutException extends RuntimeException {

    public ChannelTimeoutException() {
    }

    public ChannelTimeoutException(String message) {
        super(message);
    }

    public ChannelTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChannelTimeoutException(Throwable cause) {
        super(cause);
    }

    public ChannelTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
