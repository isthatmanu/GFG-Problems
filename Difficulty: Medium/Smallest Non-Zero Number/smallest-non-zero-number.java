class Solution {
    public int find(int[] arr) {
        // code here
        // Edge case: if array is null or empty, 0 is the smallest valid value
        if (arr == null || arr.length == 0) {
            return 0;
        }

        long minX = 0; // Tracks the minimum value required at the current step

        // Process the array from right to left (index n-1 down to 0)
        for (int i = arr.length - 1; i >= 0; i--) {
            // (arr[i] + minX + 1) / 2 acts as the integer ceiling function.
            // We use a long type for minX to safely avoid any 32-bit integer overflow.
            minX = (arr[i] + minX + 1) / 2;
        }

        // Cast the final result back to an int as specified by the method signature
        return (int) minX;
    

    }
}
