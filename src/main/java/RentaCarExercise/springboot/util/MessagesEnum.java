package RentaCarExercise.springboot.util;

public enum MessagesEnum {

    EMAIL_IN_USE("Email taken");

    private final String message;

    MessagesEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
