
class Solution {
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Helper function to find Least Common Multiple (LCM)
    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b; // Divided first to prevent overflow
    }
    int minOperations(int[] b) {
        // code here
        int n = b.length;
        boolean[] visited = new boolean[n];
        long totalLcm = 1;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                long cycleLength = 0;
                int current = i;

                // Traverse the cycle
                while (!visited[current]) {
                    visited[current] = true;
                    
                    // If b[] values are 1-based, subtract 1. 
                    // If b[] values are already 0-based, change this to: current = b[current];
                    current = b[current] - 1; 
                    
                    cycleLength++;
                }

                // Update the running LCM
                if (cycleLength > 0) {
                    totalLcm = lcm(totalLcm, cycleLength);
                }
            }
        }

        // Return as an int (or cast based on target return signature)
        return (int) totalLcm;

    }
};