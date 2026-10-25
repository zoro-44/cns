public class exp6
{
    private int[] S = new int[256];
    private int x = 0;
    private int y = 0;

    public exp6(byte[] key)
    {
        initialize(key);
    }

    private void initialize(byte[] key)
    {
        for (int i = 0; i < 256; i++)
        {
            S[i] = i;
        }

        int j = 0;

        for (int i = 0; i < 256; i++)
        {
            j = (j + S[i] + (key[i % key.length] & 0xFF)) & 0xFF;

            int temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }
    }

    public byte[] encryptDecrypt(byte[] input)
    {
        byte[] result = new byte[input.length];

        for (int i = 0; i < input.length; i++)
        {
            x = (x + 1) & 0xFF;
            y = (y + S[x]) & 0xFF;

            int temp = S[x];
            S[x] = S[y];
            S[y] = temp;

            int xorIndex = (S[x] + S[y]) & 0xFF;

            result[i] = (byte)(input[i] ^ S[xorIndex]);
        }

        return result;
    }

    public static void main(String[] args)
    {
        String text = "Hello World";

        byte[] key = "secretkey".getBytes();

        exp6 rc4 = new exp6(key);

        byte[] encrypted =
                rc4.encryptDecrypt(text.getBytes());

        System.out.println("Encrypted (Hex): "
                + bytesToHex(encrypted));

        exp6 rc4Decrypt = new exp6(key);

        byte[] decrypted =
                rc4Decrypt.encryptDecrypt(encrypted);

        System.out.println("Decrypted: "
                + new String(decrypted));
    }

    private static String bytesToHex(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes)
        {
            sb.append(String.format("%02x", b & 0xFF));
        }

        return sb.toString();
    }
}

// Commands: javac exp6.java
// Commands: java exp6