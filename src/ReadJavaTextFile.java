package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadJavaTextFile {
    public static void main(String[] args) {
        String fileName = "java_documentation.txt"; 

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int javaCount = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                // Разбиваем строку на слова, разделенные пробелами
                String[] words = line.split(" ");

                for (String word : words) {
                    // Проверяем, содержит ли слово "java" (без учета регистра)
                    if (word.equalsIgnoreCase("java")) {
                        javaCount++;

                        // Если встретили "java" в третий раз, завершаем цикл
                        if (javaCount == 3) {
                            break;
                        }
                    }
                }

                System.out.println(javaCount);
                // Если встретили "java" в третий раз, завершаем цикл
                if (javaCount >= 3) {
                    System.out.println("Третье вхождение слова 'java' найдено.");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}