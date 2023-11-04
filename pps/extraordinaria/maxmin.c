#include <stdio.h>
#include <stdlib.h>

void maxmin(float *nums, int length)
{
    float min = 0.00, max = 0.00;
    int i;
    if (length != 0)
    {
        min = nums[0];
        max = nums[0];
    }
    for (i = 0; i < length; i++)
    {
        min = min > nums[i] ? nums[i] : min;
        max = max < nums[i] ? nums[i] : max;
    }
    printf("%10.2f%10.2f\n", max, min);
}
int main(int argc, char *argv[])
{
    int i = 0;
    FILE *f;
    float *nums;
    if (argc < 2)
        return -1;
    nums = (float *)malloc(sizeof(float));
    if (!nums)
        return -1;
    f = fopen(argv[1], "r");
    if (!f)
    {
        free(nums);
        return -1;
    }
    while (nums != NULL && fscanf(f, "%f", &nums[i++]) != EOF)
        nums = (float *)realloc(nums, (i + 1) * sizeof(float));
    if (!nums)
    {
        fclose(f);
        return -1;
    }
    maxmin(nums, --i);
    free(nums);
    fclose(f);
    return 0;
}