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
