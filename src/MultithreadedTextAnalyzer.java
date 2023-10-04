package src;

public class MultithreadedTextAnalyzer {
    private static final String sample = "Многопоточность"; // Образец для поиска

    public static void main(String[] args) {
        // Создаем и запускаем поток для чтения текста
        Thread readerThread = new Thread(() -> {
            String text = readText(); // Считываем текст
            analyzeText(text); // Анализируем текст
        });
        readerThread.start();
    }

    // Метод для считывания текста (просто пример, можно заменить на чтение из файла и т. д.)
    private static String readText() {
        return "Многопоточность — это способность центрального процессора (CPU) выполнять несколько потоков исполнения (вычислительных задач) одновременно.";
    }

    // Метод для анализа текста и вывода результата
    private static void analyzeText(String text) {
        Thread analyzerThread = new Thread(() -> {
            if (text.contains(sample)) {
                System.out.println("Вхождение образца найдено в тексте.");
                System.out.println("Текст: " + text);
            } else {
                System.out.println("Вхождение образца не найдено в тексте.");
            }
        });
        analyzerThread.start();
    }
}
