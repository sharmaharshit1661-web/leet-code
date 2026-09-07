class Solution {
    public String convert(String s, int numRows) {
        // Base case: 1 row or not enough characters to zigzag
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // Initialize a StringBuilder for each row
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        // Traverse the string and place characters into rows
        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);

            // Reverse direction if we hit the top or bottom row
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            currentRow += goingDown ? 1 : -1;
        }

        // Concatenate all rows into a single string
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}
