class Solution {
    public int removeDuplicates(int[] nums) {
        // Edge case: if the array has 0 or 1 element, it already has no duplicates
        if (nums.length == 0) return 0;

        // k points to the index of the last confirmed unique element
        int k = 0;

        // Iterate through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // Found a new distinct value
            if (nums[i] != nums[k]) {
                k++;                // Move to next available position
                nums[k] = nums[i];  // Place the unique element
            }
        }

        // Return the number of unique elements (k is 0-indexed, so count is k + 1)
        return k + 1;
    }
}