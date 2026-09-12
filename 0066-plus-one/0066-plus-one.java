class Solution {
    public int[] plusOne(int[] digits) {
        // Traverse backwards starting from the least significant digit
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // No more carry needed, return immediately
            }
            // If the digit is 9, it rolls over to 0, carry continues
            digits[i] = 0;
        }

        // If all digits were 9 (e.g., [9, 9, 9] -> [1, 0, 0, 0])
        int[] result = new int[digits.length + 1];
        result[0] = 1; // Remaining elements are already 0 by default in Java
        return result;
    }
}
