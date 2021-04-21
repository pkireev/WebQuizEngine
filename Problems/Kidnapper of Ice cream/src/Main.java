import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String newspaper = sc.nextLine();
        String note = sc.nextLine();

        Map<String, Integer> wordsMap = getMapFromString(newspaper);
        Map<String, Integer> noteMap = getMapFromString(note);
        boolean busted = false;

        for (String word : noteMap.keySet()) {
            if (wordsMap.getOrDefault(word, 0) < noteMap.getOrDefault(word, 0)) {
                busted = true;
                break;
            }
        }

        if (busted) {
            System.out.println("You are busted");
        } else {
            System.out.println("You get money");
        }
    }

    public static Map<String, Integer> getMapFromString(String line) {
        Map<String, Integer> result = new HashMap<>();

        for (String word : line.split("\\s")) {
            int qnt = result.getOrDefault(word, 0);
            result.put(word, ++qnt);
        }

        return result;
    }
}