class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        /*
        [0, 1, 3, 14, 18, 19] , target = 12
         0  1  2  3  4   5
               m              letters[mid] <= target --> go to right
               s  m      e    letters[mid] > target --> go to left
               s  e
        */
        int start = 0;
        int end = letters.length - 1;
        
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (letters[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (letters[start] > target) {
            return letters[start];
        } else if (letters[end] > target) {
            return letters[end];
        } else {
            return letters[0];
        }
    }
}