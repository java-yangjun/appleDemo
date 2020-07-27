package com.jung.channel.api.test.redisLock.exception;

public class CacheLockException extends RuntimeException {

    public CacheLockException() {
    }

    public CacheLockException(String message) {
        super(message);
    }

    public CacheLockException(int code, String message) {
        super(message);
    }

    public CacheLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheLockException(Throwable cause) {
        super(cause);
    }

    public CacheLockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
