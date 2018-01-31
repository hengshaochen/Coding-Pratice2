// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        // System.out.println(replace("bbcbcaca", "bca", "b"));
         System.out.println(replace("henrychenhenry", "henry", "emily"));
    }


    public String replace(String a, String b, String c) {
        // corner1: a = "" , b = "" 
        // corner2: a = "abb" , b = "b" , c = "" --> a
        if (a.length() == 0 || b.length() == 0) {
            return a;
        }
       
        int i = 0;
        StringBuilder sb = new StringBuilder();
       while (i < a.length()) {
           if (i + b.length() - 1 >= a.length()) {
               sb.append(a.substring(i, a.length()));
               break;
           }
           
           String sub = a.substring(i, i + b.length());
           if (sub.compareTo(b) == 0) {
               sb.append(c);
               i = i + b.length();
           } else {
               sb.append(a.charAt(i));
               i = i + 1;
           }
       }
       return sb.toString(); 
    }
    
}