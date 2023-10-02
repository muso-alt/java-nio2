package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CountCharacterInFile {
    public static void main(String[] args) {
        String fileName = "java_documentation.txt"; 
        Scanner sc = new Scanner (System.in);
        char targetCharacter = sc.nextLine().charAt(0); // Получаем символ из аргументов командной строки

        int count = countCharacterInFile(fileName, targetCharacter);

        System.out.println("Символ '" + targetCharacter + "' встречается " + count + " раз.");
        sc.close();
    }

    public static int countCharacterInFile(String filePath, char targetCharacter) {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int c;

            while ((c = reader.read()) != -1) {
                char character = (char) c;

                // Проверяем, совпадает ли считанный символ с целевым символом
                if (character == targetCharacter) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}