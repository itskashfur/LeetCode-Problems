class SparseTable {
    int sparse[][]; 
    SparseTable(int[] nums){
        int n = nums.length; 
        sparse = new int[21][n]; 

        // Now we have to create a sparse tables with nums, where nums[i] = sum of zeroBlocks[i] + zeroBlocks[i + 1] 

        // for zero length 
        for(int i = 0; i < n; i++) {
            sparse[0][i] = nums[i]; 
        }

        // for length 2 - 20 
        for(int base = 1; base <= 20; base++) {
            for(int i = 0; i < n; i++) {

                int pow2 = 1 << (base - 1); 
                if(i + pow2 < n) {
                    sparse[base][i] = Math.max(sparse[base - 1][i], sparse[base - 1][i + pow2]); 
                } else sparse[base][i] = sparse[base - 1][i]; 
            }
        }
    }
    int query(int l, int r) {
        if(l > r) return 0; 
        // return the max in range r to l 
        int base = 0; 
        for(; base <= 20; base++) {
            if((1 << base) > r - l + 1 ) {
                break; 
            }
        }
        base--; 
        if(base < 0) return 0;  
        return Math.max(sparse[base][l], sparse[base][r - (1 << base) + 1]); 
    }
}

class SegmentTree {
    private int n; 
    private int arr[]; 
    private int seg[]; 

    SegmentTree(int[] nums){
        int n = nums.length; 
        this.n = n; 
        seg = new int[4 * n]; 
        this.arr = nums; 
        build(1, 0, n - 1); 
    }

    private void build(int node, int l, int r) {
        if(l == r) {
            seg[node] = arr[l]; 
            return; 
        }

        int mid = (l + r) >> 1; 
        build(2 * node, l , mid); 
        build(2 * node + 1, mid + 1, r); 
        seg[node] = Math.max(seg[2 * node], seg[2 * node + 1]); 
    }

    int internalQuery(int node, int st, int en, int l, int r) {
        if(l <= st && en <= r) {
            // we found the current node 
            return seg[node]; 
        }
        int mid = (st + en) >> 1; 
        int res = 0; 
        if(mid >= l) {
            res = Math.max(res, internalQuery(node * 2, st, mid, l, r)); 
        }

        if(r > mid) {
            res = Math.max(res, internalQuery(node * 2 + 1, mid + 1, en, l, r)); 
        }
        return res; 
    }

    int query(int l, int r) {
        if(l > r) return 0; 
        return internalQuery(1, 0, n - 1, l, r); 
    }
}

class Solution {
    int seg = 0; 
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] q) {
        int n = s.length(); 
        int cnt1 = 0; 
        for(char c: s.toCharArray()) {
            if(c == '1') cnt1++; 
        }

        List<Integer> zeroBlocks = new ArrayList<>(); 
        List<Integer> zeroLeft = new ArrayList<>(); 
        List<Integer> zeroRight = new ArrayList<>(); 

        int idx = 0; 
        while(idx < n) {
            int r = idx; 
            while(r < n && s.charAt(idx) == s.charAt(r)) {
                r++; 
            }
            int curBlockLen = r - idx; 
            if(s.charAt(idx) == '0') {
                // zero block 
                zeroBlocks.add(curBlockLen); 
                zeroLeft.add(idx);
                zeroRight.add(r - 1); 
            }
            idx = r; 
        }

        // Now zeroLeft, zeroRight - both are sorted 
        int m = zeroBlocks.size(); 
        seg = m; 
        List<Integer> ans = new ArrayList<>(); 
        // base case 
        if(m <= 1) {
            for(int i = 0; i < q.length; i++) ans.add(cnt1); 
            return ans; 
        }
        int nums[] = new int[m - 1]; 
        // prepar the nums 
        for( int bl = 0; bl < m - 1; bl++) {
            nums[bl] = zeroBlocks.get(bl) + zeroBlocks.get(bl + 1); 
        }
        // SparseTable sp = new SparseTable(nums); 
        SegmentTree sp = new SegmentTree(nums); 

        
        for(int i = 0; i < q.length; i++) {
            int l = q[i][0], r= q[i][1]; 

            // More than two segments. Now we have to apply the operation in [l...r] 
            // We have three cases 
            int l_idx = lowerBound(zeroRight, l); 
            int r_idx = upperBound(zeroLeft, r) - 1; 

            if(l_idx > m - 1 || r_idx < 0 || l_idx >= r_idx) {
                // left index can not be last, 
                // right index can not be first 
                // both cannot be same or l_idx > r_idx 
                ans.add(cnt1); 
                continue; 
            } 

            // leftMostBlock that falls or overlaps with l 
            // it means for this zero block zeroLeft[i] < l & zeroRight[i] > l 
            // for this case contribution = r - max(zeroLeft[i], l) + zeroBlock[i + 1] 
            int leftLen = zeroRight.get(l_idx) - Math.max(zeroLeft.get(l_idx), l) + 1; 

            // rightMostBlock that falls or verlaps with r means zerLeft[j] < r & zeroRight[j] > r
            // here contri = min(r, zeroRight[j])  - zeroLeft[j] + zeroBlock[j - 1]
            int rightLen = Math.min(r, zeroRight.get(r_idx)) - zeroLeft.get(r_idx) + 1; 

            // If there are only two 0 blocks within the substring 
            if ( l_idx + 1 == r_idx) {
                int contribution = leftLen + rightLen; 
                ans.add(cnt1 + contribution); 
                continue; 
            }
            // If more than two blocks 
            // left contribution 
            int leftContri = leftLen + zeroBlocks.get(l_idx + 1); 
            int rightContri = rightLen + zeroBlocks.get(r_idx - 1); 

            // Now for all middle one's we have to find the max from the range 
            // i.e, from (i + 1 ... j - 2 )
            // This how to find efficiently ?? We can use Segment Tree / or sparse tables 
            int middleContri = sp.query(l_idx + 1, r_idx - 2); // why r_idx - 2?? If r_idx -1, then it will be count r_idx again ( last pair again).
            ans.add(cnt1 + Math.max(leftContri, Math.max(rightContri, middleContri)));  
        }
        return ans; 
    }
    private int lowerBound(List<Integer> zeroRight, int l) {
        int left = 0; 
        int right = seg; 
        while(left < right) {
            int mid = (left + right) >> 1; 
            if(zeroRight.get(mid) >= l) {
                right = mid; 
            } else left = mid + 1; 
        }
        return left; 
    }
    private int upperBound(List<Integer> zeroLeft, int r) {
        int left = 0, right = seg; 
        while(left < right) {
            int mid = (left + right) >> 1; 
            if(zeroLeft.get(mid) <= r) {
                left = mid + 1; 
            } else right = mid; 
        }
        return left; 
    }
}