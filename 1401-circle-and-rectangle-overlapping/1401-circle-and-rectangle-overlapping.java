class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find the point on/inside the rectangle closest to the circle's center
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        // Calculate the vector components from the closest point to the circle center
        int dx = xCenter - closestX;
        int dy = yCenter - closestY;

        // Check if the squared distance is within the squared radius
        return dx * dx + dy * dy <= radius * radius;
    }
}