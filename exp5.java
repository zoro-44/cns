import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class exp5
{
    public static String encrypt(String plainText, SecretKey key)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedBytes =
                cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String cipherText, SecretKey key)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decryptedBytes =
                cipher.doFinal(Base64.getDecoder().decode(cipherText));

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static SecretKey generateBlowfishKey(int keySize)
            throws Exception
    {
        KeyGenerator keyGenerator =
                KeyGenerator.getInstance("Blowfish");

        keyGenerator.init(keySize);

        return keyGenerator.generateKey();
    }

    public static void main(String[] args)
    {
        try
        {
            SecretKey key = generateBlowfishKey(128);

            String plainText = "Hello, Blowfish Encryption";

            System.out.println("Plain Text: " + plainText);

            String encryptedText = encrypt(plainText, key);

            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = decrypt(encryptedText, key);

            System.out.println("Decrypted Text: " + decryptedText);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
//javac exp5.java
//java exp5