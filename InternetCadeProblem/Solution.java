import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int countingComputer(Map<Integer, Integer> clean) {
        int countforcouple = 0;
        for (Map.Entry mapElement : clean.entrySet()) {

            int key = (int) mapElement.getKey();
            int value = (int) mapElement.getValue();
            int temp = value / 2;
            countforcouple = countforcouple + temp;

        }
        return countforcouple;

    }

    public static int solution(int K, int[] I, int[] O) {
        /* I = Inside O= Outside dizisi.
         * İçeri de ki bilgisayarları temsil etmek için bir hashmap kullandım.(Aynı şey outside hashmap'i içinde geçerli.
         * Bilgisayarın Id'si hasmap de key'i temsil ederken o Id'ye ait olan value değeride içeride kaç tane o ıd'ye sahip bilgisayar malzemesi olduğunu gösterir.

         */
        Map<Integer, Integer> inside = new HashMap<>();
        Map<Integer, Integer> outside = new HashMap<>();
        //Hasp map dizilere göre oluşturuluyor.

        //Inside
        for (int i = 0; i < I.length; i++) {
            if (!inside.containsKey(I[i])) {
                inside.put(I[i], 1);
            } else {
                inside.put(I[i], inside.get(I[i]) + 1);
            }
        }
        //Outside
        for (int i = 0; i < O.length; i++) {
            if (!outside.containsKey(O[i])) {
                outside.put(O[i], 1);
            } else {
                outside.put(O[i], outside.get(O[i]) + 1);
            }
        }

        //K = ise sadece içerde ki bilgisayar çiftini saymak yeterli
        if (K == 0) {
            return countingComputer(inside);
        }
        // Hashmap traverse ediliyor içerde tek sayıda bilgisayar malzemesi var ilk aşamada onlar çif yapılmaya çalışıyor.
        int countforK = 0;
        for (Map.Entry mapElement : inside.entrySet()) {

            int key = (int) mapElement.getKey();
            int value = (int) mapElement.getValue();
            if (value % 2 == 1 && outside.containsKey(key)) {
                if (outside.get(key) - 1 == 0) {
                    outside.remove(key);
                } else {
                    outside.put(key, outside.get(key) - 1);
                }
                value++;
                inside.put(key, value);
                countforK++;
            }
            //eğer değere ulaşıldıysa bilgisayar sayısı sayılıyor
            if (countforK == K) {
                return countingComputer(inside);
            }
        }
        //eğer tekleri çift yapmak yeterli gelmediyse dışarıdan çifter şekilde bilgisayar malzemesi taşımak gerek onun için gerekli döngü
        for (Map.Entry mapElement : outside.entrySet()) {

            int key = (int) mapElement.getKey();
            int value = (int) mapElement.getValue();
            if (value != 0 && value / 2 != 0) {
                //Hepsini içeri taşı
                int demand = K - countforK;
                int temp = value / 2;
                temp = temp * 2;
                if (demand > temp) {
                    outside.put(key, value - temp);
                    demand = temp;
                    countforK = countforK + temp;
                } else {
                    outside.put(key, value - demand);

                    countforK = countforK + demand;
                }
                if (inside.containsKey(key)) {
                    inside.put(key, inside.get(key) + demand);
                } else {
                    inside.put(key, demand);
                }
            }


            if (countforK == K) {
                return countingComputer(inside);
            }
        }
        //Dışarı da ki malze K'dan az ise her koşulu deneyip sonra sayma işlemi yapıyoruz

        return countingComputer(inside);
        // write your code in Java SE 8
    }


    public static void main(String[] args) {
        int[] C = {1, 1};
        int[] D = {2, 2, 2, 2};
        int k = 4;
        System.out.println("0, new int[]{1, 2, 1, 1}, new int[]{1, 4, 3, 2, 4} CEVAP = " + solution(0, new int[]{1, 2, 1, 1}, new int[]{1, 4, 3, 2, 4}));
        System.out.println("2, new int[]{1, 2, 1, 1}, new int[]{1, 4, 3, 2, 4} CEVAP = " + solution(2, new int[]{1, 2, 1, 1}, new int[]{1, 4, 3, 2, 4}));
        System.out.println("10, new int[]{1, 2, 1, 1, 3}, new int[]{1, 4, 3, 2, 4} CEVAP = " + solution(10, new int[]{1, 2, 1, 1, 3}, new int[]{1, 4, 3, 2, 4}));
        System.out.println("10, new int[]{1, 2, 1, 2, 3}, new int[]{1, 4, 3, 2, 4} CEVAP = " + solution(10, new int[]{1, 2, 1, 2, 3}, new int[]{1, 4, 3, 2, 4}));
        System.out.println("10, new int[]{1, 2, 1, 1, 3, 5}, new int[]{1, 4, 3, 2, 4} CEVAP = " + solution(10, new int[]{1, 2, 1, 1, 3, 5}, new int[]{1, 4, 3, 2, 4}));
        System.out.println("10, new int[]{0, 1, 2, 1, 1, 3, 5}, new int[]{1, 4, 3, 2, 4} CEVAP = " + solution(10, new int[]{0, 1, 2, 1, 1, 3, 5}, new int[]{1, 4, 3, 2, 4}));
        System.out.println("10, new int[]{0, 1, 2, 1, 1, 3, 8}, new int[]{1, 4, 3, 2, 4} CEVAP = " + solution(10, new int[]{0, 1, 2, 1, 1, 3, 8}, new int[]{1, 4, 3, 2, 4}));
        System.out.println("2, new int[]{1, 2, 1, 1, 3, 5}, new int[]{1, 4, 3, 2, 4} CEVAP = " + solution(2, new int[]{1, 2, 1, 1, 3, 5}, new int[]{1, 4, 3, 2, 4}));
        System.out.println("1, new int[]{1, 2, 1, 1, 3, 5}, new int[]{1, 4, 3, 2, 4} CEVAP = " + solution(1, new int[]{1, 2, 1, 1, 3, 5}, new int[]{1, 4, 3, 2, 4}));
        System.out.println("3, new int[]{1, 2, 1, 1, 3, 5}, new int[]{1, 4, 3, 2, 4} CEVAP = " + solution(3, new int[]{1, 2, 1, 1, 3, 5}, new int[]{1, 4, 3, 2, 4}));
    }
}
