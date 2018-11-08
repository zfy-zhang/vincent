package com.vincent.personal.exception;

/**
 * Created with IDEA
 * author:vincent
 * Date:2018/11/7
 */
public class TipException extends RuntimeException {
    public TipException() {
    }

    public TipException(String message) {
        super(message);
    }

    public TipException(String message, Throwable cause) {
        super(message, cause);
    }

    public TipException(Throwable cause) {
        super(cause);
    }
}
