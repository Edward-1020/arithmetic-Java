package SET;

import java.util.TreeSet;

/**
 * 统计摩斯密码
 */
public class Solution {
    public int uniqueMorseRepresentations (String[] words) {
        String[] codes = {
                ".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."
        };


        TreeSet<String> set = new TreeSet<>();
        for (String word: words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++)
                //  word.charAt(i) - 'a' 表示asic码相对于a的偏移量
                //  根据偏移量找到摩斯码
                res.append(codes[word.charAt(i) - 'a']);
            set.add(res.toString());
        }

        return set.size();
    }
}
