import java.util.*;

public class Company {

    private double income;
    public ArrayList<Employee> salaryStaff = new ArrayList<>();

//комапаратор можно через лямбду:
//public Comparator<Employee> employeeSalaryComparator =
//          (o1, o2) -> Double.compare(o2.getMonthSalary(), o1.getMonthSalary());
//но оставил пока так...
    public Comparator<Employee> employeeSalaryComparator = new Comparator<Employee>() {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Double.compare(o2.getMonthSalary(), o1.getMonthSalary());
        }
    };

    public void hire(Employee employee) {
        salaryStaff.add(employee);
    }

    public void hireAll(Collection<Employee> employees) {
        salaryStaff.addAll(employees);
    }

    public void fire(Employee employee) {
        salaryStaff.remove(employee);
    }

    ArrayList<Employee> getTopSalaryStaff(int count) {
        salaryStaff.sort(employeeSalaryComparator);

        if (count < salaryStaff.size()) {
            return new ArrayList<>(salaryStaff.subList(0, count));
        }
        return salaryStaff;
    }

    ArrayList<Employee> getLowestSalaryStaff(int count) {
        salaryStaff.sort(employeeSalaryComparator.reversed());
        if (count < salaryStaff.size()) {
            return new ArrayList<>(salaryStaff.subList(0, count));
        }
        return salaryStaff;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
