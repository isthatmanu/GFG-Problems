class Solution {
    public int maxAmount(int[] arr, int k) {
        // code here
        long maxProfit = 0;
        long MOD = 1000000007;

        // Create a Max-Heap to keep track of the maximum ticket prices
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Insert all ticket quantities into the heap
        for (int tickets : arr) {
            maxHeap.add(tickets);
        }

        // Sell k tickets greedily
        for (int i = 0; i < k; i++) {
            // If the heap is empty, no more tickets can be sold
            if (maxHeap.isEmpty()) {
                break;
            }

            // Get the highest available ticket price
            int currentPrice = maxHeap.poll();

            // Add the price to total profit and apply modulo
            maxProfit = (maxProfit + currentPrice) % MOD;

            // If the seller still has tickets left, push the updated price back into the heap
            if (currentPrice - 1 > 0) {
                maxHeap.add(currentPrice - 1);
            }
        }

        return (int) maxProfit;
    }
}