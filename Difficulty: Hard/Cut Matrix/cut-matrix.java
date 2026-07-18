class Solution {
    public int findWays(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int mod = (int) 1e9 + 7;

        // 1. Build 2D Suffix Sum matrix to count ones in any submatrix (r, c) to (n-1, m-1)
        int[][] suf = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                suf[i][j] = suf[i + 1][j] + suf[i][j + 1] - suf[i + 1][j + 1] + matrix[i][j];
            }
        }

        // dp[r][c][p] = total ways to cut submatrix starting at (r,c) into p pieces
        int[][][] dp = new int[n][m][k + 1];

        // Base Case: 1 piece (no cuts needed). Valid if the submatrix contains at least one 1.
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                dp[r][c][1] = suf[r][c] > 0 ? 1 : 0;
            }
        }

        // 2. Dynamic Programming for 2 up to k pieces
        for (int p = 2; p <= k; p++) {
            
            // Precompute suffix sums of the previous DP state over rows for each column
            int[][] sufRow = new int[n + 1][m];
            for (int c = 0; c < m; c++) {
                for (int r = n - 1; r >= 0; r--) {
                    sufRow[r][c] = (sufRow[r + 1][c] + dp[r][c][p - 1]) % mod;
                }
            }

            // Precompute suffix sums of the previous DP state over columns for each row
            int[][] sufCol = new int[n][m + 1];
            for (int r = 0; r < n; r++) {
                for (int c = m - 1; c >= 0; c--) {
                    sufCol[r][c] = (sufCol[r][c + 1] + dp[r][c][p - 1]) % mod;
                }
            }

            // Compute DP values for pieces state 'p'
            for (int r = n - 1; r >= 0; r--) {
                for (int c = m - 1; c >= 0; c--) {
                    if (suf[r][c] == 0) continue; // No ones available to make a valid cut

                    long res = 0;

                    // --- Optimized Horizontal Cuts ---
                    // Find the first row 'i' where cutting leaves at least one '1' in the upper portion.
                    // This is equivalent to finding the smallest i > r such that suf[i][c] < suf[r][c].
                    int nextRow = r + 1;
                    while (nextRow < n && suf[nextRow][c] == suf[r][c]) {
                        nextRow++;
                    }
                    if (nextRow < n) {
                        res = (res + sufRow[nextRow][c]) % mod;
                    }

                    // --- Optimized Vertical Cuts ---
                    // Find the first column 'j' where cutting leaves at least one '1' in the left portion.
                    // This is equivalent to finding the smallest j > c such that suf[r][j] < suf[r][c].
                    int nextCol = c + 1;
                    while (nextCol < m && suf[r][nextCol] == suf[r][c]) {
                        nextCol++;
                    }
                    if (nextCol < m) {
                        res = (res + sufCol[r][nextCol]) % mod;
                    }

                    dp[r][c][p] = (int) res;
                }
            }
        }

        return dp[0][0][k];
    }
}
