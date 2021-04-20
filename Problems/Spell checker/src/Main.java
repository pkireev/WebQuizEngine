import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int d = Integer.parseInt(sc.nextLine());

        Set<String> knownWords = new HashSet<>();
        for (int i = 0; i < d; i++) {
            knownWords.add(sc.nextLine().toLowerCase());
        }

        int l = Integer.parseInt(sc.nextLine());

        Set<String> checkText = new HashSet<>();
        for (int i = 0; i < l; i++) {
            String[] textWords = sc.nextLine().toLowerCase().split("\\s");
            checkText.addAll(List.of(textWords));
        }

        checkText.removeAll(knownWords);
        checkText.forEach(System.out::println);
    }
}