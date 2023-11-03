public class Manager implements Employee {

    double monthSalary = 100000;
    double bonus = (Math.random() / 4 * 100_000 + 115_000) * 0.05;

    @Override
    public double getMonthSalary() {
        return monthSalary + bonus;
    }
}
