package lib;

public class TaxFunction {

    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int monthWorkingInYear, int annualDeductible, boolean isSingle, int numberOfChildren) {
        validateWorkingMonths(monthWorkingInYear);

        int annualIncome = calculateAnnualIncome(monthlySalary, otherMonthlyIncome, monthWorkingInYear);
        int baseTax = calculateBaseTax(annualIncome);
        int deductions = calculateDeductions(isSingle, numberOfChildren, annualDeductible);

        int finalTax = baseTax - deductions;
        return Math.max(finalTax, 0);
    }

    private static void validateWorkingMonths(int months) {
        if (months > 12 || months < 0) {
            System.err.println("Invalid working month");
        }
    }

    private static int calculateAnnualIncome(int monthlySalary, int otherIncome, int monthsWorked) {
        return (monthlySalary + otherIncome) * monthsWorked;
    }

    private static int calculateBaseTax(int income) {
        if (income <= 5000000) {
            return 0;
        } else if (income <= 10000000) {
            return (int) (0.05 * (income - 5000000));
        } else {
            return (int) (0.05 * 5000000 + 0.1 * (income - 10000000));
        }
    }

    private static int calculateDeductions(boolean isSingle, int numChildren, int deductible) {
        int deductions = 0;

        if (isSingle) {
            deductions += 1000000;
        }

        deductions += numChildren * 500000;
        deductions += deductible;

        return deductions;
    }
}
