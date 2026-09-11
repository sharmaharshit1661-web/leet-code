class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        // If needle is longer than haystack, it can never match
        if (m > n) {
            return -1;
        }

        // Only iterate up to n - m
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            
            // Check character by character
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }

            // If we matched all characters of needle
            if (j == m) {
                return i;
            }
        }

        return -1;
    }
}