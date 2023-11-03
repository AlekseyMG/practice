public class TopManager implements Employee {
    private final double companyIncome;
    private double monthSalary = 150000;

    public TopManager(Company company) {
        this.companyIncome = company.getIncome();
    }

    @Override
    public double getMonthSalary() {
        if (companyIncome > 10_000_000) {
            return monthSalary + monthSalary * 1.5;
        } else {
            return monthSalary;
        }
    }
}
