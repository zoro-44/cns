import java.util.*;

public class exp3b
{
    static final String ALPHABET =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static final String SUBSTITUTION_KEY =
        "QWERTYUIOPASDFGHJKLZXCVBNM";

    public static String encrypt(String plainText)
    {
        StringBuilder encrypted = new StringBuilder();

        for (char c : plainText.toUpperCase().toCharArray())
        {
            int idx = ALPHABET.indexOf(c);

            if (idx != -1)
            {
                encrypted.append(SUBSTITUTION_KEY.charAt(idx));
            }
            else
            {
                encrypted.append(c);
            }
        }

        return encrypted.toString();
    }

    public static String decrypt(String cipherText)
    {
        StringBuilder decrypted = new StringBuilder();

        for (char c : cipherText.toUpperCase().toCharArray())
        {
            int idx = SUBSTITUTION_KEY.indexOf(c);

            if (idx != -1)
            {
                decrypted.append(ALPHABET.charAt(idx));
            }
            else
            {
                decrypted.append(c);
            }
        }

        return decrypted.toString();
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plain text: ");
        String plainText = sc.nextLine();

        String encryptText = encrypt(plainText);

        System.out.println("Encrypted text: " + encryptText);

        String decryptText = decrypt(encryptText);

        System.out.println("Decrypted text: " + decryptText);

        sc.close();
    }
}
//javac exp3b.java
//java exp3b