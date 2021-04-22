import java.util.HashSet;
import java.util.Set;

/**
 * @Classname NumDecoding
 * @Description
 * @Date 2021/4/21 21:45
 * @Created by shuaif
 */
public class NumDecoding {
    /**
     * 解码方法：
     *  A-Z 映射成 1-26的数字，给定输入是一个数字组成的字符串，输出可合法解码成字母的数量。
     *
     * 分析：
     *  最多只能两个数字组成一个字母，因此，针对两个连续字符可能解码成0,1,2三种情况，进行分类讨论
     *
     * 题解：
     *  动态规划 （大部分问什么设什么为状态）
     *  状态定义：dp[i] 为 s 有多少解码方法，返回的结果为dp[-1]
     *  分析：
     *      s[i]不合法:
     *          s[i] == 0 时, s[i-1] + s[i] 不合法 e.g 00,30 返回 0
     *                      s[i-1] + s[i] 合法 e.g 10,20 解码固定：合并解码，dp[i] = dp[i-2]
     *      s[i]合法:
     *          s[i] != 0 时, s[i-1] + s[i] 不合法 e.g 31,27 解码固定: 单独解码，dp[i] = dp[i-1]
     *                      s[i-1] + s[i] 合法 e.g 13,23 可单独解码可合并解码， dp[i] = dp[i-2] + dp[i-1]
     *  状态转移方程：
     *      dp[i] = dp[i-1]  when s[i] in 1-9 , s[i-1] in 3-9 or s[i-1:i+1] > 26
     *            = dp[i-2]  when s[i] in 0 , s[i-1] in 0,3-9
     *            = dp[i-1] + dp[i-2] when s[i] in 1-9, s[i-1] in 1-2
     * @param s -数字串
     * @return 可合法解码的个数
     */
    public int numDecodings(String s){
        if (s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;

        Set<String> legalStrs = new HashSet<>();
        // 初始化 合法字符串
        for (int i = 1; i < 27; i++) {
            legalStrs.add(i+"");
        }
        int[] dp = new int[s.length()];
        dp[0] = 1;
        // 确定状态1
        String string_index_1 = s.substring(1,2);
        String merge_01 = s.substring(0,2);
        if (!legalStrs.contains(string_index_1)) {
            if (!legalStrs.contains(merge_01)) return 0;
            dp[1] = 1;
        } else {
            if (!legalStrs.contains(merge_01)) dp[1] = 1;
            else dp[1] = 2;
        }
        // 状态转移
        for (int i = 2; i < s.length(); i++) {
            String s_i = s.substring(i,i+1);
            String merge_i = s.substring(i-1,i+1);
            if (!legalStrs.contains(s_i)) { // s[i] == 0
                if (!legalStrs.contains(merge_i)) return 0; // 30, 00
                dp[i] = dp[i-2];
            } else { // s[i] != 0
                if (!legalStrs.contains(merge_i)) { // 27
                    dp[i] = dp[i-1];
                    continue;
                }
                dp[i] = dp[i-2] + dp[i-1];
            }
        }
        return dp[dp.length-1];
    }

}
