import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CoronaTestKıtı {


    static void coronaTestKiti(int[] arr) {

        Map<Integer, Integer> number = new HashMap<Integer, Integer>();
        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            if (!number.containsKey(arr[i])) {
                number.put(arr[i], 1);
            } else {
                number.put(arr[i], number.get(arr[i]) + 1);
            }

            if (arr[i] > max) {
                max = arr[i];
            }

        }

        int total = number.get(max);
        System.out.println(max + " " + total);


    }


    public static void main(String[] args) throws IOException {

        File file =
                new File("input08.txt");
        Scanner scanner = new Scanner(file);
        int arCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[arCount];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < arCount; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        coronaTestKiti(ar);


        scanner.close();
    }

}




