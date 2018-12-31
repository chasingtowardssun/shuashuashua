import java.util.*;

public class completeSquare{
        public static List<List<Integer>> getCompleteSquares(int[] nums) {
                List<List<Integer>> res = new ArrayList<>();
                if(nums == null || nums.length < 3) return res;
. check 1point3acres for more.
                Arrays.sort(nums);
                //[1,2,3,4,5,6,7,8,9,10];
                //a^ + b^ = c^-baidu 1point3acres
                for(int i = nums.length - 1; i >= 2; i--) {
                        if(i != nums.length - 1 && nums[i] == nums[i+1]) continue;
                        int left = 0, right = i - 1;
                        while(left < right) {
                                int sum = nums[left] * nums[left] + nums[right] * nums[right];
                                if(sum < nums[i] * nums[i]) {
                                        left++;
                                }else if(sum > nums[i] * nums[i]) {
                                        right--;
                                }else{
                                        List<Integer> path = new ArrayList<>();
                                        path.add(nums[left]);
                                        path.add(nums[right]);. check 1point3acres for more.
                                        path.add(nums[i]);
                                        res.add(path);
                                        left++;
                                        right--;
                                        while(nums[left] == nums[left-1]) left++;
                                        while(nums[right] == nums[right+1]) right--;
                                }
                        }
                }

                return res;
        }

        public static void main(String[] args) {
                int[] nums = {1,1,2,2,3,3,4,4,5,6,7,8,8,9,10,10};
                System.out.println(getCompleteSquares(nums));
        }
}
