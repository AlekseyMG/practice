import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        sortBySalaryAndAlphabet(staff);
        for (Employee employee : staff) System.out.println(employee);    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        //TODO Метод должен отсортировать сотрудников по заработной плате и алфавиту.
        Comparator<Employee> salaryComparator = (o1, o2) -> {
            int compare = o1.getSalary().compareTo(o2.getSalary());
            if (compare == 0)
                return o1.getName().compareTo(o2.getName());
            return compare;
        };
        staff.sort(salaryComparator);
    }
}