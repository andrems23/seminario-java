

import java.util.Arrays;

public class metodosfracos {

    public static void main(String[] args) {
        int[] original = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Array Original: " + Arrays.toString(original));
        System.out.println("------------------------------------------------");
        
        int[] copiaB = original.clone();
        long inicioB = System.nanoTime(); 
        bubbleSort(copiaB);
        long fimB = System.nanoTime();
        double tempoB = (fimB - inicioB) / 1_000_000.0; 
        System.out.println("Tempo Bubble:    " + tempoB + " ms");
        System.out.println("------------------------------------------------");

        int[] copiaS = original.clone();
        long inicioS = System.nanoTime();
        selectionSort(copiaS);
        long fimS = System.nanoTime();
        double tempoS = (fimS - inicioS) / 1_000_000.0;
        System.out.println("Tempo Selection: " + tempoS + " ms");
        System.out.println("------------------------------------------------");

        int[] copiaI = original.clone();
        long inicioI = System.nanoTime();
        insertionSort(copiaI);
        long fimI = System.nanoTime();
        double tempoI = (fimI - inicioI) / 1_000_000.0;
        System.out.println("Tempo Insertion: " + tempoI + " ms");
        System.out.println("------------------------------------------------");
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j]; 
                    arr[j] = arr[j + 1]; 
                    arr[j + 1] = temp;
                }
        System.out.println("Bubble Sort:    " + Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[minIdx]) minIdx = j;
            int temp = arr[minIdx]; 
            arr[minIdx] = arr[i]; 
            arr[i] = temp;
        }
        System.out.println("Selection Sort: " + Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int chave = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > chave) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = chave;
        }
        System.out.println("Insertion Sort: " + Arrays.toString(arr));
    }
}
