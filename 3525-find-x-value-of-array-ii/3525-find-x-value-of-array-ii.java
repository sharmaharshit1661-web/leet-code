class Solution {
    static class Node {
        int prod;
        int[] count;

        Node(int k) {
            this.prod = 1;
            this.count = new int[k];
        }
    }

    private int n;
    private int k;
    private Node[] tree;

    // Merge two segment tree nodes: left and right
    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node res = new Node(k);
        res.prod = (int) (((long) left.prod * right.prod) % k);

        // Copy counts from the left subtree
        for (int r = 0; r < k; r++) {
            res.count[r] += left.count[r];
        }

        // Add counts from the right subtree, shifted by the product of the left subtree
        for (int r = 0; r < k; r++) {
            int newRem = (int) (((long) left.prod * r) % k);
            res.count[newRem] += right.count[r];
        }

        return res;
    }

    // Build the initial segment tree
    private void build(int[] nums, int nodeIdx, int l, int r) {
        if (l == r) {
            tree[nodeIdx] = new Node(k);
            int rem = nums[l] % k;
            tree[nodeIdx].prod = rem;
            tree[nodeIdx].count[rem] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        build(nums, 2 * nodeIdx, l, mid);
        build(nums, 2 * nodeIdx + 1, mid + 1, r);
        tree[nodeIdx] = merge(tree[2 * nodeIdx], tree[2 * nodeIdx + 1]);
    }

    // Point update: set nums[pos] = val
    private void update(int nodeIdx, int l, int r, int pos, int val) {
        if (l == r) {
            int rem = val % k;
            tree[nodeIdx].prod = rem;
            for (int i = 0; i < k; i++) {
                tree[nodeIdx].count[i] = 0;
            }
            tree[nodeIdx].count[rem] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        if (pos <= mid) {
            update(2 * nodeIdx, l, mid, pos, val);
        } else {
            update(2 * nodeIdx + 1, mid + 1, r, pos, val);
        }
        tree[nodeIdx] = merge(tree[2 * nodeIdx], tree[2 * nodeIdx + 1]);
    }

    // Range query: aggregate segment node for nums[ql..qr]
    private Node query(int nodeIdx, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[nodeIdx];
        }
        int mid = l + (r - l) / 2;
        if (qr <= mid) {
            return query(2 * nodeIdx, l, mid, ql, qr);
        }
        if (ql > mid) {
            return query(2 * nodeIdx + 1, mid + 1, r, ql, qr);
        }
        Node leftRes = query(2 * nodeIdx, l, mid, ql, qr);
        Node rightRes = query(2 * nodeIdx + 1, mid + 1, r, ql, qr);
        return merge(leftRes, rightRes);
    }

    // Renamed to match the method name the driver expects
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];

        build(nums, 1, 0, n - 1);

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // 1. Persistently update nums[idx] = val
            update(1, 0, n - 1, idx, val);

            // 2. Query range [start, n - 1]
            Node res = query(1, 0, n - 1, start, n - 1);

            // 3. Count of prefixes with remainder x
            ans[i] = res.count[x];
        }

        return ans;
    }
}