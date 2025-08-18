

// 3. Write a Java program to perform encryption and decryption using the following algorithms 
// a. Ceaser cipher 
// b. Substitution cipher 
// c. Hill Cipher

// Program : 3

import java.util.*;

public class _3EncryptionAndDecryption {

    // =========================
    // 1. Caesar Cipher
    // =========================
    public static String caesarEncrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) ((c - base + key) % 26 + base));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String caesarDecrypt(String text, int key) {
        return caesarEncrypt(text, 26 - key); // reverse shift
    }

    // =========================
    // 2. Substitution Cipher
    // =========================
    public static String substitutionEncrypt(String text, String key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        text = text.toUpperCase();
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = alphabet.indexOf(c);
                result.append(key.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String substitutionDecrypt(String text, String key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        text = text.toUpperCase();
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = key.indexOf(c);
                result.append(alphabet.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // =========================
    // 3. Hill Cipher (2x2 matrix)
    // =========================
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return 1;
    }

    public static String hillEncrypt(String text, int[][] key) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        if (text.length() % 2 != 0) text += "X"; // pad if odd length
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int a = text.charAt(i) - 'A';
            int b = text.charAt(i + 1) - 'A';
            int x = (key[0][0] * a + key[0][1] * b) % 26;
            int y = (key[1][0] * a + key[1][1] * b) % 26;
            result.append((char) (x + 'A'));
            result.append((char) (y + 'A'));
        }
        return result.toString();
    }

    public static String hillDecrypt(String text, int[][] key) {
        int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]) % 26;
        if (det < 0) det += 26;
        int invDet = modInverse(det, 26);

        int[][] invKey = new int[2][2];
        invKey[0][0] = (key[1][1] * invDet) % 26;
        invKey[0][1] = ((-key[0][1] + 26) * invDet) % 26;
        invKey[1][0] = ((-key[1][0] + 26) * invDet) % 26;
        invKey[1][1] = (key[0][0] * invDet) % 26;

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int a = text.charAt(i) - 'A';
            int b = text.charAt(i + 1) - 'A';
            int x = (invKey[0][0] * a + invKey[0][1] * b) % 26;
            int y = (invKey[1][0] * a + invKey[1][1] * b) % 26;
            result.append((char) (x + 'A'));
            result.append((char) (y + 'A'));
        }
        return result.toString();
    }

    // =========================
    // Main Menu
    // =========================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose Cipher Method:");
        System.out.println("1. Caesar Cipher");
        System.out.println("2. Substitution Cipher");
        System.out.println("3. Hill Cipher");
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter plain text: ");
        String text = sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter key (number): ");
                int keyCaesar = sc.nextInt();
                String caesarEnc = caesarEncrypt(text, keyCaesar);
                String caesarDec = caesarDecrypt(caesarEnc, keyCaesar);
                System.out.println("Encrypted: " + caesarEnc);
                System.out.println("Decrypted: " + caesarDec);
                break;

            case 2:
                System.out.print("Enter substitution key (26 unique letters A-Z): ");
                String keySub = sc.next().toUpperCase();
                String subEnc = substitutionEncrypt(text, keySub);
                String subDec = substitutionDecrypt(subEnc, keySub);
                System.out.println("Encrypted: " + subEnc);
                System.out.println("Decrypted: " + subDec);
                break;

            case 3:
                System.out.println("Enter 2x2 Hill Cipher key matrix (4 integers): ");
                int[][] keyHill = new int[2][2];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        keyHill[i][j] = sc.nextInt();
                    }
                }
                String hillEnc = hillEncrypt(text, keyHill);
                String hillDec = hillDecrypt(hillEnc, keyHill);
                System.out.println("Encrypted: " + hillEnc);
                System.out.println("Decrypted: " + hillDec);
                break;

            default:
                System.out.println("Invalid choice.");
        }

        sc.close();
    }
}

// OutPut :
// Choose Cipher Method:
// 1. Caesar Cipher
// 2. Substitution Cipher
// 3. Hill Cipher
// 1     
// Enter plain text: Hello
// Enter key (number): 2
// Encrypted: Jgnnq
// Decrypted: Hello
