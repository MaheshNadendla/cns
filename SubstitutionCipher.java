public class SubstitutionCipher {

    public static String substitutionEncrypt(String text, String key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        text = text.toUpperCase();
        StringBuilder encrypted = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (alphabet.indexOf(ch) != -1)
                encrypted.append(key.charAt(alphabet.indexOf(ch)));
            else
                encrypted.append(ch);
        }
        return encrypted.toString();
    }

    public static String substitutionDecrypt(String text, String key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        text = text.toUpperCase();
        StringBuilder decrypted = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (key.indexOf(ch) != -1)
                decrypted.append(alphabet.charAt(key.indexOf(ch)));
            else
                decrypted.append(ch);
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        String key = "QWERTYUIOPASDFGHJKLZXCVBNM";

        String enc = substitutionEncrypt("HELLO WORLD", key);
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + substitutionDecrypt(enc, key));
    }
}