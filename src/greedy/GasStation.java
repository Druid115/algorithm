package greedy;

/**
 * @Author: Druid
 * @Date: 2023/5/11 17:26
 * @Description: 134. 加油站
 */
public class GasStation {

    /**
     * 思路：如果 gas 总量小于 cost 的总量，那么肯定是跑不完一圈的。如果能跑完一圈，那么需要找到出发点。
     * 出发点可以通过 gas[i] - cost[i] 累加值进行判断，若结果小于 0，说明从 0 到 i 中的任何一个位置出发，到达 i 的时候，一定会缺油。
     * 需要从 i + 1 的位置重新开始计算。证明：假设存在一个 j 的位置，[0, j] < 0，[j, i] > 0，那么在 [0, j] 的时候，就会选择新的位置。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            totalSum += gas[i] - cost[i];
            curSum += gas[i] - cost[i];
            if (curSum < 0) {
                start = i + 1;
                curSum = 0;
            }
        }

        if (totalSum < 0) {
            return -1;
        } else {
            return start;
        }
    }
}
