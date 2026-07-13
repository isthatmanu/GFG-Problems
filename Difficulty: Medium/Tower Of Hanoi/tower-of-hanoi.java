class Solution {
    public int towerOfHanoi(int n, int from, int to, int aux) {
        // code here
        
        if (n == 0)
            return 0;

        int left = towerOfHanoi(n - 1, from, aux, to);

        //System.out.println("move disk " + n + " from rod " + from + " to rod " + to);

        int right = towerOfHanoi(n - 1, aux, to, from);

        return left + 1 + right;
    
    
    }
}
