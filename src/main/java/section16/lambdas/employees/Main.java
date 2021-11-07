package section16.lambdas.employees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 45);
        Employee jack = new Employee("Jack Hill", 32);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

        // lambda
        employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));

        // method ref
        employees.sort(Comparator.comparing(Employee::getName));

        System.out.println(employees);
    }
}
