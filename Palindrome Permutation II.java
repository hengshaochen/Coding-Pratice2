class Solution {
    public List<String> generatePalindromes(String s) {
        // 思路： Step1: 構成回文一定要是只有一個奇數 例如aa c bb 這樣可以 aa c z bb 這樣不行
        // 因此先用HashMap統計字母出現次數. 若奇數超過一次就不可能構成回文
        // Step2: 接著把偶數的都放到list中, 奇數的放到mid中, 用backtrack(DFS)構造答案
        // 偶數的只要放一半, 後面可以直接取reverse
        List<String> ans = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        int odd = 0;
        for (char cur : s.toCharArray()) {
            if (!map.containsKey(cur)) {
                map.put(cur, 0);
            }
            map.put(cur, map.get(cur) + 1);
            
            // 若是奇數就+1, 偶數就-1, 若是aa, 第一次是-1, 後面會還原成0
            odd += map.get(cur) % 2 != 0 ? 1 : -1;
        }
        if (odd > 1) {
            return ans;
        }
        
        // 把偶數的所有元素的個數一半都放到list中
        String mid = "";
        List<Character> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char cur_char = entry.getKey();
            int cur_repeat_time = entry.getValue();
            if (cur_repeat_time % 2 != 0) {
                // 奇數次的字元放到mid, 不能直接continue掉, 因為會有這種case: aaa
                mid = mid + cur_char;
            }
            for (int i = 0; i < cur_repeat_time / 2; i++) {
                list.add(cur_char);
            }
        }
        helper(list, mid, new boolean[list.size()], new StringBuilder(), ans);
        return ans;
    }
    public void helper(List<Character> list, String mid, boolean[] used, StringBuilder sb, List<String> ans) {
        // base case, aacbb 在list中是ab, 只要接到長度是ab就可以了
        if (sb.length() == list.size()) {
            // ab + c + ba = abcba 或是 ba + c + ab = bacab
            ans.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse(); // 要還原 例如sb = "ab" , 為了構成abcba, 會返轉成"ba" 要轉回原本的ab
            return;
        }
        
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1]) {
                continue;
            }
            if (!used[i]) {
                used[i] = true;
                sb.append(list.get(i));
                helper(list, mid, used, sb, ans);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}