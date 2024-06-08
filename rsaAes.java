import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

interface Encryptable {
    String encrypt(String data);
    String decrypt(String encryptedData);
}

// AES class implementing Encryptable interface
class AES implements Encryptable {
    private Key secretKey;

    public AES() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // 128-bit key size
        secretKey = keyGenerator.generateKey();
    }

    @Override
    public String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

// RSA class implementing Encryptable interface
class RSA implements Encryptable {
    private KeyPair keyPair;

    public RSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // 2048-bit key size
        keyPair = keyPairGenerator.generateKeyPair();
    }

    @Override
    public String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override

    public String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

// Main class
public class rsaAes {
    public static void main(String[] args) {
        try {
            String data = "Call me AlphaFrederic!";

            Encryptable aes = new AES();
            String encryptedDataAES = aes.encrypt(data);
            String decryptedDataAES = aes.decrypt(encryptedDataAES);

            Encryptable rsa = new RSA();
            String encryptedDataRSA = rsa.encrypt(data);
            String decryptedDataRSA = rsa.decrypt(encryptedDataRSA);

            System.out.println("Original Data: \n" + data);
            System.out.println("AES Encrypted Data: " + encryptedDataAES);
            System.out.println("AES Decrypted Data: \n" + decryptedDataAES);
            System.out.println("RSA Encrypted Data: " + encryptedDataRSA);
            System.out.println("RSA Decrypted Data: " + decryptedDataRSA);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}