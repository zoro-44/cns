import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class exp4
{
    public static SecretKey generateKey() throws Exception
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        return keyGenerator.generateKey();
    }

    public static String encrypt(String plainText, SecretKey secretKey)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String cipherText, SecretKey secretKey)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(
                Base64.getDecoder().decode(cipherText)
        );

        return new String(decryptedBytes, "UTF-8");
    }

    public static void main(String[] args)
    {
        try
        {
            String plainText = "Hello, DES Encryption";

            System.out.println("Plain Text: " + plainText);

            SecretKey key = generateKey();

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
//javac exp4.java java exp4