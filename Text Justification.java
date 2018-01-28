class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int index = 0;
        
        while (index < words.length) {
            int count = words[index].length();  // 當前行的字元數
            int last = index + 1;   // 當前指向要處理的word
            
            
            while (last < words.length) {
                if (words[last].length() + count + 1 > maxWidth) {
                    // 超過螢幕寬度 break, +1是空格
                    break;
                }
                count = count + words[last].length() + 1;
                last++;
            }
            
            
            StringBuilder sb = new StringBuilder();
            sb.append(words[index]);
            int diff = last - index - 1;   // 這行有幾個空隙 第一行diff = 2
            
            
            if (last == words.length || diff == 0) {
                // CASE1: words字典中最後一個單詞 或是該行的最後一個字 --> 沒有空隙
                for (int i = index + 1; i < last; i++) {
                    sb.append(" ");
                    sb.append(words[i]);
                }
                for (int i = sb.length(); i < maxWidth; i++) {
                    // 如果字都加入了 但還沒有到L, 在尾巴填充空格
                    // "justification.  "
                    sb.append(" ");
                }
            } else {
                // CASE2: "This    is    an",
                int spaces = (maxWidth - count) / diff; // 例如上面是(16 - 10) / 2 = 3, 每個空隙都要額外加入3空格
                int r = (maxWidth - count) % diff; // 例如"example  of text", 兩個縫隙, 第一個縫比第一個縫要多一個空格, (16 - 15) % 2 = 0    // ex: 如果count = 7, 16-7=9 , 如果把9分給兩個縫隙 第一個縫隙拿到5個空格, 第二個縫隙拿到4個空格
                for (int i = index + 1; i < last; i++) {
                    for (int k = spaces; k > 0; k--) {
                        sb.append(" ");
                    }
                    // 例如上面的例子 上面分配了4個空格, 還要多分配一個變5個
                    if (r > 0) {
                        sb.append(" ");
                        r--;
                    }
                    sb.append(" ");
                    sb.append(words[i]);
                }
            }
            
            res.add(sb.toString());
            index = last;
        }
        return res;
    }
}