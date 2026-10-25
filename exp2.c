#include <stdio.h>

int main()
{
    const char *str = "Hello World";
    int i = 0;

    printf("Original String: %s\n", str);

    printf("\nAND with 127:\n");

    while (str[i] != '\0')
    {
        printf("%c -> %c (ASCII: %d -> %d)\n",
               str[i],
               str[i] & 127,
               str[i],
               str[i] & 127);
        i++;
    }

    i = 0;

    printf("\nXOR with 127:\n");

    while (str[i] != '\0')
    {
        printf("%c -> %c (ASCII: %d -> %d)\n",
               str[i],
               str[i] ^ 127,
               str[i],
               str[i] ^ 127);
        i++;
    }

    return 0;
}
