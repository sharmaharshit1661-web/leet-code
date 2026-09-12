import java.util.*;

class Solution {
    // Helper class to represent intervals with original index
    static class Interval {
        int l, r, weight, id;
        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    // Helper class to represent DP state
    static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }

        // Compare two states: greater weight is better, then lexicographically smaller indices
        static State getBetter(State a, State b) {
            if (a == null) return b;
            if (b == null) return a;
            if (a.weight > b.weight) return a;
            if (b.weight > a.weight) return b;

            // Tie-break: lexicographical comparison of indices
            int sizeA = a.indices.size();
            int sizeB = b.indices.size();
            int minSize = Math.min(sizeA, sizeB);
            for (int i = 0; i < minSize; i++) {
                int cmp = Integer.compare(a.indices.get(i), b.indices.get(i));
                if (cmp != 0) {
                    return cmp < 0 ? a : b;
                }
            }
            return sizeA <= sizeB ? a : b;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> iv = intervalsList.get(i);
            intervals[i] = new Interval(iv.get(0), iv.get(1), iv.get(2), i);
        }

        // Sort intervals by their end points r (and then l)
        Arrays.sort(intervals, (a, b) -> {
            if (a.r != b.r) return Integer.compare(a.r, b.r);
            return Integer.compare(a.l, b.l);
        });

        // dp[i][c] stores optimal state using a subset of first i intervals picking c intervals
        State[][] dp = new State[n + 1][5];

        for (int i = 1; i <= n; i++) {
            Interval cur = intervals[i - 1];

            // Binary search to find the largest index j such that intervals[j-1].r < cur.l
            int low = 0, high = i - 1, prev = 0;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (mid == 0) {
                    low = mid + 1;
                    continue;
                }
                if (intervals[mid - 1].r < cur.l) {
                    prev = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            for (int c = 1; c <= 4; c++) {
                // Option 1: Do not include current interval
                State best = dp[i - 1][c];

                // Option 2: Include current interval
                if (c == 1) {
                    List<Integer> list = new ArrayList<>();
                    list.add(cur.id);
                    State take = new State(cur.weight, list);
                    best = State.getBetter(best, take);
                } else {
                    State prevState = dp[prev][c - 1];
                    if (prevState != null) {
                        List<Integer> list = new ArrayList<>(prevState.indices);
                        // Insert cur.id while keeping the indices list sorted
                        list.add(cur.id);
                        Collections.sort(list);
                        State take = new State(prevState.weight + cur.weight, list);
                        best = State.getBetter(best, take);
                    }
                }

                dp[i][c] = best;
            }
        }

        // Find the overall best state among choosing 1, 2, 3, or 4 intervals
        State bestOverall = null;
        for (int c = 1; c <= 4; c++) {
            bestOverall = State.getBetter(bestOverall, dp[n][c]);
        }

        int[] result = new int[bestOverall.indices.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = bestOverall.indices.get(i);
        }
        return result;
    }
}