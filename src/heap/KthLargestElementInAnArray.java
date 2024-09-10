package heap;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * @Author: Druid
 * @Date: 2023/9/20 11:21
 * @Description: 215. 数组中的第K个最大元素
 */
public class KthLargestElementInAnArray {

    /**
     * 思路：使用优先队列，将数组中的元素一次添加到队列中，控制队列中的元素始终为 k 个。遍历完成后，队列中的第一个元素即为结果。
     */
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }

    private final Random random = new Random(System.currentTimeMillis());

    /**
     * 思路：快速排序。
     */
    public int findKthLargest2(int[] nums, int k) {
        int target = nums.length - k;
        int left = 0;
        int right = nums.length - 1;

        while (true) {
            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == target) {
                return nums[pivotIndex];
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(nums, left, randomIndex);

        int pivot = nums[left];
        // [left + 1, le) <= pivot
        // (ge, right] >= pivot
        int le = left + 1;
        int ge = right;
        while (true) {
            while (le <= ge && nums[le] < pivot) {
                le++;
            }
            while (le <= ge && nums[ge] > pivot) {
                ge--;
            }

            if (le >= ge) {
                break;
            }
            swap(nums, le, ge);
            le++;
            ge--;
        }
        swap(nums, left, ge);
        return ge;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    /**
     * 思路：手动建大顶堆。
     */
    public int findKthLargest3(int[] nums, int k) {
        buildHeap(nums);
        // 从最后一个元素开始对序列进行调整，不断缩小调整的范围直到第 K 个元素
        for (int i = nums.length - 1; i > nums.length - k; i--) {
            swap(nums, 0, i);
            adjustHeap(nums, 0, i);
        }
        return nums[0];
    }

    private void buildHeap(int[] nums) {
        // 从最后一个非叶节点开始
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }
    }

    private void adjustHeap(int[] nums, int i, int length) {
        // 从该节点的左子节点开始调整
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 左右两子节点中选择较大者
            if (k + 1 < length && nums[k] < nums[k + 1]) {
                k++;
            }
            if (nums[k] > nums[i]) {
                swap(nums, i, k);
                i = k;
            } else {
                break;
            }
        }
    }
}
