package models.fields;

import jakarta.validation.constraints.NotNull;
import settings.Settings;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class Password implements Serializable {
    final private byte[] hash;
    final private byte[] salt;

    public Password(String salt, String hash) {
        this.hash = stringToByteArray(hash);
        this.salt = stringToByteArray(salt);
    }

    public Password(String password) {
        salt = generateSalt();
        hash = generateHash(password);
    }

    protected byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    protected byte[] generateHash(String password) {
        try {
            password = password + Settings.HASH_PAPER;
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(Settings.HASH_ALGORITHM);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hash algorithm '" + Settings.HASH_ALGORITHM + "' didn't found! Try to change Setting.HASH_ALGORITHM.");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("InvalidKeySpecException: cannot create hash!");
        }
    }

    public boolean isEqual(String password) {
        return Arrays.equals(hash, generateHash(password));
    }

    protected String byteArrayToString(byte[] arr) {
        return Base64.getEncoder().encodeToString(arr);
    }

    public String getSaltAsString() {
        return byteArrayToString(salt);
    }

    public String getHashAsString() {
        return byteArrayToString(hash);
    }

    protected byte[] stringToByteArray(String str) {
        return Base64.getDecoder().decode(str);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password)) return false;
        Password password = (Password) o;
        return Arrays.equals(hash, password.hash) && Arrays.equals(salt, password.salt);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(hash);
        result = 31 * result + Arrays.hashCode(salt);
        return result;
    }
}
