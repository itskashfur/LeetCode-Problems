class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int len=Integer.MAX_VALUE;
        int i=0,n=s.length(),j=0;
        int count=0;
        String ans=new String(s);
        while(j<n){
            if(s.charAt(j)=='1') count++;
            while(i<n&&(count>k||s.charAt(i)=='0')){
                if(s.charAt(i)=='1') count--;
                i++;
            }
            if(count==k){
                int currlen=j-i+1;
                String curr=s.substring(i,j+1);

                if(currlen<len){
                    len=currlen;
                    ans=curr;
                }
                else if(currlen==len&&curr.compareTo(ans)<0){
                    ans=curr;
                }
            }
            j++;
        }
        if(len==Integer.MAX_VALUE){
            return "";
        }
        return ans;
    }
}