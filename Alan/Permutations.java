class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        permutation(nums,ans,0);
        return ans;
    }
    public void permutation(int nums[],List<List<Integer>> ans,int index){
        if(index>=nums.length){
            ArrayList<Integer> l=new ArrayList<>();

            for(Integer x:nums){
                l.add(x);
            }
            ans.add(l);
            return;
        }
        for(int i=index;i<nums.length;i++){
            int temp=nums[index];
            nums[index]=nums[i];
            nums[i]=temp;
            permutation(nums,ans,index+1);
           temp=nums[index];
            nums[index]=nums[i];
            nums[i]=temp;
        }
    }
}