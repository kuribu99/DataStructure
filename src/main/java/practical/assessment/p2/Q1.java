package practical.assessment.p2;

/**
 *
 * @author Kong My
 */
public class Q1 {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.printf("calculateInvestment(%d) -> %.2f\n", i, calculateInvestment(1000, 10, i));
        }
    }

    public static double calculateInvestment(double investAmount, int interestRate, int year) {
        if (year <= 0) {
            return investAmount;
        }
        else {
            return (100 + interestRate) * 0.01 * calculateInvestment(investAmount, interestRate, year - 1);
        }
    }

}
