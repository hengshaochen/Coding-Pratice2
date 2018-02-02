class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i <= s.length() - 10; i++) {
            String cur = s.substring(i, i + 10);
            if (!map.containsKey(cur)) {
                map.put(cur, 1);
            } else {
                map.put(cur, map.get(cur) + 1);
            }
        }
        
        List<String> ans = new ArrayList<>();
        for (String cur : map.keySet()) {
            if (map.get(cur) > 1) {
                ans.add(cur);
            }
        }
        
        return ans;
    }
}

// Follow up 不限制一定要出現兩次，全都輸出，但要照字母升序排列
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i <= s.length() - 10; i++) {
            String cur = s.substring(i, i + 10);
            if (!map.containsKey(cur)) {
                map.put(cur, 1);
            } else {
                map.put(cur, map.get(cur) + 1);
            }
        }
        
        List<String> ans = new ArrayList<>();
        for (String cur : map.keySet()) {
            ans.add(cur);
        }
        Collections.sort(ans);
        return ans;
    }
}

// 節省空間: 请问这题的九章解为什么要自己写一个hash function? 会比直接用built in hash快吗? 谢谢!
// 实际上九章是写了一个encode，由于只有'A'，'C'，'G'，'T'这四个字符，所以不妨令它们分别表示0，1，2，3，那么一串长度为10的字符串就可以唯一表示成长度为10的四进制数字，encode就是将这个四进制数字转化为十进制数字。也就是说在之后的过程中，我们会使用唯一的十进制数字代替唯一的字符串。对数字的hash快于对字符串的hash，同时使用数字代替字符串可以减少空间，这就是写encode的原因。
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        // 出現只有一次存在map中即可
        HashSet<Integer> set = new HashSet<>();
        
        // 出現超過一次的存在dna
        HashSet<String> dna = new HashSet<>();
        
        
        for (int i = 0; i <= s.length() - 10; i++) {
            String cur = s.substring(i, i + 10);
            int encoded = encode(cur);
            
            if (set.contains(encoded)) {
                dna.add(cur);
            } else {
                set.add(encoded);
            }
        }
        
        List<String> ans = new ArrayList<>();
        for (String cur : dna) {
                ans.add(cur);
        }
        
        return ans;
    }
    
    public int encode(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                sum = sum * 4;
            } else if (s.charAt(i) == 'C') {
                sum = sum * 4 + 1;
            } else if (s.charAt(i) == 'G') {
                sum = sum * 4 + 2;
            } else {
                sum = sum * 4 + 3;
            }
        }
        return sum;
    }
}