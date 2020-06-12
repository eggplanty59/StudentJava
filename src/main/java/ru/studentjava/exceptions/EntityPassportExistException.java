package ru.studentjava.exceptions;

import org.springframework.util.Assert;

public class EntityPassportExistException extends BaseException{
    public EntityPassportExistException(String message) {
        super(message);
    }

    public EntityPassportExistException(String type, String passport) {
        super(formatMessage(type, passport));

    }

    private static String formatMessage(String type, String passport){
        Assert.hasText(type, "Тип не может быть пустым");
        Assert.hasText(passport, "Паспорт не может быть пустым");
        return String.format("%s с паспортом %s уже существует", type, passport);
    }
}
