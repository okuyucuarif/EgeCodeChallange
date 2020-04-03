public class AltinDedektoru {

    //different approach to solution o(n^)
    public static int solutionN2(int[] A) {
        // write your code in Java SE 8

        int maxDept = 0;
        for (int i = 0; i < A.length; i++) {
            //Current
            int current = A[i];

            //Look Left Side
            int maxLeftSide = A[i];
            for (int j = i - 1; j > -1; j--) {
                if (A[j] > maxLeftSide) {
                    maxLeftSide = A[j];
                }
            }


            //Look Right Side
            int maxRightSide = A[i];
            for (int k = i + 1; k < A.length; k++) {
                if (A[k] > maxRightSide) {
                    maxRightSide = A[k];
                }
            }
            //Find min between maxRightSide & maxLeftSide
            int min = Math.min(maxRightSide, maxLeftSide);

            //Find current dept
            int dept = min - current;
            if (dept > maxDept) {
                maxDept = dept;
            }
        }

        return maxDept;

    }


    //different approach to solution o(n)
    static int findWater(int[] A) {
        // left[i] contains height of tallest bar to the
        // left of i'th bar including itself
        int left[] = new int[A.length];

        // Right [i] contains height of tallest bar to
        // the right of ith bar including itself
        int right[] = new int[A.length];


        // Fill left array
        left[0] = A[0];
        for (int i = 1; i < A.length; i++)
            left[i] = Math.max(left[i - 1], A[i]);

        // Fill right array
        right[A.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--)
            right[i] = Math.max(right[i + 1], A[i]);

        // Calculate the accumulated water element by element
        // consider the amount of water on i'th bar, the
        // amount of water accumulated on this particular
        // bar will be equal to min(left[i], right[i]) - arr[i] .

        // Initialize result
        int dept = 0;
        int deptMax = 0;
        for (int i = 0; i < A.length; i++) {
            dept = Math.min(left[i], right[i]) - A[i];
            if (dept > deptMax) {
                deptMax = dept;
            }
        }


        return deptMax;
    }

    //different approach to solution
    public static int solution2(int[] A) {
        int maxH = 0;
        int minH = 0;
        int maxD = 0;
        int d = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > maxH) {
                d = maxH - minH;
                maxH = A[i];
                minH = A[i];
            } else if (A[i] < minH) {
                minH = A[i];
            } else {
                d = A[i] - minH;
            }
            if (d > maxD) {
                maxD = d;
            }
        }
        return maxD;
    }

}
