package kr.co.songjava.configuration.exception;

import kr.co.songjava.configuration.http.BaseResponseCode;

/**
 * custom 으로 예외 처리
 */
public abstract class AbstractBaseException extends RuntimeException {

    private static final long serialVersionUID = 8342235231880246631L;

    protected BaseResponseCode responseCode;
    protected Object[] args;

    public AbstractBaseException() {

    }

    public AbstractBaseException(BaseResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public BaseResponseCode getResponseCode() {
        return responseCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
