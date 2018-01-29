String a = abb   a->o , b->d
String b = odd
Isomophic: True
String a = abc      a->a, b->d c->c
String b = adc True

String a = foo
String b = ade --> f
False
----
cdec
abcx   c->a d->b e->c d-> 

HashMap: c: a, d: b, e:c


Hashmap | c:a, d:b, e:c

String a = "abc"
String b = "foo"

<Encode, ArrayList<String>>
<012, abc,fgh>
<011, foo, zoo>
<001, cch>
foo zoo oaa

        ooa
        001
                                       i
                                      001
input dict = [ "abc", "foo" , "zoo", "cca" "acc" "fgh" , "cch" ]
output: List<List<String>> :   [abc, fgh] [foo zoo] [cch]
                                   0           1      2
                                   foo,zoo,acc --> ans.add(1) --> List, -->
                                   i = 0 abc fgh
                                   i = 1 foo zoo
                                   i = 2 cch
List<List<String>> | 
List<String> | abc 

isIsomorphic(abc, fgh)
List<String> --> Add(abc, fgh)

foo compare zoo, fgh, cch
zoo compare fgh, cch

n strings in the dict: O(n!)




HashMap | a:f, f:a

public boolean isIsomorphic(String a, String b) {
    if (a.length() != b.length()) {
        return false;
    }
    
    HashMap<Character, Character> hmA = new HashMap<>();
    HashMap<Character, Character> hmB = new HashMap<>();
    
    for (int i = 0; i < a.length(); i++) {
        Character ch = a.charAt(i);
        if (!hmA.isContained(ch)) {
            hmA.put(ch, b.charAt(i));
        } else {
            if (hmA.get(ch) != b.charAt(i)) {
                 return false;
            }
        }
        if (!hmB.isContained(ch)) {
            hmB.put(ch, b.charAt(i));
        } else {
            if (hmB.get(ch) != b.charAt(i)) {
                 return false;
            }
        }
    }
    
    return true;
}

------------------
find the combination of factor
8 = 2 * 2 * 2
  = 2 * 4
  
  
  12
  [2 * 6]
  [2 * 2 * 3]
  [3 * 4]
  List<List<Integer>>
  
2 = X
3 = X
4 = 2 * 2
5 = X
6 = 2 * 3
7 = X
8 = 4 * 2 --> 2 * 2 * 2

base case is 4 = 2 * 2
dp[2] = [2]
dp[3] = [3]
dp[4] = [[2,2], 4]
dp[5] = [5]
dp[6] = [[2,3], 6]
dp[7] = [7]
dp[8] = [[2,2,2], [2,4], 8] --> 2([2]) * 4([2,2], 4),  [2] * [2,2], [2] * [4]
...

  12
  [2 * 6]
  [2 * 2 * 3]
  [3 * 4]
   cur_product = 1
   2 2 3
           12
        2      ....  12
      3  4 5
    
    
[1,2,3]
     i
Generate a number from 0-i: 1 --> swap(1,i)
[1,3,2]
   i
Generate a number from 0-i: 1 --> swap(1,i)
[1,3,2]
 i
Generate a number from 0-i: 0 --> swap(2,i)
[2,3,1]


[A,B,C]: 0-2, index 2: 1/3     一開始，三人挑一人放到index = 2。
[A,B]: 0-1, index 1: 1/2 * 2/3 = 1/3 ，考慮放到index = 1的情況。剩下兩人挑一個放到index = 1，剩下的那兩個被挑剩的機率是(1 - 1/3) ,,    1/2是剩下兩個人, 2選1 --> 這個格挑中的機率是 1/2 * 2/3 = 1/3


[A]: 0-1, index 0: 1 * 1/2 * 2/3 = 1/3
                       1 - 1/2 --> in 138 line
                            1 - 1/3 --> in 137 line

=====n is input=====
int[] num = new int[n]; // n = 52

for (int i = n-1; i >= 0; i--) {
    int rand = random.nextInt(i + 1); // rand [0 ~ 52) == 0, 1, ..., 51
    swap(num, i, rand);
    0~51 挑一個放到51 
    0-50 挑一個放到50...
}

