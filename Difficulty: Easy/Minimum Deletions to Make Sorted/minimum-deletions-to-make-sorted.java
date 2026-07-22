import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int minDeletions(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int n = arr.length;
        ArrayList<Integer> tails = new ArrayList<>();
        
        for (int num : arr) {
            // Find insertion index using binary search
            int idx = Collections.binarySearch(tails, num);
            
            // If num is not found, convert to insertion point
            if (idx < 0) {
                idx = -(idx + 1);
            }
            
            // Append or replace element
            if (idx == tails.size()) {
                tails.add(num);
            } else {
                tails.set(idx, num);
            }
        }
        
        // Minimum deletions = total length - LIS length
        return n - tails.size();
    }
}
