package section16.lambdas.streams;

import section16.lambdas.employees.Department;
import section16.lambdas.employees.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final List<Department> DEPARTMENTS = new ArrayList<>();

    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee jane = new Employee("Jane Deer", 25);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        Department hr = new Department("Human Resources");
        hr.addEmployee(jane);
        hr.addEmployee(jack);
        hr.addEmployee(snow);
        hr.addEmployee(snow);

        Department accounting = new Department("Accounting");
        accounting.addEmployee(john);

        DEPARTMENTS.add(hr);
        DEPARTMENTS.add(accounting);

        DEPARTMENTS.stream().flatMap(department -> department.getEmployees().stream())
                .forEach(System.out::println);

        Map<Integer, List<Employee>> groupedByAge = groupedByAge();
        System.out.println(groupedByAge);

        reduceAgesToPrintYoungest();

        Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
                .filter((s -> {
                    System.out.println(s);
                    return s.length() == 3;
                })).count();
    }

    private static Map<Integer, List<Employee>> groupedByAge() {
        return DEPARTMENTS.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(Employee::getAge));
    }

    private static void reduceAgesToPrintYoungest() {
        DEPARTMENTS.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1, e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);
    }
}
