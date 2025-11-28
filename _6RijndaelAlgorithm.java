

import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class _6RijndaelAlgorithm {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Input plaintext
        System.out.print("Enter plaintext: ");
        String plain = sc.nextLine();

        // AES key must be 16 bytes (128-bit)
        System.out.print("Enter 16-char key: ");
        String keyStr = sc.nextLine();

        if (keyStr.length() != 16) {
            System.out.println("Key must be exactly 16 characters!");
            return;
        }

        // Create AES key
        SecretKeySpec key = new SecretKeySpec(keyStr.getBytes(), "AES");

        // AES Cipher
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // ---------------- ENCRYPT ----------------
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plain.getBytes());
        String encText = Base64.getEncoder().encodeToString(encrypted);
        System.out.println("Encrypted Text: " + encText);

        // ---------------- DECRYPT ----------------
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(encText);
        byte[] decrypted = cipher.doFinal(decoded);
        System.out.println("Decrypted Text: " + new String(decrypted));
    }
}


// import javax.crypto.*;
// import javax.crypto.spec.SecretKeySpec;

// public class AESShort {
//     public static void main(String[] args) throws Exception {

//         String msg = "Hello World";
//         String keyStr = "1234567890123456"; // 16-byte key

//         SecretKeySpec key = new SecretKeySpec(keyStr.getBytes(), "AES");
//         Cipher c = Cipher.getInstance("AES");

//         c.init(Cipher.ENCRYPT_MODE, key);
//         byte[] enc = c.doFinal(msg.getBytes());

//         c.init(Cipher.DECRYPT_MODE, key);
//         byte[] dec = c.doFinal(enc);

//         System.out.println("Encrypted: " + new String(enc));
//         System.out.println("Decrypted: " + new String(dec));
//     }
// }

