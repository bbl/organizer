package hello.utils;

import hello.dto.RestResponse;

import static hello.utils.ErrorConstants.RETURN_CODE_OK;

public class RestResponseUtil {

    public static <T> RestResponse<T> withReturnCode(T output, String returnCode) {
        return new RestResponse<>(returnCode, output);
    }

    public static <T> RestResponse<T> withReturnCode(T output, String returnCode, String returnMessage) {
        RestResponse<T> response = new RestResponse<>(returnCode, output);
        response.setReturnMessage(returnMessage);
        return response;
    }

    public static <T> RestResponse<T> withReturnCodeOk() {
        return new RestResponse<>(RETURN_CODE_OK, null);
    }

    public static <T> RestResponse<T> withReturnCodeOk(T body) {
        return new RestResponse<>(RETURN_CODE_OK, body);
    }

    public static <T> RestResponse<T> withReturnCode(T output, Integer returnCode) {
        return withReturnCode(output, returnCode.toString());
    }

    public static <T> boolean isOk(RestResponse<T> restResponse) {
        return restResponse != null && RETURN_CODE_OK.equals(restResponse.getReturnCode());
    }

    public static <T> boolean isNotOk(RestResponse<T> restResponse) {
        return !isOk(restResponse);
    }

}
