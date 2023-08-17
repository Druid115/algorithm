/**
 * @Author: Druid
 * @Date: 2023/5/8 14:22
 * @Description: 169. 多数元素
 */
public class MajorityElement {

    /**
     * 思路：Boyer-Moore 投票算法
     * 如果候选人不是 res，则 res 会和其他非候选人一起反对候选人，所以候选人一定会下台（count < 0 时发生换届选举）。
     * 如果候选人是 res，则 res 会支持自己，其他候选人会反对，同样因为 res 票数超过一半，所以 res 一定会成功当选。
     */
    public int majorityElement(int[] nums) {
        int count = 1;
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == res) {
                count++;
            } else {
                count--;
                if (count < 0) {
                    res = nums[i];
                    count = 1;
                }
            }
        }
        return res;
    }
}
