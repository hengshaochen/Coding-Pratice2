class Solution {
    public String addBinary(String a, String b) {
        if (a.length() == 0 || b.length() == 0) {
            return "";
        }
        
        int i = a.length() - 1;
        int j = b.length() - 1;
        
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int sum = carry;
            sum = sum + (i < 0 ? 0 : Character.getNumericValue(a.charAt(i)));
            sum = sum + (j < 0 ? 0 : Character.getNumericValue(b.charAt(j)));
            
            sb.append(Integer.valueOf(sum % 2));
            carry = sum / 2;
            i--;
            j--;
        }
        if (carry != 0) {
            sb.append(Integer.valueOf(carry));
        }
        return sb.reverse().toString();
    }
}