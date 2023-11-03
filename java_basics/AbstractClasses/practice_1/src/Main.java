import java.text.NumberFormat;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//Сделайте возможным создание разных экземпляров компании со своим списком сотрудников и доходов.

//Для демонстрации и тестирования работы ваших классов:
//
//Создайте и наймите в компанию: 180 операторов Operator, 80 менеджеров по продажам Manager, 10 топ-менеджеров TopManager.
//Распечатайте список из 10–15 самых высоких зарплат в компании.
//Распечатайте список из 30 самых низких зарплат в компании.
//Увольте 50% сотрудников.
//Распечатайте список из 10–15 самых высоких зарплат в компании.
//Распечатайте список из 30 самых низких зарплат в компании.
//
//Примеры вывода списка зарплат
//
//Список из пяти зарплат по убыванию:
//
//230 000 руб.
//178 000 руб.
//165 870 руб.
//123 000 руб.
//117 900 руб.

        Company company1 = new Company();
//        Company company2 = new Company();
        company1.setIncome(11_000_000);
//        company2.setIncome(9_000_000);

        generateCompanyEmployees(company1, 10, 80, 180);
//        generateCompanyEmployees(company2, 2, 4, 1);

        printTopSalaryStaff(company1, 15); //список из 10–15 самых высоких зарплат
        System.out.println();
        printLowestSalaryStaff(company1, 30); //список из 30 самых низких зарплат

        fireEmployees(company1, company1.salaryStaff.size() / 2); //Увольте 50% сотрудников

        System.out.println("------------");
        printTopSalaryStaff(company1, 15); //список из 10–15 самых высоких зарплат
        System.out.println();
        printLowestSalaryStaff(company1, 30); //список из 30 самых низких зарплат

    }

    private static void printTopSalaryStaff(Company company, int count) {
        printEmployees(company.getTopSalaryStaff(count));
    }

    private static void printLowestSalaryStaff(Company company, int count) {
        printEmployees(company.getLowestSalaryStaff(count));
    }

    private static void printEmployees(ArrayList<Employee> employees) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        long currentEmployeeSalary;
        for (Employee employee : employees) {
            currentEmployeeSalary = Math.round(employee.getMonthSalary());
            System.out.println(numberFormat.format(currentEmployeeSalary) + " руб.");
        }
    }

    private static void generateCompanyEmployees(
            Company company, int topManagers, int managers, int operators) {
        for (int i = 0; i < topManagers; i++) {
            company.hire(new TopManager(company));
        }
        for (int i = 0; i < managers; i++) {
            company.hire(new Manager());
        }
        for (int i = 0; i < operators; i++) {
            company.hire(new Operator());
        }
    }

    private static void fireEmployees(Company company, int count) {
        if (count >= company.salaryStaff.size()) {
            count = company.salaryStaff.size();
        }
        for (int i = 0; i < count; i++) {
            company.fire(company.salaryStaff.get(i));
        }
    }
}