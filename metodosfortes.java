
import java.util.Arrays;

public class metodosfortes {

    public static void main(String[] args) {
        int[] original = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Array Original: " + Arrays.toString(original));
        System.out.println("------------------------------------------------");

        int[] copiaQ = original.clone();
        long inicioQ = System.nanoTime(); 
        quickSort(copiaQ, 0, copiaQ.length - 1);
        long fimQ = System.nanoTime();   
        double tempoQ = (fimQ - inicioQ) / 1_000_000.0;  
        System.out.println("Quick Sort:     " + Arrays.toString(copiaQ));
        System.out.println("Tempo Quick:    " + tempoQ + " ms");
        System.out.println("------------------------------------------------");

        int[] copiaM = original.clone();
        long inicioM = System.nanoTime();
        mergeSort(copiaM, 0, copiaM.length - 1);
        long fimM = System.nanoTime();
        double tempoM = (fimM - inicioM) / 1_000_000.0;
        System.out.println("Merge Sort:     " + Arrays.toString(copiaM));
        System.out.println("Tempo Merge:    " + tempoM + " ms");
        System.out.println("------------------------------------------------");

        int[] copiaH = original.clone();
        long inicioH = System.nanoTime();
        heapSort(copiaH);
        long fimH = System.nanoTime();
        double tempoH = (fimH - inicioH) / 1_000_000.0;
        System.out.println("Heap Sort:      " + Arrays.toString(copiaH));
        System.out.println("Tempo Heap:     " + tempoH + " ms");
        System.out.println("------------------------------------------------");
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int p = particionar(arr, l, r);
            quickSort(arr, l, p - 1);
            quickSort(arr, p + 1, r);
        }
    }

    private static int particionar(int[] arr, int l, int r) {
        int pivo = arr[r];
        int i = (l - 1);
        for (int j = l; j < r; j++) {
            if (arr[j] < pivo) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[r]; arr[r] = temp;
        return i + 1;
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0]; arr[0] = arr[i]; arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
        
        if (esq < n && arr[esq] > arr[maior]) maior = esq;
        if (dir < n && arr[dir] > arr[maior]) maior = dir;
        if (maior != i) {
            int swap = arr[i]; arr[i] = arr[maior]; arr[maior] = swap;
            heapify(arr, n, maior);
        }
    }
}
