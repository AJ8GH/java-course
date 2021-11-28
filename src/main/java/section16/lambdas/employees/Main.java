package section16.lambdas.employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Tim Buchalka", 45);
        Employee jack = new Employee("Jack Hill", 32);
        Employee snow = new Employee("Snow White", 22);
        Employee red = new Employee("Red RidingHood", 25);
        Employee charming = new Employee("Prince Charming", 36);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);
        employees.add(red);
        employees.add(charming);

        // no lambda
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return 0;
            }
        });

        // static collections sort with lambda
        Collections.sort(employees, (e1, e2) ->  {
            return e1.getName().compareTo(e2.getName());
        });

        // static collections sort with static comparator comparing, and employee method ref
        Collections.sort(employees, Comparator.comparing(Employee::getName));


        // list.sort with lambda
        employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));

        // list.sort with method ref
        employees.sort(Comparator.comparing(Employee::getName));

        System.out.println(employees);

        String s = upperConcat((s1, s2) -> s1.toUpperCase() + s2.toUpperCase(),
                john.getName(),
                tim.getName());

        System.out.println(s);

        AnotherClass anotherClass = new AnotherClass();
        anotherClass.concatStrings(anotherClass.uc, "string1", "string2");

        anotherClass.printValue();

        for (Employee employee : employees) {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
            new Thread(() -> System.out.println(employee.getAge())).start();
        }
    }

    private static String upperConcat(UpperConcat uc,
                                           String s1,
                                           String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}
