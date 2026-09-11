class Solution {
    public int totalNumbers(int[] digits) {
        // Step 1: Count frequency of each digit (0 to 9)
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }

        int distinctCount = 0;

        // Step 2: Test every 3-digit even number from 100 to 998
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;         // Hundreds place
            int d2 = (num / 10) % 10;   // Tens place
            int d3 = num % 10;          // Units place

            // Count occurrences needed for the current candidate number
            int[] currentNeed = new int[10];
            currentNeed[d1]++;
            currentNeed[d2]++;
            currentNeed[d3]++;

            // Verify if the input digits can form this number
            if (count[d1] >= currentNeed[d1] &&
                count[d2] >= currentNeed[d2] &&
                count[d3] >= currentNeed[d3]) {
                distinctCount++;
            }
        }

        return distinctCount;
    }
}