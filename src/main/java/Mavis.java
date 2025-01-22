import java.util.Scanner;

public class Mavis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Mavis\nWhat can I do for you?");

        while(true) {
            String word = scanner.nextLine();
            if (word.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println("Mavis: " + word);
            }
        }
        scanner.close();

    }
}
