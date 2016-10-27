package com.zslin.basic.exception;

/**
 * 系统异常
 * @author zslin.com 20160514
 *
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -4555331337009026323L;

    public SystemException() {
        super();
    }

    public SystemException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public SystemException(String msg) {
        super(msg);
    }

    public SystemException(Throwable throwable) {
        super(throwable);
    }
}
