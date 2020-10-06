public class InsertionSort {

    public int[] sort(int[] input) {
        if (input.length < 1) return null; // 4 3 2 10 12 1 5 6
        for (int j = 1; j < input.length; j++) {
            int temp = input[j];
            int i = j - 1;
            while (i >= 0 && input[i] > temp) {
                input[i + 1] = input[i];
                i = i - 1;
            }
            input[i + 1] = temp;
        }
        return input;
    }

    public static void main(String args[]) {
        int[] a = {4, 3, 2, 10, 12, 1, 5, 6};
        InsertionSort insertionSort = new InsertionSort();
        a = insertionSort.sort(a);
        for (int j : a) {
            System.out.println(j);

        }
    }
}
