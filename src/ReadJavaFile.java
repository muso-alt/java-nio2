package src;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadJavaFile {
    public static void main(String[] args) {
        try {
            // Определите текущий каталог
            Path currentDirectory = Paths.get("").toAbsolutePath();

            // Имя файла (замените на имя вашего файла .java)
            String fileName = "ExampleJavaClass.java";

            // Перейдите в каталог src проекта (предполагается, что файл .java находится в src)
            Path srcDirectory = currentDirectory.resolve("src");

            // Соберите полное имя файла с текстом программы
            Path javaFilePath = srcDirectory.resolve(fileName);

            // Прочитайте содержимое файла .java и выведите его
            String fileContent = new String(Files.readAllBytes(javaFilePath), StandardCharsets.UTF_8);
            System.out.println("Содержимое файла " + fileName + ":\n");
            System.out.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}