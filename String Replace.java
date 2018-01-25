// package whatever; // don't place package name!

import java.io.*;

class MyCode {
  public static void main (String[] args) {
    //System.out.println("Hello Java");
    // 給String A, B, C 把A中的所有B都換成C
    new MyCode();
  }
  
  public MyCode() {
    // 比如A = "aaaaa"   B = "aa"  C = "b"  答案可以是bba  bab  abb
    String a = "aaav";
    // zCIabCI
    String b = "aaa";
    String c = "b";
    
    
    System.out.println(replace(a, b, c));
  }
  
  public String replace(String a, String b, String c) {
    if (b.length() > a.length()) {
      return "NOT VALID";
    }
    
    StringBuilder sb = new StringBuilder();
    
    int i = 0;
    while (i < a.length()) {
      //System.out.println(i);
      boolean equ = true;
      for (int j = 0; j < b.length(); j++) {
        
        // 超過範圍
        //System.out.println("i+j:" + (i + j));
        if (i + j >= a.length()) {
          equ = false;
          break;
        }
        
        if (a.charAt(i + j) != b.charAt(j)) {
          //System.out.println("tes");
          equ = false;
          break;
        }
      }
      
      if (equ == true) {
        // 可以替換
        sb.append(c);
        i = i + b.length();
      } else {
        sb.append(a.charAt(i));
        i++;
      }
      //System.out.println("cur sb: " + sb);
    }
    return sb.toString();
    
  }
}
