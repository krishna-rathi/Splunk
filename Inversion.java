#include <bits/stdc++.h>
using namespace std;

long  long _mergeSort(long long arr[], int temp[], int left, int right);
long  long merge(long long arr[], int temp[], int left, int mid, int right);

/* This function sorts the input array and returns the
number of inversions in the array */
long  long mergeSort(long long  arr[], int array_size)
{
    int temp[array_size];
    return _mergeSort(arr, temp, 0, array_size - 1);
}

/* An auxiliary recursive function that sorts the input array and
returns the number of inversions in the array. */
long  long _mergeSort(long long arr[], int temp[], int left, int right)
{
    long  long mid, inv_count = 0;
    if (right > left) {
        /* Divide the array into two parts and
        call _mergeSortAndCountInv()
        for each of the parts */
        mid = (right + left) / 2;

        /* Inversion count will be sum of
        inversions in left-part, right-part
        and number of inversions in merging */
        inv_count += _mergeSort(arr, temp, left, mid);
        inv_count += _mergeSort(arr, temp, mid + 1, right);

        /*Merge the two parts*/
        inv_count += merge(arr, temp, left, mid + 1, right);
    }
    return inv_count;
}

/* This funt merges two sorted arrays
and returns inversion count in the arrays.*/
long  long merge(long long arr[], int temp[], int left,
          int mid, int right)
{
    int i, j, k;
    long  long inv_count = 0;

    i = left; /* i is index for left subarray*/
    j = mid; /* j is index for right subarray*/
    k = left; /* k is index for resultant merged subarray*/
    while ((i <= mid - 1) && (j <= right)) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        }
        else {
            temp[k++] = arr[j++];

            /* this is tricky -- see above
            explanation/diagram for merge()*/
            inv_count = inv_count + (mid - i);
        }
    }

    /* Copy the remaining elements of left subarray
(if there are any) to temp*/
    while (i <= mid - 1)
        temp[k++] = arr[i++];

    /* Copy the remaining elements of right subarray
(if there are any) to temp*/
    while (j <= right)
        temp[k++] = arr[j++];

    /*Copy back the merged elements to original array*/
    for (i = left; i <= right; i++)
        arr[i] = temp[i];

    return inv_count;}

int main(){

  
int n;
cin>>n;
long long a[n];
for(int i=0;i<n;i++){
cin>>a[i];}
 long long ans = mergeSort(a, n);
    cout  << ans; }
