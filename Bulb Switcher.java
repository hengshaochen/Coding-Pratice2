// Brute Force Time: O(n^2) , Space: O(n)
class Solution {
    public int bulbSwitch(int n) {
        boolean[] bulbStatus = new boolean[n+1];
        for (int i = 1; i <= n; i++) { // true → on, false → of
            bulbStatus[i] = true;
        }

        for (int i = 2; i <= n; i++) { // number of round, 從第二輪開始，因為第一輪已經初始化了
            for (int j = 1; j <= n; j++) {
                bulbStatus[j] = j % i == 0 ? !bulbStatus[j] : bulbStatus[j];
            }
        }

        int numberOfOn = 0;
        for (int i = 1; i <= n; i++) {
            if (bulbStatus[i]) {
                numberOfOn++;
            }
        }

        return numberOfOn;
    }

}

// O(1)
class Solution {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}