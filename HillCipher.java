import java.util.Scanner;

public class HillCipher {

    // Multiply 2x2 * 2x1 mod 26
    public static int[][] multiply(int[][] key, int[][] vector) {
        int[][] result = new int[2][1];
        result[0][0] = (key[0][0] * vector[0][0] + key[0][1] * vector[1][0]) % 26;
        result[1][0] = (key[1][0] * vector[0][0] + key[1][1] * vector[1][0]) % 26;
        return result;
    }

    // Clean text: remove spaces, convert to uppercase
    public static String cleanText(String text) {
        StringBuilder sb = new StringBuilder();

        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            // Keep only A-Z
            if (c >= 'A' && c <= 'Z') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // Encrypt
    public static String encrypt(String text, int[][] key) {

        text = cleanText(text);

        // If odd length, add X
        if (text.length() % 2 != 0) {
            text = text + "X";
        }

        StringBuilder enc = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int a = text.charAt(i) - 'A';
            int b = text.charAt(i + 1) - 'A';

            int[][] vector = { { a }, { b } };

            int[][] result = multiply(key, vector);

            enc.append((char) (result[0][0] + 'A'));
            enc.append((char) (result[1][0] + 'A'));
        }

        return enc.toString();
    }

    // Find inverse matrix mod 26
    public static int[][] inverseMatrix(int[][] key) {

        int det = (key[0][0] * key[1][1]) - (key[0][1] * key[1][0]);
        det = (det % 26 + 26) % 26;

        // Find multiplicative inverse
        int detInv = -1;
        for (int i = 1; i < 26; i++) {
            if ((det * i) % 26 == 1) {
                detInv = i;
                break;
            }
        }

        if (detInv == -1) {
            throw new RuntimeException("Matrix has no inverse mod 26");
        }

        int[][] inv = new int[2][2];
        inv[0][0] = ( key[1][1] * detInv ) % 26;
        inv[1][1] = ( key[0][0] * detInv ) % 26;
        inv[0][1] = ((-key[0][1] + 26) * detInv ) % 26;
        inv[1][0] = ((-key[1][0] + 26) * detInv ) % 26;

        return inv;
    }

    // Decrypt
    public static String decrypt(String text, int[][] key) {

        text = cleanText(text);
        int[][] invKey = inverseMatrix(key);

        StringBuilder dec = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int a = text.charAt(i) - 'A';
            int b = text.charAt(i + 1) - 'A';

            int[][] vector = { { a }, { b } };

            int[][] result = multiply(invKey, vector);

            dec.append((char) (result[0][0] + 'A'));
            dec.append((char) (result[1][0] + 'A'));
        }

        return dec.toString();
    }

    // MAIN
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String text = sc.nextLine();

        System.out.println("Enter 2x2 key matrix:");
        int[][] key = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key[i][j] = sc.nextInt();
            }
        }

        String encrypted = encrypt(text, key);
        System.out.println("Encrypted: " + encrypted);

        try {
            String decrypted = decrypt(encrypted, key);
            System.out.println("Decrypted: " + decrypted);
        } catch (Exception e) {
            System.out.println("Matrix not invertible!");
        }

        sc.close();
    }
}