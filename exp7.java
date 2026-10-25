import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;

public class exp7
{
    public static KeyPair generateRSAKeyPair(int keySize) throws Exception
    {
        KeyPairGenerator keyGen =
                KeyPairGenerator.getInstance("RSA");

        keyGen.initialize(keySize);

        return keyGen.generateKeyPair();
    }

    public static String encrypt(String plainText,
                                 PublicKey publicKey)
                                 throws Exception
    {
        Cipher encryptCipher =
                Cipher.getInstance("RSA");

        encryptCipher.init(
                Cipher.ENCRYPT_MODE,
                publicKey
        );

        byte[] encryptedBytes =
                encryptCipher.doFinal(
                        plainText.getBytes("UTF-8")
                );

        return Base64.getEncoder()
                .encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText,
                                  PrivateKey privateKey)
                                  throws Exception
    {
        Cipher decryptCipher =
                Cipher.getInstance("RSA");

        decryptCipher.init(
                Cipher.DECRYPT_MODE,
                privateKey
        );

        byte[] decryptedBytes =
                decryptCipher.doFinal(
                        Base64.getDecoder()
                        .decode(encryptedText)
                );

        return new String(
                decryptedBytes,
                "UTF-8"
        );
    }

    public static void main(String[] args)
    {
        try
        {
            KeyPair keyPair =
                    generateRSAKeyPair(2048);

            PublicKey publicKey =
                    keyPair.getPublic();

            PrivateKey privateKey =
                    keyPair.getPrivate();

            String plainText =
                    "Hello, RSA Encryption!";

            System.out.println(
                    "Plain Text: " + plainText
            );

            String originalText =
                    "Hello, RSA Encryption!";

            String encryptedText =
                    encrypt(originalText, publicKey);

            System.out.println(
                    "Encrypted Text: "
                    + encryptedText
            );

            String decryptedText =
                    decrypt(encryptedText, privateKey);

            System.out.println(
                    "Decrypted Text: "
                    + decryptedText
            );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

// Commands:
// javac exp7.java
// java exp7