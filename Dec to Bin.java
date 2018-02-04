// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        int input = 16;
        StringBuilder bin = new StringBuilder();
        while (input != 0) {
            bin.append(input % 2);
            input /= 2;
        }
        bin.reverse();
        System.out.println(bin);
    }
}