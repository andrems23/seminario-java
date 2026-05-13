import java.util.Arrays;

/**
 * Classe híbrida com implementações otimizadas de algoritmos de ordenação
 * Com sistema de cronômetro para medir performance
 */
public class ambosmetodos {
    
    // ================= MÉTODO AUXILIAR =================
    private static void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }
    
    // ================= BUBBLE SORT (O(n²)) =================
    public static void bubbleSort(int[] vetor) {
        if (vetor == null || vetor.length <= 1) return;
        
        int n = vetor.length;
        for (int i = 0; i < n - 1; i++) {
            boolean trocou = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    trocar(vetor, j, j + 1);
                    trocou = true;
                }
            }
            if (!trocou) break;
        }
    }
    
    // ================= SELECTION SORT (O(n²)) =================
    public static void selectionSort(int[] vetor) {
        if (vetor == null || vetor.length <= 1) return;
        
        int n = vetor.length;
        for (int i = 0; i < n - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                if (vetor[j] < vetor[menor]) {
                    menor = j;
                }
            }
            if (menor != i) {
                trocar(vetor, i, menor);
            }
        }
    }
    
    // ================= INSERTION SORT (O(n²)) =================
    public static void insertionSort(int[] vetor) {
        if (vetor == null || vetor.length <= 1) return;
        
        for (int i = 1; i < vetor.length; i++) {
            int chave = vetor[i];
            int j = i - 1;
            while (j >= 0 && vetor[j] > chave) {
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j + 1] = chave;
        }
    }
    
    // ================= QUICK SORT (O(n log n)) =================
    public static void quickSort(int[] vetor) {
        if (vetor == null || vetor.length <= 1) return;
        quickSort(vetor, 0, vetor.length - 1);
    }
    
    public static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int pivo = particionar(vetor, inicio, fim);
            quickSort(vetor, inicio, pivo - 1);
            quickSort(vetor, pivo + 1, fim);
        }
    }
    
    private static int particionar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[fim];
        int i = inicio - 1;
        
        for (int j = inicio; j < fim; j++) {
            if (vetor[j] < pivo) {
                i++;
                trocar(vetor, i, j);
            }
        }
        
        trocar(vetor, i + 1, fim);
        return i + 1;
    }
    
    // ================= MERGE SORT (O(n log n)) =================
    public static void mergeSort(int[] vetor) {
        if (vetor == null || vetor.length <= 1) return;
        mergeSort(vetor, 0, vetor.length - 1);
    }
    
    public static void mergeSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int meio = inicio + (fim - inicio) / 2;
            mergeSort(vetor, inicio, meio);
            mergeSort(vetor, meio + 1, fim);
            merge(vetor, inicio, meio, fim);
        }
    }
    
    private static void merge(int[] vetor, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1;
        int n2 = fim - meio;
        
        int[] esquerda = new int[n1];
        int[] direita = new int[n2];
        
        for (int i = 0; i < n1; i++) {
            esquerda[i] = vetor[inicio + i];
        }
        for (int j = 0; j < n2; j++) {
            direita[j] = vetor[meio + 1 + j];
        }
        
        int i = 0, j = 0;
        int k = inicio;
        
        while (i < n1 && j < n2) {
            if (esquerda[i] <= direita[j]) {
                vetor[k] = esquerda[i];
                i++;
            } else {
                vetor[k] = direita[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            vetor[k] = esquerda[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            vetor[k] = direita[j];
            j++;
            k++;
        }
    }
    
    // ================= HEAP SORT (O(n log n)) =================
    public static void heapSort(int[] vetor) {
        if (vetor == null || vetor.length <= 1) return;
        
        int n = vetor.length;
        
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(vetor, n, i);
        }
        
        for (int i = n - 1; i > 0; i--) {
            trocar(vetor, 0, i);
            heapify(vetor, i, 0);
        }
    }
    
    private static void heapify(int[] vetor, int n, int raiz) {
        int maior = raiz;
        int esquerda = 2 * raiz + 1;
        int direita = 2 * raiz + 2;
        
        if (esquerda < n && vetor[esquerda] > vetor[maior]) {
            maior = esquerda;
        }
        
        if (direita < n && vetor[direita] > vetor[maior]) {
            maior = direita;
        }
        
        if (maior != raiz) {
            trocar(vetor, raiz, maior);
            heapify(vetor, n, maior);
        }
    }
    
    // ================= MÉTODOS DE UTILIDADE =================
    
    public static boolean isSorted(int[] vetor) {
        if (vetor == null || vetor.length <= 1) return true;
        for (int i = 0; i < vetor.length - 1; i++) {
            if (vetor[i] > vetor[i + 1]) return false;
        }
        return true;
    }
    
    public static int[] copiar(int[] original) {
        return original.clone();
    }
    
    // ================= CRONÔMETRO =================
    
    /**
     * Classe do cronômetro para medir tempo de execução
     */
    public static class Cronometro {
        private long tempoInicio;
        private long tempoFim;
        
        public void iniciar() {
            tempoInicio = System.nanoTime();
        }
        
        public void parar() {
            tempoFim = System.nanoTime();
        }
        
        public double getTempoSegundos() {
            return (tempoFim - tempoInicio) / 1_000_000_000.0;
        }
        
        public double getTempoMillis() {
            return (tempoFim - tempoInicio) / 1_000_000.0;
        }
        
        public long getTempoNanosegundos() {
            return tempoFim - tempoInicio;
        }
        
        public String formatarTempo() {
            double ms = getTempoMillis();
            if (ms < 1) {
                return String.format("%.2f µs", getTempoNanosegundos() / 1000.0);
            } else if (ms < 1000) {
                return String.format("%.3f ms", ms);
            } else {
                return String.format("%.3f s", getTempoSegundos());
            }
        }
    }
    
    // ================= CLASSE DE TESTE COM CRONÔMETRO =================
    
    public static class TesteOrdenacao {
        
        /**
         * Testa um algoritmo específico e mostra o tempo
         */
        public static double testarAlgoritmo(String nome, AlgoritmoOrdenacao algoritmo, int[] dados) {
            int[] copia = copiar(dados);
            Cronometro cron = new Cronometro();
            
            System.gc(); // Sugere garbage collection para medição mais precisa
            cron.iniciar();
            algoritmo.ordenar(copia);
            cron.parar();
            
            boolean ordenado = isSorted(copia);
            double tempoMs = cron.getTempoMillis();
            
            System.out.printf("│ %-18s │ %8.3f ms │ %10d │ %s      │%n", 
                nome, tempoMs, copia.length, ordenado ? "✅" : "❌");
            
            return tempoMs;
        }
        
        /**
         * Executa todos os algoritmos e mostra tabela comparativa
         */
        public static void executarTodos(int[] original) {
            System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                    TESTE DE PERFORMANCE - SORTS                    ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════╝");
            System.out.println("\n📊 Array Original: " + Arrays.toString(original));
            System.out.println("📏 Tamanho do array: " + original.length + " elementos\n");
            
            System.out.println("┌────────────────────┬──────────────┬────────────┬──────────┐");
            System.out.println("│ Algoritmo          │ Tempo        │ Elementos  │ Ordenado │");
            System.out.println("├────────────────────┼──────────────┼────────────┼──────────┤");
            
            // Algoritmos O(n²)
            testarAlgoritmo("Bubble Sort", (arr) -> bubbleSort(arr), original);
            testarAlgoritmo("Selection Sort", (arr) -> selectionSort(arr), original);
            testarAlgoritmo("Insertion Sort", (arr) -> insertionSort(arr), original);
            
            System.out.println("├────────────────────┼──────────────┼────────────┼──────────┤");
            
            // Algoritmos O(n log n)
            testarAlgoritmo("Quick Sort", (arr) -> quickSort(arr), original);
            testarAlgoritmo("Merge Sort", (arr) -> mergeSort(arr), original);
            testarAlgoritmo("Heap Sort", (arr) -> heapSort(arr), original);
            
            System.out.println("└────────────────────┴──────────────┴────────────┴──────────┘");
        }
        
        /**
         * Testa com diferentes tamanhos de array para comparar performance
         */
        public static void testeComparativoPerformance() {
            System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║              ANÁLISE DE PERFORMANCE POR TAMANHO DO ARRAY               ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
            
            int[] tamanhos = {100, 500, 1000, 5000, 10000};
            
            for (int tamanho : tamanhos) {
                System.out.println("\n📌 TESTANDO COM " + tamanho + " ELEMENTOS:");
                
                // Gera array aleatório
                int[] dados = new int[tamanho];
                for (int i = 0; i < tamanho; i++) {
                    dados[i] = (int) (Math.random() * tamanho * 10);
                }
                
                System.out.println("┌────────────────────┬──────────────┐");
                System.out.println("│ Algoritmo          │ Tempo        │");
                System.out.println("├────────────────────┼──────────────┤");
                
                testarAlgoritmo("Bubble Sort", (arr) -> bubbleSort(arr), dados);
                testarAlgoritmo("Selection Sort", (arr) -> selectionSort(arr), dados);
                testarAlgoritmo("Insertion Sort", (arr) -> insertionSort(arr), dados);
                testarAlgoritmo("Quick Sort", (arr) -> quickSort(arr), dados);
                testarAlgoritmo("Merge Sort", (arr) -> mergeSort(arr), dados);
                testarAlgoritmo("Heap Sort", (arr) -> heapSort(arr), dados);
                
                System.out.println("└────────────────────┴──────────────┘");
            }
        }
        
        /**
         * Teste com array já ordenado (melhor caso)
         */
        public static void testeMelhorCaso(int tamanho) {
            System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                    MELHOR CASO (ARRAY JÁ ORDENADO)                     ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
            
            int[] dados = new int[tamanho];
            for (int i = 0; i < tamanho; i++) {
                dados[i] = i;
            }
            
            System.out.println("\n📌 Array já ordenado com " + tamanho + " elementos\n");
            
            System.out.println("┌────────────────────┬──────────────┬──────────┐");
            System.out.println("│ Algoritmo          │ Tempo        │ Status   │");
            System.out.println("├────────────────────┼──────────────┼──────────┤");
            
            testarAlgoritmo("Bubble Sort", (arr) -> bubbleSort(arr), dados);
            testarAlgoritmo("Insertion Sort", (arr) -> insertionSort(arr), dados);
            testarAlgoritmo("Quick Sort", (arr) -> quickSort(arr), dados);
            
            System.out.println("└────────────────────┴──────────────┴──────────┘");
        }
        
        /**
         * Teste com array em ordem inversa (pior caso)
         */
        public static void testePiorCaso(int tamanho) {
            System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                    PIOR CASO (ARRAY INVERTIDO)                         ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
            
            int[] dados = new int[tamanho];
            for (int i = 0; i < tamanho; i++) {
                dados[i] = tamanho - i;
            }
            
            System.out.println("\n📌 Array em ordem inversa com " + tamanho + " elementos\n");
            
            System.out.println("┌────────────────────┬──────────────┬──────────┐");
            System.out.println("│ Algoritmo          │ Tempo        │ Status   │");
            System.out.println("├────────────────────┼──────────────┼──────────┤");
            
            testarAlgoritmo("Bubble Sort", (arr) -> bubbleSort(arr), dados);
            testarAlgoritmo("Insertion Sort", (arr) -> insertionSort(arr), dados);
            testarAlgoritmo("Quick Sort", (arr) -> quickSort(arr), dados);
            
            System.out.println("└────────────────────┴──────────────┴──────────┘");
        }
        
        /**
         * Demonstração interativa com menu
         */
        public static void demonstrarComMenu() {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            
            System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                    SISTEMA DE ORDENAÇÃO COM CRONÔMETRO             ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════╝");
            
            while (true) {
                System.out.println("\n📋 MENU PRINCIPAL:");
                System.out.println("1  Testar todos algoritmos (array padrão)");
                System.out.println("2  Testar com tamanho personalizado");
                System.out.println("3 Análise de performance por tamanho");
                System.out.println("4  Melhor caso (array já ordenado)");
                System.out.println("5  Pior caso (array invertido)");
                System.out.println("6  Testar algoritmo específico");
                System.out.println("0  Sair");
                System.out.print("\n👉 Escolha uma opção: ");
                
                int opcao = scanner.nextInt();
                
                
                switch (opcao) {
                    case 1 -> {
                        int[] padrao = {38, 27, 43, 3, 9, 82, 10};
                        executarTodos(padrao);
                    }
                    case 2 -> {
                        System.out.print("Digite o tamanho do array: ");
                        int tamanho = scanner.nextInt();
                        int[] aleatorio = new int[tamanho];
                        for (int i = 0; i < tamanho; i++) {
                            aleatorio[i] = (int) (Math.random() * tamanho * 10);
                        }
                        executarTodos(aleatorio);
                    }
                    case 3 -> testeComparativoPerformance();
                    case 4 -> {
                        System.out.print("Digite o tamanho do array: ");
                        int tamMelhor = scanner.nextInt();
                        testeMelhorCaso(tamMelhor);
                    }
                    case 5 -> {
                        System.out.print("Digite o tamanho do array: ");
                        int tamPior = scanner.nextInt();
                        testePiorCaso(tamPior);
                    }
                    case 6 -> testarAlgoritmoEspecifico(scanner);
                    case 0 -> {
                        System.out.println("\n👋 Encerrando programa...");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("❌ Opção inválida!");
                }
                
                System.out.print("\n🔹 Pressione Enter para continuar...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
        
        private static void testarAlgoritmoEspecifico(java.util.Scanner scanner) {
            System.out.println("\n🎯 ALGORITMOS DISPONÍVEIS:");
            System.out.println("1 - Bubble Sort");
            System.out.println("2 - Selection Sort");
            System.out.println("3 - Insertion Sort");
            System.out.println("4 - Quick Sort");
            System.out.println("5 - Merge Sort");
            System.out.println("6 - Heap Sort");
            System.out.print("\nEscolha o algoritmo: ");
            
            int escolha = scanner.nextInt();
            System.out.print("Digite o tamanho do array: ");
            int tamanho = scanner.nextInt();
            
            int[] dados = new int[tamanho];
            for (int i = 0; i < tamanho; i++) {
                dados[i] = (int) (Math.random() * tamanho * 10);
            }
            
            System.out.println("\n📊 Array original (primeiros 10 elementos): " + 
                Arrays.toString(Arrays.copyOf(dados, Math.min(10, tamanho))) + 
                (tamanho > 10 ? "..." : ""));
            
            Cronometro cron = new Cronometro();
            cron.iniciar();
            
            switch (escolha) {
                case 1 -> bubbleSort(dados);
                case 2 -> selectionSort(dados);
                case 3 -> insertionSort(dados);
                case 4 -> quickSort(dados);
                case 5 -> mergeSort(dados);
                case 6 -> heapSort(dados);
                default -> {
                    System.out.println("❌ Opção inválida!");
                    return;
                }
            }
            
            cron.parar();
            
            System.out.println("\n✅ RESULTADO:");
            System.out.println("├─ Tempo: " + cron.formatarTempo());
            System.out.println("├─ Array ordenado: " + (isSorted(dados) ? "✅ Sim" : "❌ Não"));
            System.out.println("└─ Primeiros elementos: " + 
                Arrays.toString(Arrays.copyOf(dados, Math.min(10, dados.length))) +
                (dados.length > 10 ? "..." : ""));
        }
    }
    
    // Interface funcional para algoritmos
    @FunctionalInterface
    public interface AlgoritmoOrdenacao {
        void ordenar(int[] arr);
    }
    
    // ================= MAIN =================
    public static void main(String[] args) {
        // Se tiver argumento, executa modo rápido
        if (args.length > 0) {
            int[] dados = {38, 27, 43, 3, 9, 82, 10};
            TesteOrdenacao.executarTodos(dados);
        } else {
            // Modo interativo com menu
            TesteOrdenacao.demonstrarComMenu();
        }
    }
}