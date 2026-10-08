class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int lvl = 0;

        for (int i = 0; i < s.length(); i++)
            if ((s.charAt(i) == '(' ? lvl++ : --lvl) > 0)
                sb.append(s.charAt(i));

        return sb.toString();
    }
}