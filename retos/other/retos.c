#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void checkMem(char *p)
{
    if (!p)
    {
        printf("no memory\n");
        exit(-2);
    }
}

int isAnagram(char *word1, char *word2)
{
    int isAnagram = 1;
    int i;
    int j;
    int length1 = strlen(word1);
    int length2 = strlen(word2);
    if (length1 != length2)
        return 0;
    for (i = 0; i < length1 && isAnagram; i++)
    {
        int contains = 0;
        for (j = 0; j < length2 && !contains; j++)
        {
            contains |= word1[i] == word2[j];
        }
        isAnagram &= contains;
    }
    return isAnagram;
}

int main(int argc, char *argv[])
{
    char *word1;
    char *word2;
    int length1 = strlen(argv[1]);
    int length2 = strlen(argv[2]);
    if (argc != 3)
    {
        printf("hay que pasar 2 parametros\n");
        exit(-1);
    }
    word1 = (char *)malloc(length1 * sizeof(char));
    checkMem(word1);
    word2 = (char *)malloc(length2 * sizeof(char));
    checkMem(word2);
    checkMem(strcpy(word1, argv[1]));
    checkMem(strcpy(word2, argv[2]));
    if (isAnagram(word1, word2))
        printf("en las palabras %s y %s hay un anagrama\n", word1, word2);
    else 
        printf("en las palabras %s y %s no hay un anagrama\n", word1, word2);
    free(word1);
    free(word2);
    return 0;
}
