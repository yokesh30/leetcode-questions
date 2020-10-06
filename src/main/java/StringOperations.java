import java.util.*;

public class StringOperations {
    Integer outerCount = 0;
    Integer innerCount = 0;

    public boolean isUnique(String s) {
        if (s == null || s.length() < 1) {
            System.out.println(" String is empty or null");
            return false;
        }
        Boolean isUnique = true;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            outerCount++;
            for (int j = i + 1; j < charArray.length; j++) {
                innerCount++;
                if (charArray[i] == charArray[j]) {
                    isUnique = false;
                    return isUnique;
                }
            }
        }
        return isUnique;
    }

    public boolean isUniqueUsingHashset(String s) {
        if (s == null || s.length() < 1) return false;
        Boolean isUnique = true;
        char[] charArray = s.toCharArray();
        HashSet<Character> hashSet = new HashSet<>();
        for (char ch : charArray) {
            if (hashSet.contains(ch)) {
                System.out.println("String is not unique. Encountered duplicate character is " + ch);
                return false;
            }
            hashSet.add(ch);
        }
        System.out.println("No duplicate characters found");
        return true;
    }

    public StringBuilder stringCompression(String s) {
        if (s == null || s.length() < 1) return null;
        StringBuilder returnValue = new StringBuilder();
        char[] charArray = s.toCharArray();
        char previous = charArray[0];
        int count = 0;
        for (char ch : charArray) {
            if (ch != previous) {
                returnValue.append(previous);
                returnValue = count > 1 ? returnValue.append(count) : returnValue;
                previous = ch;
                count = 1;
            } else {
                count++;
            }
        }

        returnValue.append(charArray[charArray.length - 1]);
        returnValue = count > 1 ? returnValue.append(count) : returnValue;
        return returnValue;
    }

    public boolean isSubstring(String s1, String s2) {
        if (s1 == null || s1.length() < 1) return false;
        if (s2 == null || s2.length() < 1) return false;

        if (s2.indexOf(s1) >= 0) {
            System.out.println("String S1 is in S2");
            return true;
        }
        return false;
    }

    public boolean isRotatedString(String s1, String s2) {
        //s1 erbottlewat s2 waterbottle
        if (s1 == null || s1.length() < 1) return false;
        if (s2 == null || s2.length() < 1) return false;
        if (s1.length() != s2.length()) return false;

        if ((s1 + s2).indexOf(s2) >= 0) return true;
        return false;
    }

    public int decode(String input) {
        int n = input.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = input.charAt(1) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.parseInt(input.substring(i - 1, i)); //1
            int second = Integer.parseInt(input.substring(i - 2, i));//12
            if (first >= 0 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public int lengthOfLongestSubstring(String s) {
        int i = 0;
        int j = 0;
        int max = 0;
        HashSet<Character> ch = new HashSet<>();
        while (j < s.length()) {
            if (!ch.contains(s.charAt(j))) {
                ch.add(s.charAt(j));
                j++;
                max = Math.max(max, ch.size());
            } else {
                ch.remove(s.charAt(i));
                i++;
            }
        }
        return max;
    }

    public String frequentWords(String paragraph, String banned) {
        if (paragraph == null || paragraph == "") return null;
        paragraph = paragraph.replace("[^a-zA-Z]", "");
        paragraph = paragraph.replace(",", "");
        paragraph = paragraph.replace(banned, "");
        paragraph = paragraph.replace(".", "");
        paragraph = paragraph.replace(";", "");
        paragraph = paragraph.replace("?", "");
        paragraph = paragraph.replace("!", "");
        paragraph = paragraph.replace("'", "");
        paragraph = paragraph.toLowerCase();
        String[] words = paragraph.split(" ");
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            int value = 0;

            if (!words[i].equals(banned)) {
                if (hashMap.containsKey(words[i])) value = hashMap.get(words[i]);
                hashMap.put(words[i], value + 1);
            }
        }
        Set<Map.Entry<String, Integer>> entries = hashMap.entrySet();
        String key = "";
        int value = 0;

        for (Map.Entry<String, Integer> me : entries) {
            // Check for word having highest frequency
            if (me.getValue() > value) {
                value = me.getValue();
                key = me.getKey();
            }
        }

        List<String> wordss = Arrays.asList(words);

        Collections.sort(wordss, Comparator.comparingInt(word -> {
            return Collections.frequency(wordss, word);
        }).reversed());
        return wordss.get(0);
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for (String b : banned) set.add(b);

        Map<String, Integer> map = new HashMap<>();
        String ans = "";
        int count = 0;
        for (String s : paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ")) {
            s = s.trim();
            if (s.length() == 0 || set.contains(s)) continue;
            map.put(s, map.getOrDefault(s, 0) + 1);
            if (count < map.get(s)) {
                count = map.get(s);
                ans = s;
            }
        }
        return ans;
    }

//    public String[] reorderLogFiles(String[] logs) {
//        Arrays.sort(logs, (log1, log2) -> {
//            String[] split1 = log1.split(" ", 2);
//            String[] split2 = log2.split(" ", 2);
//            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
//            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
//            if (!isDigit1 && !isDigit2) {
//                int cmp = split1[1].compareTo(split2[1]);
//                if (cmp != 0) return cmp;
//                return split1[0].compareTo(split2[0]);
//            }
//            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
//        });
//        return logs;
//    }

    public String[] reorderLogFiles(String[] logs) {

        Comparator<String> myComp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int s1si = s1.indexOf(' ');
                int s2si = s2.indexOf(' ');
                char s1fc = s1.charAt(s1si + 1);
                char s2fc = s2.charAt(s2si + 1);

                if (s1fc <= '9') {
                    if (s2fc <= '9') return 0;
                    else return 1;
                }
                if (s2fc <= '9') return -1;

                int preCompute = s1.substring(s1si + 1).compareTo(s2.substring(s2si + 1));
                if (preCompute == 0) return s1.substring(0, s1si).compareTo(s2.substring(0, s2si));
                return preCompute;
            }
        };

        Arrays.sort(logs, myComp);
        return logs;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public String minWindow(String searchString, String t) {

        Map<Character, Integer> requiredCharacters = buildMappingOfCharactersToOccurrences(t);
        Map<Character, Integer> windowCharacterMapping = new HashMap<>();
        int left = 0;
        int right = 0;
        int totalCharFrequenciesToMatch = requiredCharacters.size();
        int charFrequenciesInWindowThatMatch = 0;
        int minWindowLengthSeenSoFar = Integer.MAX_VALUE;
        String minWindow = "";

        while (right < searchString.length()) {
            char characterAtRightPointer = searchString.charAt(right);
            addCharacterToHashtableMapping(windowCharacterMapping, characterAtRightPointer);
            boolean rightCharIsARequirement = requiredCharacters.containsKey(characterAtRightPointer);
            if (rightCharIsARequirement) {
                boolean requirementForCharacterMet = requiredCharacters.get(characterAtRightPointer).intValue() ==
                        windowCharacterMapping.get(characterAtRightPointer).intValue();
                if (requirementForCharacterMet) {
                    charFrequenciesInWindowThatMatch++;
                }
            }
            while (charFrequenciesInWindowThatMatch == totalCharFrequenciesToMatch && left <= right) {
                char characterAtLeftPointer = searchString.charAt(left);
                int windowSize = right - left + 1;
                if (windowSize < minWindowLengthSeenSoFar) {
                    minWindowLengthSeenSoFar = windowSize;
                    minWindow = searchString.substring(left, right + 1);
                }
                windowCharacterMapping.put(characterAtLeftPointer, windowCharacterMapping.get(characterAtLeftPointer) - 1);
                boolean leftCharIsARequirement = requiredCharacters.containsKey(characterAtLeftPointer);
                if (leftCharIsARequirement) {
                    boolean characterFailsRequirement = windowCharacterMapping.get(characterAtLeftPointer).intValue() <
                            requiredCharacters.get(characterAtLeftPointer).intValue();

                    if (characterFailsRequirement) {
                        charFrequenciesInWindowThatMatch--;
                    }
                }
                left++;
            }
            right++;
        }

        return minWindow;
    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return false;
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        left = chars.length / 2 - 1;
        right = chars.length / 2;

        while (left >= 0 && right < s.length()) {
            if (chars[left] == chars[right]) continue;
            else return false;
        }
        return true;
    }

    private Map<Character, Integer> buildMappingOfCharactersToOccurrences(String s) {

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            int occurrencesOfCharacter = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), occurrencesOfCharacter + 1);
        }

        return map;
    }

    private void addCharacterToHashtableMapping(Map<Character, Integer> map, Character c) {
        int occurrences = map.getOrDefault(c, 0);
        map.put(c, occurrences + 1);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        return backtrack(s, wordDict, new HashMap<String, List<String>>());
    }

    // backtrack returns an array including all substrings derived from s.
    public List<String> backtrack(String s, List<String> wordDict, Map<String, List<String>> mem) {
        if (mem.containsKey(s)) return mem.get(s);
        List<String> result = new ArrayList<String>();
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                String next = s.substring(word.length());
                if (next.length() == 0)
                    result.add(word);
                else {
                    List<String> backtrack = backtrack(next, wordDict, mem);
                    for (String sub : backtrack) {
                        result.add(word + " " + sub);
                    }
                }
            }
            mem.put(s, result);
        }
        return result;
    }

    public int expressiveWords(String S, String[] words) {
        int count = 0;
        for (String w : words) {
            int i, j; // S & w's pointers.
            for (i = 0, j = 0; i < S.length(); ++i) {
                if (j < w.length() && S.charAt(i) == w.charAt(j)) { // matches, w pointer j 1 step forward to move together with i.
                    ++j;
                }else if (i > 0 && S.charAt(i - 1) == S.charAt(i) && i + 1 < S.length() && S.charAt(i) == S.charAt(i + 1)) { // previous, current & next are same.
                    ++i; // S pointer 1 step forward, attempt to traverse the repeated chars.
                }else if (!(i > 1 && S.charAt(i) == S.charAt(i - 1) && S.charAt(i) == S.charAt(i - 2))) { // current & previous 2 are not same.
                    break; // No match.
                }
            }
            if (i == S.length() && j == w.length()) { ++count; } // both pointers reach ends.
        }
        return count;
    }

    public String minWindow1(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return null;
        int left = 0;
        int minLeft = 0;
        int length = s.length() + 1;
        int count = 0;

        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (hm.containsKey(t.charAt(i))) {
                hm.put(t.charAt(i), hm.get(t.charAt(i)) + 1);
            } else {
                hm.put(t.charAt(i), 1);
            }
        }

        for (int right = 0; right < s.length(); right++) {
            if (hm.containsKey(s.charAt(right))) {
                hm.put(s.charAt(right), hm.get(s.charAt(right)) - 1);
                if (hm.get(s.charAt(right)) >= 0) count++;
            }

            while (count == t.length()) {
                if (right - left + 1 < length) {
                    length = right - left + 1;
                    minLeft = left;
                }

                if (hm.containsKey(s.charAt(left))) {
                    hm.put(s.charAt(left), hm.get(s.charAt(left)) + 1);
                    if (hm.get(s.charAt(left)) > 0) count--;
                }
                left++;
            }
        }
        return s.substring(minLeft, minLeft + length);
    }

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0)
                sum += b.charAt(j--) - '0';
            if (i >= 0)
                sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // Calculate lefts and store in res.
        int left = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0)
                left = left * nums[i - 1];
            res[i] = left;
        }
        // Calculate rights and the product from the end of the array.
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1)
                right = right * nums[i + 1];
            res[i] *= right;
        }
        return res;
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>> map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String> res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> counts = new HashMap();
        for (String domain : cpdomains) {
            String[] cpinfo = domain.split("\\s+");
            String[] frags = cpinfo[1].split("\\.");
            int count = Integer.valueOf(cpinfo[0]);
            String cur = "";
            for (int i = frags.length - 1; i >= 0; --i) {
                cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
                counts.put(cur, counts.getOrDefault(cur, 0) + count);
            }
        }

        List<String> ans = new ArrayList();
        for (String dom : counts.keySet())
            ans.add("" + counts.get(dom) + " " + dom);
        return ans;
    }

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits.isEmpty()) return ans;
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while (ans.peek().length() != digits.length()) {
            String remove = ans.remove();
            String map = mapping[digits.charAt(remove.length()) - '0'];
            for (char c : map.toCharArray()) {
                ans.addLast(remove + c);
            }
        }
        return ans;
    }

    public static List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        char[] check = new char[]{'(', ')'};
        dfs(s, res, check, 0, 0);
        return res;
    }

    public static void dfs(String s, List<String> res, char[] check, int last_i, int last_j) {
        int count = 0;
        int i = last_i;
        while (i < s.length() && count >= 0) {

            if (s.charAt(i) == check[0]) count++;
            if (s.charAt(i) == check[1]) count--;
            i++;
        }

        if (count >= 0) {
            // no extra ')' is detected. We now have to detect extra '(' by reversing the string.
            String reversed = new StringBuffer(s).reverse().toString();
            if (check[0] == '(') dfs(reversed, res, new char[]{')', '('}, 0, 0);
            else res.add(reversed);

        } else {  // extra ')' is detected and we have to do something
            i -= 1; // 'i-1' is the index of abnormal ')' which makes count<0
            for (int j = last_j; j <= i; j++) {
                if (s.charAt(j) == check[1] && (j == last_j || s.charAt(j - 1) != check[1])) {
                    dfs(s.substring(0, j) + s.substring(j + 1, s.length()), res, check, i, j);
                }
            }
        }
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0 || divisor == 0) return 0;
        long d1 = dividend, d2 = divisor;
        long result = divideLong(Math.abs(d1), Math.abs(d2));
        result = d1 * d2 < 0 ? -result : result;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return Integer.MAX_VALUE;
        return (int) result;
    }

    private long divideLong(long dividend, long divisor) {
        if (dividend < divisor) return 0;
        long sum = divisor, divideTimes = 1;
        while (sum + sum <= dividend) {
            sum += sum;
            divideTimes += divideTimes;
        }
        return divideTimes + divideLong(dividend - sum, divisor);
    }

    public List<String> findStrobogrammatic(int n) {
        List<String> one = Arrays.asList("0", "1", "8"), two = Arrays.asList(""), r = two;
        if (n % 2 == 1)
            r = one;
        for (int i = (n % 2) + 2; i <= n; i += 2) {
            List<String> newList = new ArrayList<>();
            for (String str : r) {
                if (i != n)
                    newList.add("0" + str + "0");
                newList.add("1" + str + "1");
                newList.add("6" + str + "9");
                newList.add("8" + str + "8");
                newList.add("9" + str + "6");
            }
            r = newList;
        }
        return r;
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public void backtrack(List<String> list, String str, int open, int close, int max) {

        if (str.length() == max * 2) {
            list.add(str);
            return;
        }

        if (open < max)
            backtrack(list, str + "(", open + 1, close, max);
        if (close < open)
            backtrack(list, str + ")", open, close + 1, max);
    }

    int[] mapping = new int[26];

    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < order.length(); i++)
            mapping[order.charAt(i) - 'a'] = i;
        for (int i = 1; i < words.length; i++)
            if (bigger(words[i - 1], words[i]))
                return false;
        return true;
    }

    boolean bigger(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        for (int i = 0; i < n && i < m; ++i)
            if (s1.charAt(i) != s2.charAt(i))
                return mapping[s1.charAt(i) - 'a'] > mapping[s2.charAt(i) - 'a'];
        return n > m;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[256]; //character hash
        //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right++)]-- >= 1)
                count--;

            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0)
                list.add(left);

            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0)
                count++;
        }
        return list;
    }

    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        int[] buckets = new int[10];
        for (int i = 0; i < digits.length; i++) {
            buckets[digits[i] - '0'] = i;
        }

        for (int i = 0; i < digits.length; i++) {
            for (int k = 9; k > digits[i] - '0'; k--) {
                if (buckets[k] > i) {
                    char tmp = digits[i];
                    digits[i] = digits[buckets[k]];
                    digits[buckets[k]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }

        return num;
    }

    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int open = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') open++;
            else if (c == ')') {
                if (open == 0) continue;
                open--;
            }
            sb.append(c);
        }

        StringBuilder result = new StringBuilder();
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '(' && open-- > 0) continue;
            result.append(sb.charAt(i));
        }
        return result.reverse().toString();
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];     // there are 256 ASCII characters in the world

        int i = 0;  // i will be behind j
        int num = 0;
        int res = 0;

        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) {    // if count[s.charAt(j)] == 0, we know that it is a distinct character
                num++;
            }
            while (num > k && i < s.length()) {     // sliding window
                count[s.charAt(i)]--;
                if (count[s.charAt(i)] == 0) {
                    num--;
                }
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

    public static void main(String args[]) {
        String s = "aaabaaaaccaaaaba";
        StringOperations stringOperations = new StringOperations();
        String dabbab = stringOperations.longestPalindrome("dabbab");
        stringOperations.expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"});
        String s3 = stringOperations.minWindow1("ADOBECODEBANC", "ABC");
        int eceba = stringOperations.lengthOfLongestSubstringKDistinct("eceba", 2);
        stringOperations.productExceptSelf(new int[]{1, 2, 3, 4});
        List<String> strobogrammatic = stringOperations.findStrobogrammatic(4);
        List<String> strings1 = stringOperations.letterCombinations("23");
        List<String> strings2 = stringOperations.removeInvalidParentheses("()()(");
        List<Integer> anagrams = stringOperations.findAnagrams("cbaebabacd", "abc");
        stringOperations.divide(10, 3);
        stringOperations.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz");
        List<String> strings3 = stringOperations.generateParenthesis(3);
        int i = stringOperations.maximumSwap(2376);
        String s2 = stringOperations.minRemoveToMakeValid("(a(b(c)d)");
//        boolean isUnique = stringOperations.isUnique(s);
//        boolean isUniq = stringOperations.isUniqueUsingHashset(s);
//        System.out.println("Is the string unique?: " + isUnique);
//        System.out.println("Is the string uniq?: " + isUniq);

//        System.out.println("Compressed String is " + stringOperations.stringCompression(s));
//        boolean isSubstring = stringOperations.isSubstring("water", "waterbottle");
//        System.out.println("Is Substring: " + isSubstring);
        boolean rotatedString = stringOperations.isRotatedString("erbottlewat", "waterbottle");
        System.out.println("Is rotated string: " + rotatedString);
        String ss = "12122";
        int decode = stringOperations.decode(ss);
        System.out.println(" Decoded count: " + decode);
        int abcabcaa = stringOperations.lengthOfLongestSubstring("abcabcbb");
        System.out.println("Long string: " + abcabcaa);
        stringOperations.frequentWords("Bob hit a ball, the hit BALL flew far after it was hit.?!';", "hit");
        stringOperations.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.?!';", new String[]{"hit"});

        String[] logs = new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        stringOperations.reorderLogFiles(logs);
        boolean valid = stringOperations.isValid("()");
        stringOperations.wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        List<String> strings = stringOperations.subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"});
        String s1 = stringOperations.addBinary("1010", "1011");
    }
}
