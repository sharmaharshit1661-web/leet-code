class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        int length = 0;

        // Step 1: Skip all trailing spaces from the end
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Step 2: Count the characters of the last word
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }

        return length;
    }
}