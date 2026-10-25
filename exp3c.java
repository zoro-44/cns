import java.util.*;

public class exp3c
{
    static int[][] keyMatrix = new int[2][2];
    static int[][] inverseKeyMatrix = new int[2][2];

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 4 integers for 2x2 key matrix:");

        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                keyMatrix[i][j] = sc.nextInt();
            }
        }

        if (!findInverseKey())
        {
            System.out.println("Key Matrix is not invertible. Exiting.");
            return;
        }

        sc.nextLine();

        System.out.println("Enter plaintext (uppercase only):");
        String plaintext = sc.nextLine()
                             .toUpperCase()
                             .replaceAll("[^A-Z]", "");

        if (plaintext.length() % 2 != 0)
        {
            plaintext += "X";
        }

        String encrypted = encrypt(plaintext);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }

    public static String encrypt(String text)
    {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2)
        {
            int[] pair = {
                text.charAt(i) - 'A',
                text.charAt(i + 1) - 'A'
            };

            int[] encryptedPair = new int[2];

            for (int row = 0; row < 2; row++)
            {
                encryptedPair[row] =
                    (keyMatrix[row][0] * pair[0]
                    + keyMatrix[row][1] * pair[1]) % 26;
            }

            result.append((char)(encryptedPair[0] + 'A'));
            result.append((char)(encryptedPair[1] + 'A'));
        }

        return result.toString();
    }

    public static String decrypt(String text)
    {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2)
        {
            int[] pair = {
                text.charAt(i) - 'A',
                text.charAt(i + 1) - 'A'
            };

            int[] decryptedPair = new int[2];

            for (int row = 0; row < 2; row++)
            {
                decryptedPair[row] =
                    (inverseKeyMatrix[row][0] * pair[0]
                    + inverseKeyMatrix[row][1] * pair[1]) % 26;

                if (decryptedPair[row] < 0)
                {
                    decryptedPair[row] += 26;
                }
            }

            result.append((char)(decryptedPair[0] + 'A'));
            result.append((char)(decryptedPair[1] + 'A'));
        }

        return result.toString();
    }

    public static boolean findInverseKey()
    {
        int a = keyMatrix[0][0];
        int b = keyMatrix[0][1];
        int c = keyMatrix[1][0];
        int d = keyMatrix[1][1];

        int det = (a * d - b * c) % 26;

        if (det < 0)
        {
            det += 26;
        }

        int detInv = -1;

        for (int i = 0; i < 26; i++)
        {
            if ((det * i) % 26 == 1)
            {
                detInv = i;
                break;
            }
        }

        if (detInv == -1)
        {
            return false;
        }

        inverseKeyMatrix[0][0] = (d * detInv) % 26;
        inverseKeyMatrix[0][1] = (-b * detInv) % 26;
        inverseKeyMatrix[1][0] = (-c * detInv) % 26;
        inverseKeyMatrix[1][1] = (a * detInv) % 26;

        for (int i = 0; i < 2; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                if (inverseKeyMatrix[i][j] < 0)
                {
                    inverseKeyMatrix[i][j] += 26;
                }
            }
        }

        return true;
    }
}
//javac exp3c.java
//java exp3c