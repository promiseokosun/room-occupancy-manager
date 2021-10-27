package com.techblazer.lbt.dto;

import com.techblazer.lbt.constant.ResponseCodes;
import com.techblazer.lbt.constant.ResponseStatuses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private ResponseStatuses status;
    private String code;
    private String message;
    private T data;

    public static BaseResponse<?> getSuccessResponse(Object data) {
        return new BaseResponse<>(ResponseStatuses.SUCCESS, ResponseCodes.SUCCESSFUL.getCode(), ResponseCodes.SUCCESSFUL.getMessage(), data);
    }

    public static BaseResponse<?> getSuccessResponse(String code, String message, Object data) {
        return new BaseResponse<>(ResponseStatuses.SUCCESS, code, message, data);
    }

    public static BaseResponse<?> getFailedResponse(String code, String message, Object data) {
        return new BaseResponse<>(ResponseStatuses.FAILED, code, message, data);
    }

    public static BaseResponse<?> getResponse(ResponseStatuses status, String code, String message, Object data) {
        return new BaseResponse<>(status, code, message, data);
    }
}
