
// 1. Write a C program that contains a string (char pointer) with a value ‘Hello world’. The program should
// XOR each character in this string with 0 and display the result. 


// Program : 1 

#include <stdio.h>

int main() {
    const char *str = "Hello world";
    
    printf("Original String: %s\n", str);
    printf("After XOR with 0: ");

   
    for (int i = 0; str[i] != '\0'; i++) {
        char result = str[i] ^ 0; 
        printf("%c", result);
    }

    printf("\n");
    return 0;
}

// OutPut : 

// Original String: Hello world
// After XOR with 0: Hello world
