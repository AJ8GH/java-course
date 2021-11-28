package section16.lambdas.functionalinterfaces;

import section16.lambdas.employees.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    private static final Random RANDOM = new Random();

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

        employees.forEach(employee -> {
            System.out.println(employee.getName());
            System.out.println(employee.getAge());
        });

        printEmployeesByAge(employees, employee -> employee.getAge() > 30);
        printEmployeesByAge(employees, employee -> employee.getAge() < 30);

        IntPredicate over15 = i -> i > 15;

            System.out.println(over15.test(10));
            System.out.println(over15.test(20));

        IntPredicate under100 = i -> i < 100;

            System.out.println(over15.and(under100).test(80));
            System.out.println(over15.and(under100).test(122));
            System.out.println(over15.and(under100).test(7));
            System.out.println(over15.and(under100).test(57));

            System.out.println(over15.or(under100).test(50));
            System.out.println(over15.or(under100).test(5));
            System.out.println(over15.or(under100).test(500));

        Supplier<Integer> randomSupplier = () -> RANDOM.nextInt(1000);

        for (int i = 0; i < 10; i++) {
            System.out.println(randomSupplier.get());
        }

        Function<Employee, String> getLastName = employee -> employee.getName().substring(employee.getName().indexOf(' ') + 1);

        String lastName = getLastName.apply(employees.get(1));
        System.out.println(lastName);

        Function<Employee, String> getFirstName = employee -> employee.getName().substring(0, employee.getName().indexOf(' '));

        for (Employee employee : employees) {
            if (RANDOM.nextBoolean()) {
                System.out.println(getFirstOrLastName(getFirstName, employee));
            } else {
                System.out.println(getFirstOrLastName(getLastName, employee));
            }
        }

        Function<Employee, String> upperCase = e -> e.getName().toUpperCase();
        Function<String, String> firstName = s1 -> s1.substring(0, s1.indexOf(' ') + 1);
        Function<Employee, String> chainedFunction = upperCase.andThen(firstName);

        System.out.println("=== CHAINED ===");
        System.out.println(chainedFunction.apply(charming));

        BiFunction<String, Employee, String> concatAge = (name, employee) -> name.concat(" " + employee.getAge());

        String upperName = chainedFunction.apply(snow);
        System.out.println(concatAge.apply(upperName, snow));

        IntUnaryOperator plusFive = n -> n + 5;
        System.out.println(plusFive.applyAsInt(5));

        Consumer<String> c1 = s -> s.toUpperCase();
        Consumer<String> c2 = s -> System.out.println(s);
        c1.andThen(c2).accept("hi");
    }

    private static String getFirstOrLastName(Function<Employee, String> function,
                                             Employee employee) {
        return function.apply(employee);
    }

    private static void printEmployeesByAge(List<Employee> employees,
        Predicate<Employee> ageCondition) {
        System.out.println("==============");
        employees.forEach(employee -> {
            if (ageCondition.test(employee)) {
                System.out.println(employee.getName());
            }
        });
    }
}
