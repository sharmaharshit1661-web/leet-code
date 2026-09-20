class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 'z' gives 1, 'y' gives 2, ..., 'a' gives 26
            int reversedAlphabetPos = 'z' - ch + 1;
            int stringPos = i + 1; // 1-indexed

            totalDegree += reversedAlphabetPos * stringPos;
        }

        return totalDegree;
    }
}