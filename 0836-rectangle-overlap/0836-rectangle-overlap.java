class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if rec1 is completely to the left, right, below, or above rec2
        boolean isLeft  = rec1[2] <= rec2[0]; // rec1.x2 <= rec2.x1
        boolean isRight = rec1[0] >= rec2[2]; // rec1.x1 >= rec2.x2
        boolean isBelow = rec1[3] <= rec2[1]; // rec1.y2 <= rec2.y1
        boolean isAbove = rec1[1] >= rec2[3]; // rec1.y1 >= rec2.y2  <-- fixed rec2[2] to rec2[3]

        // If any of these conditions are true, they do not overlap
        return !(isLeft || isRight || isBelow || isAbove);
    }
}