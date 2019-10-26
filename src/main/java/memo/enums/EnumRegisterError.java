package memo.enums;

public enum EnumRegisterError {
    PATTERN_LOGIN_ERROR(1),
    PATTERN_PASSWORD_ERROR(2),
    PATTERN_EMAIL_ERROR(3),
    DATABASE_USER_EXIST_ERROR(4),
    DATABASE_EMAIL_IS_TAKEN_EROR(5),
    DATABASE_SQL_EXCEPTION_ERROR(6),
    DATABASE_EXCEPTION_ERROR(7);
    private int errorCode;
    EnumRegisterError(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
