#include <stdio.h>

int main()
{
    const char str[] = "Hello World";
    char res[100];
    int i = 0;

    printf("Input string is: %s\n", str);

    while (str[i] != '\0')
    {
        res[i] = str[i] ^ 0;
        i++;
    }

    res[i] = '\0';

    printf("Exclusive XOR: %s\n", res);

    return 0;
}
