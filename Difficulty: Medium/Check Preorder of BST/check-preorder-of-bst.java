import java.util.List;
import java.util.Stack;

class Solution {
    public boolean canRepresentBST(List<Integer> arr) {
        // Stack to store ancestral root nodes
        Stack<Integer> stack = new Stack<>();
        
        // Initialize the lower bound for elements in the right subtree
        int lowerBound = Integer.MIN_VALUE;
        
        // Traverse the given list
        for (int num : arr) {
            // If any node is smaller than the lower bound, it violates the BST rule
            if (num < lowerBound) {
                return false;
            }
            
            // If num is greater than the stack's top, we are moving to a right subtree.
            // Pop elements and update the lower bound to the last popped element.
            while (!stack.isEmpty() && stack.peek() < num) {
                lowerBound = stack.pop();
            }
            
            // Push the current element onto the stack
            stack.push(num);
        }
        
        return true;
    }
}
