/*
 */
package practical.assessment.p1;

/**
 *
 * @author Kong My
 */
public class Q1 {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.printf("bunny_ears(%d) -> %d\n", i, bunny_ears(i));
        }
    }

    public static int bunny_ears(int n) {
        if (n <= 0) {
            return 0;
        }
        else {
            return (n % 2 == 0 ? 3 : 2) + bunny_ears(n - 1);
        }
    }

}
