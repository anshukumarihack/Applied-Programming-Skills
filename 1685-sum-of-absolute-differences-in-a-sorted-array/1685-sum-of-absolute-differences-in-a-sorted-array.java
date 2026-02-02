class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
       int n=nums.length;
       int[] result = new int[n];
       int totalsum = 0;
       for(int num : nums){
        totalsum += num;
       } 
       int leftsum = 0;
       for(int i = 0;i<n;i++){
        int rightsum = totalsum-leftsum-nums[i];
        int lefttotal = (nums[i]*i)-leftsum;
        int righttotal= rightsum-(nums[i]*(n-i-1));
        result[i]=lefttotal+righttotal;
        leftsum+=nums[i];
       }
       return result;
    }
}