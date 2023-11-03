import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        //TODO Метод должен вернуть сотрудника с максимальной зарплатой среди тех,
        // кто пришёл в году, указанном в переменной year

        Date startDate = Date.from(Instant.parse(year + "-01-01T00:00:00Z"));
        Date endDate = Date.from(Instant.parse(year + "-12-31T23:59:59Z"));

        //Если бы getWorkStart() возвращал LocalDate, а не Date, то было бы проще
//        return staff.stream().filter(employee -> employee.getWorkStart().getYear() == year)
//                        .max(Comparator.comparing(Employee::getSalary)).get();

        //фильтр решил разделить, но можно и совместить в один вот так
//        return staff.stream()
//                .filter(employee ->
//                        employee.getWorkStart().after(startDate)
//                        && employee.getWorkStart().before(endDate))
//                .max(Comparator.comparing(Employee::getSalary)).get();

        return staff.stream()
                .filter(employee -> employee.getWorkStart().after(startDate))
                .filter(employee -> employee.getWorkStart().before(endDate))
                .max(Comparator.comparing(Employee::getSalary)).get();
    }
}