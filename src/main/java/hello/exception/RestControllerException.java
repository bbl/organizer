package hello.exception;

public class RestControllerException extends RuntimeException {
    private String returnCode;
    private String returnMessage;

    public RestControllerException(String returnCode, String returnMessage) {
        super(returnMessage, null, false, false);
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    public RestControllerException() {
        super(null, null, false, false);
    }

    public RestControllerException(String returnCode) {
        super(null, null, false, false);
        this.returnCode = returnCode;
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
}
