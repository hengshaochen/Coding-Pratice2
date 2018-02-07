// Original 沒優化，就照乘
public class Solution {
    /*
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int m = B[0].length;
        int t = B.length;
        int[][] C = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < t; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}

// 優化：交換第二層跟第三層循環的順序A[i][k]如果等於0，就整個剪枝掉，後面就等於少了一層循環！
// 然後B[k][j]也必須要是0才有必要乘，不然其實乘法很花時間
public class Solution {
    /*
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int m = B[0].length;
        int t = B.length;
        int[][] C = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < t; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < m; j++) {
                        if (B[k][j] != 0) {
                            C[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
        }
        return C;
    }
}

// 前處理優化：看看就好，面試不一定要說這個
public class Solution {
    /**
     * @param A a sparse matrix
     * @param B a sparse matrix
     * @return the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // Write your code here
        int n = A.length;
        int m = B[0].length;
        int t = A[0].length;
        int[][] C = new int[n][m];

        List<List<Integer>> val = new ArrayList<>();
        List<List<Integer>> col = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            val.add(new ArrayList<>());
            col.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                if (B[i][j] != 0) {
                    col.get(i).add(j);
                    val.get(i).add(B[i][j]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < t; k++) {
                // 第二層優化
                if (A[i][k] != 0) {
                    // 第三層優化
                    for (int p = 0; p < col.get(k).size(); p++) {
                        int j = col.get(k).get(p);
                        int v = val.get(k).get(p);
                        C[i][j] += A[i][k] * v;
                    }
                }
            }
        }
        return C;
    }
}

