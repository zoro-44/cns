import java.security.*;
import java.util.Base64;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.DHParameterSpec;

public class exp8
{
    public static void main(String[] args) throws Exception
    {
        // Generate Diffie-Hellman parameters
        AlgorithmParameterGenerator paramGen =
                AlgorithmParameterGenerator.getInstance("DH");

        paramGen.init(512);

        AlgorithmParameters params =
                paramGen.generateParameters();

        DHParameterSpec dhSpec =
                params.getParameterSpec(DHParameterSpec.class);

        // Generate key pair for Party A
        KeyPairGenerator keyPairGenA =
                KeyPairGenerator.getInstance("DH");

        keyPairGenA.initialize(dhSpec);

        KeyPair keyPairA =
                keyPairGenA.generateKeyPair();

        // Generate key pair for Party B
        KeyPairGenerator keyPairGenB =
                KeyPairGenerator.getInstance("DH");

        keyPairGenB.initialize(dhSpec);

        KeyPair keyPairB =
                keyPairGenB.generateKeyPair();

        // Key agreement for Party A
        KeyAgreement keyAgreeA =
                KeyAgreement.getInstance("DH");

        keyAgreeA.init(keyPairA.getPrivate());

        // Key agreement for Party B
        KeyAgreement keyAgreeB =
                KeyAgreement.getInstance("DH");

        keyAgreeB.init(keyPairB.getPrivate());

        // Generate shared secret
        keyAgreeA.doPhase(
                keyPairB.getPublic(), true);

        byte[] sharedSecretA =
                keyAgreeA.generateSecret();

        keyAgreeB.doPhase(
                keyPairA.getPublic(), true);

        byte[] sharedSecretB =
                keyAgreeB.generateSecret();

        // Display shared secrets
        System.out.println(
                "Party A Shared Secret: "
                + Base64.getEncoder()
                        .encodeToString(sharedSecretA)
        );

        System.out.println(
                "Party B Shared Secret: "
                + Base64.getEncoder()
                        .encodeToString(sharedSecretB)
        );

        // Check whether both secrets are equal
        System.out.println(
                "Keys match: "
                + MessageDigest.isEqual(
                        sharedSecretA,
                        sharedSecretB
                )
        );
    }
}

// Commands:
// javac exp8.java
// java exp8