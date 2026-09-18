class Solution {
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int left = 1;
        int right = x / 2;
        int ans = 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == x) {
                return mid;
            } else if (square < x) {
                ans = mid;         // mid is a valid candidate for floor(sqrt(x))
                left = mid + 1;    // search for a larger candidate
            } else {
                right = mid - 1;   // mid is too large
            }
        }

        return ans;
    }
}