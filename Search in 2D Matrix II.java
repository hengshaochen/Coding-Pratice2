class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        // 每次看左下角的元素, 若target < 當前元素, row往上, 若target > 當前元素, col往右
        // worst case是左下移動到右上 O(M+N)
        int row = matrix.length - 1;
        int col = 0;
        
        while (row >= 0 && col <= matrix[0].length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                row--;
            } else {
                col++;
            }
        }
        return false;
    }
}