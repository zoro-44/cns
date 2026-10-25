import java.util.*;

public class exp3a
{
    public static String encrypt(String text, int shift)
    {
        StringBuilder res = new StringBuilder();

        for (char ch : text.toCharArray())
        {
            if (Character.isLetter(ch))
            {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char ency = (char) (base + (ch - base + shift) % 26);
                res.append(ency);
            }
            else
            {
                res.append(ch);
            }
        }

        return res.toString();
    }

    public static String decrypt(String text, int shift)
    {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter plain text:");
        String plainText = sc.nextLine();

        System.out.println("Enter shift key (0-25):");
        int shift = sc.nextInt();

        String encrypted = encrypt(plainText, shift);
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(encrypted, shift);
        System.out.println("Decrypted text: " + decrypted);

        sc.close();
    }
}
//javac exp3a.java
// java exp3a