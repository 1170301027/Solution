import java.util.ArrayList;
import java.util.List;

/**
 * @Classname NumDecoding
 * @Description
 * @Date 2021/4/21 21:45
 * @Created by shuaif
 */
public class NumDecoding {
    public int numDecodings(String s){
        int count = 0;
        List<Integer> index_zeros = new ArrayList<>(); // 标记0的位置
        for (int i = 0;i < s.length(); i++){
            char cur = s.charAt(i);
            if (cur == '0') {
                if (i - 1 < 0 || (s.charAt(i-1) != '1' && s.charAt(i-1) != 2)) { // 确定 首位0 或 00,30等情况
                    return 0;
                }
                index_zeros.add(i);
            }
        }
        count++;
        int last_index = 0; // 划分字符串的起始位置
        for (Integer index_zero : index_zeros) {
            if (last_index == index_zero - 1) { // 空串
                last_index = index_zero + 1;
                continue;
            }
            count *= noZerof(s.substring(last_index,index_zero-1));
            last_index = index_zero + 1;
        }
        return count;
    }

    public int noZerof(String s) {
        int count = 1;
        for (int i = 0; i < s.length() - 1 ; i++) {
            char front = s.charAt(i);
            char rear = s.charAt(i + 1);
            if (front == '1') {
                count ++ ;
            } else if (front == '2' && (true)) {

            }
        }
        return count;
    }
}
