
class Solution {
    public int fib(int n) {
        return calculate(n);
    }

    // Recursive method
    static int calculate(int n) {
        if (n <= 1)
            return n;

        return calculate(n - 1) + calculate(n - 2);
    }

    // Iterative overloaded method
    static int calculate(int n, boolean iterative) {
        if (n <= 1)
            return n;

        int a = 0, b = 1;

        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }

        return b;
    }
}
