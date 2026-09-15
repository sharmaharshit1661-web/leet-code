class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastEnd = -1; // End index of the previous chosen palindrome

        for (int i = 0; i < n; i++) {
            // Check odd-length palindrome centered around i
            // Check even-length palindrome centered between i and i + 1
            // We only need to look for palindromes of length k or k + 1
            
            // Check length k
            int l1 = i - (k / 2);
            int r1 = (k % 2 != 0) ? i + (k / 2) : i + (k / 2) - 1;
            if (isValidPalindrome(s, l1, r1) && l1 > lastEnd) {
                count++;
                lastEnd = r1;
                continue;
            }

            // Check length k + 1
            int l2 = i - (k / 2);
            int r2 = (k % 2 != 0) ? i + (k / 2) + 1 : i + (k / 2);
            if (isValidPalindrome(s, l2, r2) && l2 > lastEnd) {
                count++;
                lastEnd = r2;
            }
        }

        return count;
    }

    private boolean isValidPalindrome(String s, int left, int right) {
        if (left < 0 || right >= s.length()) {
            return false;
        }
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}