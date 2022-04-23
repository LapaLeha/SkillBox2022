import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while (true) {
            System.out.print("Введите путь копируемой папки:\n");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            System.out.print("Введите путь к папкe для вставки:\n");
            Scanner scanner1 = new Scanner(System.in);
            String input1 = scanner1.nextLine();

            FileUtils.copyFolder(input, input1);

        }
    }
}
