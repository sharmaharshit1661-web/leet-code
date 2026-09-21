class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];
        
        for (int num : nums) {
            int currentVal = num % k;
            long[] nextDp = new long[k];
            
            // Start a new single-element subarray
            nextDp[currentVal]++;
            
            // Extend existing subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRemainder = (r * currentVal) % k;
                    nextDp[newRemainder] += dp[r];
                }
            }
            
            // Accumulate counts into the global answer
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }
            
            dp = nextDp;
        }
        
        return result;
    }
}