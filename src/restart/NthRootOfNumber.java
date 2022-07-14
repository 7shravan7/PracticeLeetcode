package restart;

public class NthRootOfNumber {

    public static double findNthRootOfM(int n, long m) {
        return binarySearch(n,m);
    }

    private static double multiply(double num, int n, long m) {
        double res = 1;
        for(int i=1;i<=n;i++){
            res *= num;
            if(res>m){
                return res;
            }
        }
        return res;
    }

    private static double binarySearch(int n, long m) {
        double low = 1;
        double high = (double)m;
        double eps = 1e-7;
        while(high-low>eps){
            double mid = low + (high-low)/2.0;
            //System.out.println(mid);
            double mul = multiply(mid, n, m);
            if(mul>m){
                high = mid;
            } else {
                low = mid;
            }
        }
        return low;
    }

    // Newton Raphson Method
    // https://brilliant.org/wiki/newton-raphson-method/#:~:text=The%20Newton%2DRaphson%20method%20(also,straight%20line%20tangent%20to%20it.
    // x(n) = x(n)-(f(x)/f`(x))
    /*
    Let’s say X is the Nth root of M.
        X = M ^ (1 / N)
    The above equation can be written as follows:
        X ^ N = M
        X ^ N - M = 0
    Let’s assume F(X) = X ^ N - M
    Now we need to find the root of this equation, for which we can use the Newton Raphson method to solve
    this equation.
    In Newton's Raphson method we start with an initial guess value of X (any random initial value is fine)
     and iterate through a process that takes us towards the actual solution of the equation.
    Let’s say X(0) is the initial guess value.
    The relationship between the current value of solution value x(k) and the solution of the equation in
    the next iteration X(K + 1) is as follows:
        X(K + 1) = X(K) - F(X(K)) / F'(X(K)), where
                F'(X(K)) denotes the value derivative of F(X) at X = X(K).                                    ....(1)
    In the given case F'(X(k)) = N * X(K) ^ N - 1.
    So equation (1) can be written as follows:
        X(K + 1) = X(K) - (X(K) ^ N - M) / (N * X(K) ^ N - 1)
        X(K + 1) = (X(K) * (N * X(K) ^ N - 1) - X(K) ^ N + M) / (N * X(K) ^ N - 1))
        X(K + 1) = (X(K) ^ N * (N - 1) + M) / (N * X(K) ^ N - 1)
    So using the above formula to calculate new possible solution value X.
    Till the gap between X(K + 1) and X(K) become less than 10-7.
    When this condition is reached we get our required solution.
    Time Complexity
        O(log(M) * log(N)), where N and M are given integers.

    As we are approaching the correct solution from the guessed solution,
    it will take order of O(log(M)) time in the worst case and
    for calculating X ^ N takes O(log(N)) order of time.

Space Complexity
O(1)
     */
    public static double findNthRootOfM1(int n, long m) {

        // Variable to store maximum possible error in order
        // to obtain the precision of 10^(-6) in the answer.
        double error = 1e-7;

        // Difference between the current answer, and the answer
        // in next iteration, which we take as big as possible initially.
        double diff = 1e18;

        // Guessed answer value
        double xk = 2;

        // We keep on finding the precise answer till the difference between
        // answer of two consecutive iteration become less than 10^(-7).
        while (diff > error) {

            // Answer value in the next iteration.
            double xk_1 = (Math.pow(xk, n) * (n - 1) + m) / (n * Math.pow(xk, n - 1));

            // Difference of answer in consecutive states updated.
            diff = Math.abs(xk - xk_1);

            // Updating the current answer with the answer of next iteration.
            xk = xk_1;
        }

        // Returning the nthRootOfM with precision upto 6 decimal places
        // which is xk.
        return xk;
    }


    public static void main(String[] args) {
        System.out.println(findNthRootOfM(4,69));
        System.out.println(findNthRootOfM1(4,69));
        System.out.println(findNthRootOfM(3,27));
        System.out.println(findNthRootOfM1(3,27));
    }
}
