import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class laba4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до вхідного файлу: ");
        String inputFilePath = scanner.nextLine();
        System.out.print("Введіть шлях до вихідного файлу: ");
        String outputFilePath = scanner.nextLine();
        scanner.close();

        try {
            Path inputFile = Paths.get(inputFilePath);
            if (!Files.exists(inputFile)) {
                System.out.println("Вхідний файл не існує.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(inputFile.toString()));
            List<Integer> numbers = new ArrayList<>();
            String line;

            // Зчитуємо дані з файлу та додаємо їх у список чисел
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line);
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    // Якщо формат числа неправильний, пропускаємо рядок
                    continue;
                }
            }

            reader.close();

            // Підрахунок кількості рядків
            int rowCount = numbers.size();
            System.out.println("Початкова кількість рядків: " + rowCount);

            // Підрахунок суми всіх чисел
            int sum = 0;
            for (int number : numbers) {
                sum += number;
            }

            // Розрахунок середнього арифметичного
            double average = (double) sum / rowCount;
            System.out.println("Середнє арифметичне усіх чисел: " + average);

            // Відсортувати числа, що менші за середнє, за зростанням
            List<Integer> sortedNumbers = new ArrayList<>();
            for (int number : numbers) {
                if (number < average) {
                    sortedNumbers.add(number);
                }
            }
            Collections.sort(sortedNumbers);

            // Вивести числа більші за середнє у консоль
            System.out.println("Числа більші за середнє арифметичне:");
            for (int number : numbers) {
                if (number > average) {
                    System.out.println(number);
                }
            }

            // Перевірка і створення вихідного файлу, якщо він не існує
            Path outputFile = Paths.get(outputFilePath);
            if (!Files.exists(outputFile)) {
                Files.createFile(outputFile);
                System.out.println("Створено вихідний файл.");
            }

            // Записати відсортовані числа у вихідний файл
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.toString()));

            for (int number : sortedNumbers) {
                writer.write(Integer.toString(number));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


