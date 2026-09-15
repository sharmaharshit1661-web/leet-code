class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {
            int hLeft = height[left];
            int hRight = height[right];
            
            // Calculate current container dimensions
            int currentHeight = Math.min(hLeft, hRight);
            int currentWidth = right - left;
            int currentArea = currentHeight * currentWidth;
            
            maxWater = Math.max(maxWater, currentArea);

            // Move the pointer pointing to the shorter line inward.
            // Skipping lines shorter than or equal to currentHeight speeds up execution.
            if (hLeft < hRight) {
                while (left < right && height[left] <= currentHeight) {
                    left++;
                }
            } else {
                while (left < right && height[right] <= currentHeight) {
                    right--;
                }
            }
        }

        return maxWater;
    }
}