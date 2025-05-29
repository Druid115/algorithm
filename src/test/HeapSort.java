package test;

/**
 * @Author: DingRD
 * @Date: 2024/11/27 10:09
 * @Description:
 */
public class HeapSort {

    public void heapSort(int[] arr) {
        buildHeap(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            int tmp = arr[i];
            arr[i] = arr[0];

        }
    }

    public void buildHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length - 1);
        }
    }

    private void adjustHeap(int[] arr, int start, int end) {
        int tmp = arr[start];
        for (int i = start * 2 + 1; i <= end; i = i * 2 + 1) {
            if (i < end && arr[i] < arr[i + 1]) {
                i = i + 1;
            }
            if (arr[i] <= tmp) {
                break;
            }
            arr[start] = arr[i];
            arr[i] = tmp;
            start = i;
        }
    }
}
