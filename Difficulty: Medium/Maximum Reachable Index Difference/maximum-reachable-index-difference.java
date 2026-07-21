import java.util.Arrays;

class Solution {
    public int maxIndexDifference(String s) {
        int n = s.length();
        int[] bestReach = new int[26];
        Arrays.fill(bestReach, -1);
        
        int ans = -1;

        // Traverse the string from right to left
        for (int i = n - 1; i >= 0; i--) {
            int c = s.charAt(i) - 'a';
            
            // By default, a character can at least reach its own index
            int currentReach = i; 
            
            // If the next consecutive alphabet letter exists to its right,
            // inherit its maximum reachable rightmost index.
            if (c < 25 && bestReach[c + 1] != -1) {
                currentReach = bestReach[c + 1];
            }
            
            // Record the furthest index reachable for character 'c'
            bestReach[c] = Math.max(bestReach[c], currentReach);
            
            // If the character is 'a', calculate the maximum index difference
            if (c == 0) {
                ans = Math.max(ans, currentReach - i);
            }
        }

        return ans;
    }
}
