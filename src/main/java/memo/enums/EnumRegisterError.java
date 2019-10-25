package memo.enums;

public enum EnumRegisterError {
    PATTERN_LOGIN_ERROR(1),
    PATTERN_PASSWORD_ERROR(2),
    PATTERN_EMAIL_ERROR(3);


    private int errorCode;
    EnumRegisterError(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
