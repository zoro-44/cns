import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class exp10{

    // Method to convert byte array to hex string
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
        System.out.print("Enter text to hash using MD5: ");
        String input = scanner.nextLine();

        try {
            // Create MD5 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Compute the digest
            byte[] digestBytes = md.digest(input.getBytes());

            // Convert the digest to hex string
            String hash = bytesToHex(digestBytes);

            // Print the result
            System.out.println("MD5 Hash: " + hash);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("MD5 algorithm not found: " + e.getMessage());
        }

        scanner.close();
    }
}
//javac exp10.java
// java exp10