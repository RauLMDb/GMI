#include <stdio.h>
#include <stdlib.h>

typedef struct punto
{
    int x;
    int y;
} punto;

typedef struct rectangulo
{
    punto *infizq;
    punto *supdrch;
} rectangulo;

int area(rectangulo *rect)
{
    int a = rect->infizq->x - rect->supdrch->x;
    int b = rect->infizq->y - rect->supdrch->y;
    int area = a * b;
    return area < 0 ? -1 * area : area;
}
void printRect(rectangulo *rect)
{
    printf("%i %i %i %i\n", rect->infizq->x, rect->infizq->y, rect->supdrch->x, rect->supdrch->y);
}

int elem(int list[], int e, int length)
{
    int res = 0;
    int i;
    for (i = 0; i < length && !res; i++)
    {
        res |= list[i] == e;
    }
    return res;
}

void printOrd(rectangulo **rect, int n)
{
    int *printed = (int *)malloc(n * sizeof(int));
    int i, j, k = 0, grt = 0;
    for (i = 0; i < n; i++)
    {
        printed[i] = -1;
    }
    for (i = 0; i < n; i++)
    {
        int a = area(rect[i]);
        for (j = 0; j < n && !grt; j++)
        {
            if (!elem(printed, j, n))
            {
                grt |= a < area(rect[j]);
            }
        }
        if (!grt)
        {
            printed[k++] = i;
            printRect(rect[i]);
            i = -1;
            while (elem(printed, ++i, n));
            i--;
        }
        else
        {
            i = j - 2;
            grt = 0;
        }
    }
    free(printed);
}
void freeLista(rectangulo ***l, int n)
{
    rectangulo **rect = *l;
    int i;
    for (i = 0; i < n; i++)
    {
        free(rect[i]->supdrch);
        free(rect[i]->infizq);
        free(rect[i]);
    }
    free(rect);
}
int main(int argc, char *argv[])
{
    int n;
    FILE *f;
    int i, check, k = 0;
    rectangulo **rect;
    f = fopen(argv[argc - 1], "r");
    if (!f)
        return -1;
    check = fscanf(f, "%i", &n);
    rect = (rectangulo **)malloc(n * sizeof(rectangulo *));
    if (!rect)
    {
        fclose(f);
        return -1;
    }
    for (i = 0; i < n; i++)
    {
        rect[i] = (rectangulo *)malloc(sizeof(rectangulo));
        if (!rect[i])
        {
            fclose(f);
            return -1;
        }
        rect[i]->infizq = (punto *)malloc(sizeof(punto));
        if (!rect[i]->infizq)
        {
            fclose(f);
            return -1;
        }
        rect[i]->supdrch = (punto *)malloc(sizeof(punto));
        if (!rect[i]->supdrch)
        {
            fclose(f);
            return -1;
        }
    }
    while (k < n && check != EOF)
    {
        check = fscanf(f, "%i", &rect[k]->infizq->x);
        if (check == EOF)
            break;
        check = fscanf(f, "%i", &rect[k]->infizq->y);
        if (check == EOF)
            break;
        check = fscanf(f, "%i", &rect[k]->supdrch->x);
        if (check == EOF)
            break;
        check = fscanf(f, "%i", &rect[k]->supdrch->y);
        k++;
    }
    printOrd(rect, n);
    freeLista(&rect, n);
    fclose(f);
    return 0;
}