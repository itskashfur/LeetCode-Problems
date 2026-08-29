/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int val;
    int idx;
} Element;

// Comparator to sort elements by value
int compareElements(const void *a, const void *b) {
    Element *e1 = (Element *)a;
    Element *e2 = (Element *)b;
    return (e1->val > e2->val) - (e1->val < e2->val);
}

// Comparator to sort integer indices
int compareInts(const void *a, const void *b) {
    return (*(int *)a - *(int *)b);
}

int* lexicographicallySmallestArray(int* nums, int numsSize, int limit, int* returnSize) {
    Element* sorted = (Element*)malloc(numsSize * sizeof(Element));
    for (int i = 0; i < numsSize; i++) {
        sorted[i].val = nums[i];
        sorted[i].idx = i;
    }

    // Sort element pairs by value
    qsort(sorted, numsSize, sizeof(Element), compareElements);

    int* result = (int*)malloc(numsSize * sizeof(int));
    int* indices = (int*)malloc(numsSize * sizeof(int));
    
    int i = 0;
    while (i < numsSize) {
        int j = i;
        int groupSize = 0;

        // Collect elements belonging to the same component
        while (j < numsSize && (j == i || sorted[j].val - sorted[j - 1].val <= limit)) {
            indices[groupSize++] = sorted[j].idx;
            j++;
        }

        // Sort indices for the current group
        qsort(indices, groupSize, sizeof(int), compareInts);

        // Map smallest values to smallest indices
        for (int k = 0; k < groupSize; k++) {
            result[indices[k]] = sorted[i + k].val;
        }

        i = j; // Advance to next group
    }

    free(sorted);
    free(indices);

    *returnSize = numsSize;
    return result;
}