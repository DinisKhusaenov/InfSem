package ru.kpfu.itis.errors;

import lombok.Getter;

@Getter
public enum ErrorEntity {

    INVALID_REQUEST(400, "Неверный запрос"),
    INVALID_TOKEN(403, "Ошибка авторизации"),
    FORBIDDEN(403, "Доступ запрещен"),
    NOT_FOUND(404, "Не найдено"),
    INVALID_NAME(450, "Неверное имя"),
    EMAIL_ALREADY_TAKEN(453, "Email уже занят"),

    USER_NOT_FOUND(404,"Пользователь не найден"),
    INCORRECT_PASSWORD(460, "Неверный пароль"),

    EMAIL_ALREADY_EXISTS(460,"Email уже занят"),
    INVALID_EMAIL(461,"Email не правильный"),
    PASSWORD_TOO_SIMPLE(462,"Пароль слишком простой"),
    ;

    int status;
    String message;

    ErrorEntity(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
