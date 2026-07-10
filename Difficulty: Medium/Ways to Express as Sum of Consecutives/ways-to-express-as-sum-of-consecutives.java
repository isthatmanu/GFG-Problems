class Solution {
    public int getCount(int n) {
        // code here
        
        while (n % 2 == 0) {
            n /= 2;
        }

        int count = 1; // Total odd divisors count
        // Step 2: Find divisors of the remaining odd number
        for (int i = 3; i * i <= n; i += 2) {
            int p = 0;
            while (n % i == 0) {
                p++;
                n /= i;
            }
            count *= (p + 1);
        }
        
        if (n > 1) {
            count *= 2;
        }

        // Step 3: Subtract 1 (the divisor 1 doesn't count as 2+ terms)
        return count - 1;
    
    }
};