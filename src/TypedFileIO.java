package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TypedFileIO {
    public static void main(String[] args) {
        String fileName = "numbers.dat";

        // Шаг 1: Запись целых чисел в файл (byte, int, float)
        writeNumbersToFile(fileName);

        // Шаг 2: Считывание чисел из файла и рассчет их среднего значения
        List<Integer> intNumbers = new ArrayList<>();
        float sum = readNumbersFromFile(fileName, intNumbers);

        // Вывод результатов
        if (!intNumbers.isEmpty()) {
            System.out.println("Числа типа int: " + intNumbers.size());
            System.out.println("Среднее значение: " + (sum / intNumbers.size()));
        } else {
            System.out.println("Не было считано чисел типа int.");
        }

        // Шаг 4: Расчет среднего значения второй половины, третьей четверти чисел
        int halfSize = intNumbers.size() / 2;
        int quarterSize = intNumbers.size() / 4;

        float secondHalfSum = 0.0f;
        float thirdQuarterSum = 0.0f;

        for (int i = halfSize; i < intNumbers.size(); i++) {
            secondHalfSum += intNumbers.get(i);
        }

        for (int i = 2 * quarterSize; i < 3 * quarterSize; i++) {
            thirdQuarterSum += intNumbers.get(i);
        }

        if (quarterSize > 0) {
            System.out.println("Среднее второй половины: " + (secondHalfSum / halfSize));
            System.out.println("Среднее третьей четверти: " + (thirdQuarterSum / quarterSize));
        } else {
            System.out.println("Не было считано достаточно чисел для расчета среднего третьей четверти.");
        }
    }

    private static void writeNumbersToFile(String fileName) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            dos.writeByte(10);       // byte
            dos.writeInt(20);       // int
            dos.writeInt(40);       // int
            dos.writeInt(12);       // int
            dos.writeInt(51);       // int
            dos.writeFloat(30.5f);  // float
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static float readNumbersFromFile(String fileName, List<Integer> intNumbers) {
        float sum = 0.0f;

        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            byte b = dis.readByte();
            int i = dis.readInt();
            float f = dis.readFloat();

            sum = i;
            intNumbers.add(i);

            System.out.println("Считано byte: " + b);
            System.out.println("Считано int: " + i);
            System.out.println("Считано float: " + f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }
}
