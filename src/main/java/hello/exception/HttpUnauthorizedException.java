package hello.exception;

public class HttpUnauthorizedException extends RestControllerException {
    public HttpUnauthorizedException(String returnCode, String returnMessage) {
        super(returnCode, returnMessage);
    }

    public HttpUnauthorizedException() {
    }

    public HttpUnauthorizedException(String returnCode) {
        super(returnCode);
    }
}
