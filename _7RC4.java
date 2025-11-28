import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class _7RC4 {
    public static void main(String[] args) throws Exception {
        String msg = "Hello World";

        // Generate RC4 key
        KeyGenerator kg = KeyGenerator.getInstance("RC4");
        SecretKey key = kg.generateKey();

        // Encrypt
        Cipher c = Cipher.getInstance("RC4");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] enc = c.doFinal(msg.getBytes());

        // Decrypt
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] dec = c.doFinal(enc);

        System.out.println("Encrypted: " + new String(enc));
        System.out.println("Decrypted: " + new String(dec));
    }
}
