import java.lang.reflect.Array;
import java.util.*;

public class Operations {
    public int[] twoSum(int[] elements, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < elements.length; i++) {
            int complement = target - elements[i];
            if (hashMap.containsKey(complement)) return new int[]{hashMap.get(complement), i};
            hashMap.put(elements[i], i);
        }
        System.out.println("Target not found");
        return null;
    }

    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0, maxleft = 0, maxright = 0;
        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] >= maxleft) {
                    maxleft = height[left];
                } else {
                    res += maxleft - height[left];
                }
                left++;
            } else {
                if (height[right] >= maxright) {
                    maxright = height[right];
                } else {
                    res += maxright - height[right];
                }
                right--;
            }
        }
        return res;
    }

    public int longestConsecutive(int[] num) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);

                // keep track of the max length
                res = Math.max(res, sum);

                // extend the length to the boundary(s)
                // of the sequence
                // will do nothing if n has no neighbors
                map.put(n - left, sum);
                map.put(n + right, sum);
            } else {
                // duplicates
                continue;
            }
        }
        return res;
    }

    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (n >> i & 1) == 1 ? 1 : 0;
        }
        return count;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        //Arrays.sort(candidates);
        backtrack(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            result = newResult;
            x = x / 10;
        }

        return result;
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i] <= j) {
                    dp[j] += dp[j - coins[i]];
                }

            }
        }
        return dp[amount];
    }


    public static void main(String args[]) {

        Operations operations = new Operations();
        int i9 = operations.change(5, new int[]{1, 2, 5});
        int reverse = operations.reverse(123);
        List<List<Integer>> lists1 = operations.combinationSum(new int[]{2, 3, 6, 7}, 7);
        int i8 = operations.hammingWeight(00000000000000000000000000001011);
        operations.addNum(2);
        operations.addNum(3);
        operations.addNum(4);
        operations.addNum(5);
        double median = operations.findMedian();

        int i7 = operations.numDecodings("102");
        operations.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        int[] indexes = operations.twoSum(new int[]{2, 7, 11, 15}, 18);
        if (indexes != null) {
            for (int i = 0; i < indexes.length; i++) {
                System.out.println("Index: " + indexes[i]);
            }
        }
        int i5 = operations.missingElement(new int[]{4, 7, 9, 10}, 1);
        int i4 = operations.subarraySum(new int[]{1, 2, 2, 1, 4}, 3);
        int calculate = operations.calculate("(1+(4+5+2)-3)+(6+8)");
        int i6 = operations.calculate2("42");
        double pow = operations.pow(3, 4);
        operations.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        int kthLargest = operations.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4);
        double medianSortedArrays = operations.findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
        operations.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        operations.kClosest(new int[][]{{1, 3}, {-2, 2}}, 1);
        int i = operations.threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
        operations.aircraftUtilization(7, new int[][]{{2, 4}, {3, 6}, {3, 2}},
                new int[][]{{1, 2}});
        operations.connectSticks(new int[]{3354, 4316, 3259, 4904, 4598, 474, 3166, 6322, 8080, 9009});
        int i1 = operations.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println(" MAx Area: " + i1);
        List<List<Integer>> lists = operations.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println("Three sum: " + lists);
        int length = operations.findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});
        operations.book(10, 20);
        operations.book(15, 25);
        operations.book(20, 30);
        operations.merge(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3);
        operations.longestArithSeqLength(new int[]{9, 4, 7, 2, 10});
        int i2 = operations.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3);
        int i3 = operations.longestValidParentheses("())))()");
    }

    public int longestOnes(int[] A, int K) {
        int i = 0, j;
        for (j = 0; j < A.length; ++j) {
            if (A[j] == 0) K--;
            if (K < 0 && A[i++] == 0)
                K++;
        }
        return j - i;
    }

    public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] result = new int[nums1.length + nums2.length];
        int k = 0;
        int i = 0;
        int j = 0;

        while (i < nums1.length || j < nums2.length) {
            if (i == nums1.length) {
                result[k++] = nums2[j];
                j++;
                continue;
            }

            if (j == nums2.length) {
                result[k++] = nums1[i];
                i++;
                continue;
            }

            if (nums1[i] < nums2[j]) {
                result[k++] = nums1[i];
                i++;
            } else if (nums1[i] > nums2[j]) {
                result[k++] = nums2[j];
                j++;
            } else {
                result[k++] = nums1[i];
                result[k++] = nums2[j];
                i++;
                j++;
            }
        }
        int size = result.length;
        if (size % 2 == 1) {
            return (double) result[size / 2];
        } else {
            return ((double) result[(size / 2) - 1] + (double) result[size / 2]) / 2;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            return b[0] * b[0] + b[1] * b[1] - a[0] * a[0] + a[1] * a[1];
        });

        for (int[] i : points) {
            maxHeap.add(i);
            if (maxHeap.size() > K) maxHeap.remove();
        }
        int[][] result = new int[K][2];
        while (K-- > 0) {
            result[K] = maxHeap.remove();
        }
        return result;
    }

    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j] + 1]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int missingElement(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;

        int missingNums = nums[right] - nums[0] + (right - left);
        if (missingNums < k) return nums[right] + k - missingNums;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            int missing = nums[mid] - nums[0] + (mid - left) / 2;
            if (missing >= k) right = mid;
            else {
                left = mid;
                k -= missing;
            }
        }
        return nums[left] + k;
    }

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[nums.length - 1];
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int lo = i + 1;
            int high = nums.length - 1;
            while (lo < high) {
                sum = nums[lo] + nums[high] + nums[i];
                if (sum < target) {
                    lo++;
                } else {
                    high--;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }

    public double pow(double x, int n) {
//        double temp = x;
//        if (m == 0)
//            return 1;
//        temp = pow(x, m / 2);
//        if (m % 2 == 0)
//            return temp * temp;
//        else {
//            if (m > 0)
//                return x * temp * temp;
//            else
//                return (temp * temp) / x;
//        }

        if (n == 0)
            return 1;
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return fastPow(x, n);
    }

    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public List<List<Integer>> aircraftUtilization(int maxTravelDist, int[][] forwardRouteList, int[][] returnRouteList) {
        List<List<Integer>> res = new ArrayList<>();
        int len1 = forwardRouteList.length, len2 = returnRouteList.length;
        if (len1 == 0 || len2 == 0) return res;
        Arrays.sort(forwardRouteList, (int[] a, int[] b) -> (a[1] - b[1]));
        Arrays.sort(returnRouteList, (int[] a, int[] b) -> (a[1] - b[1]));
        int left = 0, right = len2 - 1, maxVal = -1;
        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();
        while (left < len1 && right >= 0) {
            int sum = forwardRouteList[left][1] + returnRouteList[right][1];
            if (sum > maxTravelDist) {
                --right;
                continue;
            }
            if (sum >= maxVal) {
                int r = right;
                map.putIfAbsent(sum, new ArrayList<>());
                // check the duplicates
                while (r >= 0 && returnRouteList[r][1] == returnRouteList[right][1]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(forwardRouteList[left][0]);
                    list.add(returnRouteList[r][0]);
                    map.get(sum).add(list);
                    --r;
                }
                maxVal = sum;
            }
            ++left;
        }
        return map.get(maxVal);
    }

    public int connectSticks(int[] sticks) {
//        if (sticks == null || sticks.length < 1)
//            return 0;
//        int tempSum = 0, sum = 0;
//        int len = sticks.length;
//        if (len == 1) return 0;
//        int left = 0;
//        Arrays.sort(sticks);
//        while (left < len) {
//            if (left == 0) {
//                tempSum = sticks[left] + sticks[++left];
//            } else {
//                tempSum = sticks[left];
//            }
//            left++;
//            sum += tempSum;
//        }
//        return sum;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : sticks) {
            pq.offer(s);
        }
        int sum = 0;
        while (pq.size() > 1) {
            int one = pq.poll();
            int twoo = pq.poll();
            int two = one + twoo;
            sum += two;
            System.out.println("One: " + one + " two: " + twoo + " sum: " + two);
            pq.offer(two);
        }
        return sum;
    }

    public int maxArea(int[] array) {
        int l = 0;
        int r = array.length - 1;
        int result = 0;
        while (l < r) {
            result = Math.max(result, (Math.min(array[r], array[l]) * (r - l)));
            if (array[l] < array[r]) l++;
            else r--;
        }
        return result;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) new ArrayList<>();
        int left = 1;
        int right = nums.length - 1;
        int target = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                left = i + 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum > target) {
                        while (nums[right] == nums[right - 1]) right--;
                        right--;
                    } else if (sum < target) {
                        while (nums[left] == nums[left + 1]) left++;
                        left++;
                    } else {
                        result.add(Arrays.asList(nums[left], nums[right], nums[i]));
                        while (nums[right] == nums[right - 1]) right--;
                        while (nums[left] == nums[left + 1]) left++;
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    void merge(int A[], int m, int B[], int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j])
                A[k--] = A[i--];
            else
                A[k--] = B[j--];
        }
        while (j >= 0)
            A[k--] = B[j--];
    }

    public int findLength(int[] A, int[] B) {
        int ans = 0;
        int[][] memo = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; --i) {
            for (int j = B.length - 1; j >= 0; --j) {
                if (A[i] == B[j]) {
                    memo[i][j] = memo[i + 1][j + 1] + 1;
                    if (ans < memo[i][j]) ans = memo[i][j];
                }
            }
        }
        return ans;
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    TreeMap tm = new TreeMap<Integer, Integer>();

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> entry = tm.lowerEntry(end);
        if (entry != null && entry.getValue() > start) return false;
        tm.put(start, end);
        return true;
    }

    public int longestArithSeqLength(int[] A) {
        int res = 2, n = A.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        for (int j = 0; j < A.length; j++) {
            dp[j] = new HashMap<>();
            for (int i = 0; i < j; i++) {
                int d = A[j] - A[i];
                dp[j].put(d, dp[i].getOrDefault(d, 1) + 1);
                res = Math.max(res, dp[j].get(d));
            }
        }
        return res;
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (int) (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis

            }
        }
        if (number != 0) result += sign * number;
        return result;
    }

    public int calculate2(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            }

            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = c;
            }
        }

        for (Integer i : stack) {
            result += i;
        }
        return result;
    }

    PriorityQueue<Integer> max = new PriorityQueue(Comparator.reverseOrder());
    PriorityQueue<Integer> min = new PriorityQueue();

    public void addNum(int num) {
        min.offer(num);
        max.offer(min.poll());
        if (min.size() < max.size()) {
            min.offer(max.poll());
        }
    }

    public double findMedian() {
        if (min.size() == max.size()) {
            return (min.peek() + max.peek()) / 2.0;
        } else {
            return min.peek();
        }
    }
}
