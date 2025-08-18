
// 2. Write a C program that contains a string (char pointer) with a value ‘Hello world’. The program should
// AND or and XOR each character in this string with 127 and display the result.


// Program : 2

#include <stdio.h>

int main() {
    char *str = "Hello world";
    int i = 0;

    printf("Original String: %s\n\n", str);
    
    printf("Char\tASCII\tAND 127\tXOR 127\n");
    printf("------------------------------------\n");

    while (str[i] != '\0') {
        char ch = str[i];
        int and_result = ch & 127;
        int xor_result = ch ^ 127;

        printf("%c\t%d\t%d\t%d\n", ch, ch, and_result, xor_result);
        i++;
    }

    return 0;
}

// OutPut : 

// Original String: Hello world

// Char    ASCII   AND 127 XOR 127
// ------------------------------------
// H       72      72      55
// e       101     101     26
// l       108     108     19
// l       108     108     19
// o       111     111     16
//         32      32      95
// w       119     119     8
// o       111     111     16
// r       114     114     13
// l       108     108     19
// d       100     100     27
