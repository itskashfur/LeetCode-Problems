
class Solution {
    int[] ans;

    void dfs(int u, List<Integer>[] adj, String s,
             int[] p, int[] last) {
        int c = s.charAt(u) - 'a', old = last[c];

        if (u > 0 && old != -1) p[u] = old;
        last[c] = u;

        for (int v : adj[u])
            dfs(v, adj, s, p, last);

        last[c] = old;
    }

    int calc(int u, List<Integer>[] tree) {
        ans[u] = 1;
        for (int v : tree[u])
            ans[u] += calc(v, tree);
        return ans[u];
    }

    public int[] findSubtreeSizes(int[] p, String s) {
        int n = p.length;
        List<Integer>[] adj = new ArrayList[n];
        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++)
            adj[p[i]].add(i);

        int[] last = new int[26];
        Arrays.fill(last, -1);
        dfs(0, adj, s, p, last);

        for (int i = 1; i < n; i++)
            tree[p[i]].add(i);

        ans = new int[n];
        calc(0, tree);
        return ans;
    }
}