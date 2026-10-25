import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class exp9{

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);

            if (hex.length() == 1)
                hexString.append('0');

            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text to hash using SHA-1: ");
        String input = scanner.nextLine();

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            md.update(input.getBytes());

            byte[] digestBytes = md.digest();

            String hash = bytesToHex(digestBytes);

            System.out.println("SHA-1 hash of \"" + input + "\" is:");
            System.out.println(hash);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("SHA-1 algorithm not found: " + e.getMessage());
        }

        scanner.close();
    }
}
//javac exp9.java
// java exp9