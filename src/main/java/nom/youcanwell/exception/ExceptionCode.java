package nom.youcanwell.exception;

import lombok.Getter;

public enum ExceptionCode {
    UNAUTHORIZED_USER(403, "not authorized user"),
    USER_NOT_FOUND(404, "User not found"),

    USER_NAME_ALREADY_EXISTS(400, "This memberName already exists"),
    USER_EXISTS(409, "User exists"),
    COURSE_NOT_FOUND(404, "Course not found"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    SIGNUP_WRONG(404,"somethings get wrong during login" ),
    TRADE_CODE_WRONG(404,"Not available tid" );

    @Getter
    private int code;

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
