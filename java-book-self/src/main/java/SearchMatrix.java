/**
 * @author dongzonglei
 * @description
 * @date 2020/6/4 下午4:26
 */
public class SearchMatrix {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        int left = 0; int right = m * n - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            int value = matrix[middle / n][middle % n];
            if (target == value) {
                return true;
            } else {
                if (value < target) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        SearchMatrix matrix1 = new SearchMatrix();
        matrix1.searchMatrix(matrix, 3);
    }
}
