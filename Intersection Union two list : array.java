// Union + 去重:
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        int arr1[] = {1, 2, 4, 5, 6};
        int arr2[] = {2, 3, 5, 5, 5, 7};
        int m = arr1.length;
        int n = arr2.length;
        printUnion(arr1, arr2);
    }
    
    public void printUnion(int[] arr1, int[] arr2) {
        int i = 0, j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                System.out.print(arr1[i] + " ");
                i++;
                j++;
                
                // 去重，確保下一個一定是不重複的
                while (i < arr1.length && arr1[i - 1] == arr1[i]) {
                    i++;
                }
                while (j < arr2.length && arr2[j - 1] == arr2[j]) {
                    j++;
                }  
            } else if (arr1[i] < arr2[j]) {
                System.out.print(arr1[i++] + " ");
            } else {
                System.out.print(arr2[j++] + " ");
            }
        }
        
        while (i < arr1.length) {
            System.out.print(arr1[i++] + " ");
        }
        while (j < arr2.length) {
            System.out.print(arr2[j++] + " ");
        }
    }
}

// Intersection + 去重
// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        int arr1[] = {1, 2, 2, 2, 4, 5, 6};
        int arr2[] = {2, 2, 2, 3, 5, 5, 5, 7};
        int m = arr1.length;
        int n = arr2.length;
        printUnion(arr1, arr2);
    }
    
    public void printUnion(int[] arr1, int[] arr2) {
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                System.out.print(arr1[i] + " ");
                i++;
                j++;
                
                // 去重，確保下一個一定是不重複的
                while (i < arr1.length && arr1[i - 1] == arr1[i]) {
                    i++;
                }
                while (j < arr2.length && arr2[j - 1] == arr2[j]) {
                    j++;
                }  
            } else if (arr1[i] < arr2[j]) {
                // System.out.print(arr1[i++] + " ");
                i++;
            } else {
                // System.out.print(arr2[j++] + " ");
                j++;
            }
        }
        /*
        while (i < arr1.length) {
            System.out.print(arr1[i++] + " ");
        }
        while (j < arr2.length) {
            System.out.print(arr2[j++] + " ");
        }
        */
    }
}
