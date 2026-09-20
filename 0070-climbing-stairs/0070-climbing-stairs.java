class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        // Base cases:
        // 1 step  -> 1 way  (1)
        // 2 steps -> 2 ways (1+1, 2)
        int prev2 = 1;
        int prev1 = 2;

        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}