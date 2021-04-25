package kr.co.songjava.configuration.http;

import lombok.Data;

/**
 * 공통으로 사용할 응답 클래스
 *
 * @param <T>
 */
@Data
public class BaseResponse<T> {

    private BaseResponseCode code;
    private String message;
    private T data;

    /**
     * 성공
     *
     * @param data
     */
    public BaseResponse(T data) {
        this.code = BaseResponseCode.SUCCESS;
        this.data = data;
    }

    /**
     * 예외 처리
     *
     * @param responseCode
     * @param message
     */
    public BaseResponse(BaseResponseCode responseCode, String message) {
        this.code = responseCode;
        this.message = message;
    }
}
