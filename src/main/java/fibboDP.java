import java.util.HashMap;

public class fibboDP {
    static HashMap<Integer, Integer> memo = new HashMap<>();

    static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = fib(n - 1) + fib(n - 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        int result = fib(10);
        System.out.println(result);
    }
}