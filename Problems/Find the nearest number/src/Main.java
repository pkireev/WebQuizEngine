import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = Arrays.stream(sc.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        int[] res = new int[arr.length];

        int value = sc.nextInt();
        for (int i = 0; i < arr.length; i++) {
            res[i] = Math.abs(arr[i] - value);
        }

        int min = Arrays.stream(res).min().getAsInt();

        List<Integer> r = new ArrayList<>();

        for (int i = 0; i < res.length; i++) {
            if (res[i] == min) {
                r.add(arr[i]);
            }
        }

        Collections.sort(r);
        for (int i : r) {
            System.out.print(i + " ");
        }

    }
}