package hello.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class RestResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String returnCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String returnMessage;
    @JsonUnwrapped
    private T body;

    public RestResponse() {
    }

    public RestResponse(String returnCode, T body) {
        this.returnCode = returnCode;
        this.body = body;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
