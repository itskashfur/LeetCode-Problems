class Solution {
    public int totalNumbers(int[] digits) {
        int[] arr = new int[10];
        for(int p:digits){
            arr[p]++;
        }
        int help=0;
        for(int i=0;i<10;i++){
            if(arr[i]>0)
                help++;
        }
        int count=0;
        for(int i=0;i<10;i+=2){
            if(arr[i]<1)
                continue;
            arr[i]--;
            if(arr[i]==0)
                help--;
            for(int j=1;j<10;j++){
                if(arr[j]<1)
                    continue;
                arr[j]--;
                if(arr[j]==0)
                    help--;
                count+=help;
                if(arr[j]==0)
                    help++;
                arr[j]++;
            }
            if(arr[i]==0)
                help++;
            arr[i]++;
        }
        return count;
    }
}