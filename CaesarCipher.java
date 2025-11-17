public class CaesarCipher {

    public static String caesarEncrypt(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c))
                result.append((char)((c - 'A' + key) % 26 + 'A'));
            else if (Character.isLowerCase(c))
                result.append((char)((c - 'a' + key) % 26 + 'a'));
            else
                result.append(c);
        }
        return result.toString();
    }

    public static String caesarDecrypt(String text, int key) {
        return caesarEncrypt(text, 26 - key);
    }

    public static void main(String[] args) {
        String enc = caesarEncrypt("HELLO WORLD", 3);
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + caesarDecrypt(enc, 3));
    }
}
