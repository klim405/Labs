package models.convertors;

import jakarta.persistence.AttributeConverter;
import models.fields.Password;

public class PasswordConvertor implements AttributeConverter<Password, String> {
    private static final String SEPARATOR = ":";

    @Override
    public String convertToDatabaseColumn(Password pwd) {
        if (pwd == null) return null;
        System.out.println(pwd.getSaltAsString() + SEPARATOR + pwd.getHashAsString());
        return pwd.getSaltAsString() + SEPARATOR + pwd.getHashAsString();
    }

    @Override
    public Password convertToEntityAttribute(String dbPassword) {
        if (dbPassword == null) return null;
        String[] saltAndHash = dbPassword.split(SEPARATOR);
        return new Password(saltAndHash[0], saltAndHash[1]);
    }
}
