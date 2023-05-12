import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

    private static Random random = new Random();
    
    public static Employee generateEmployee() {
        String[] names = new String[]{"Василий", "Пётр", "Юрий", "Григорий", "Евгений", "Николай", "Алексей"
                , "Максим", "Роман", "Андрей"};
        String[] surnames = new String[]{"Васильев", "Петров", "Юрьев", "Глебов", "Евгеньев", "Николаев"
                , "Алексеев", "Максимов", "Романов", "Андреев"};

        int salary = random.nextInt(3000, 5000);
        int salaryIndex = random.nextInt(1, 31);
        int age = random.nextInt(18, 60);
        int coin = random.nextInt(0, 2);

        if (coin == 1) {
            return new Worker(names[random.nextInt(10)], surnames[random.nextInt(10)]
                    , age, salary);
        } else {
            return new Freelancer(names[random.nextInt(10)], surnames[random.nextInt(10)]
                    , age, salary, salaryIndex);
        }
    }

    public static void main(String[] args) {


        Employee[] employees = new Employee[10];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = generateEmployee();
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        Arrays.sort(employees, new SurNameComparator());

        System.out.println("\n*** Отсортированный массив сотрудников по фамилии ***\n");

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        Arrays.sort(employees);

        System.out.println("\n*** Отсортированный массив сотрудников по величине дохода ***\n");

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        Arrays.sort(employees, new AgeComparator());

        System.out.println("\n*** Отсортированный массив сотрудников по возрасту ***\n");

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}

class SalaryComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o1.calculateSalary(), o2.calculateSalary());
    }
}

class SurNameComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.surName.compareTo(o2.surName);
    }
}

class AgeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o1.age, o2.age);
    }
}

abstract class Employee implements Comparable<Employee> {

    protected String firstName;
    protected String surName;
    protected int age;
    protected int salary;

    public Employee(String firstName, String surName, int age, int salary) {
        this.firstName = firstName;
        this.surName = surName;
        this.age = age;
        this.salary = salary;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return String.format("Сотрудник: %s %s; Возраст: %d; Ставка: %d; Среднемесячная заработная плата: %.0f\n"
                , surName, firstName, age, salary, calculateSalary());
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(calculateSalary(), o.calculateSalary());
    }
}

class Worker extends Employee {

    int salaryIndex;

    public Worker(String firstName, String surName, int age, int salary) {
        super(firstName, surName, age, salary);
        this.salaryIndex = 21;
    }

    @Override
    public double calculateSalary() {
        return salary * salaryIndex;
    }

    @Override
    public String toString() {
        return String.format("Сотрудник: %s %s; Возраст: %d; Ставка: %d; Ежемесячная заработная плата: %.0f\n"
                , surName, firstName, age, salary, calculateSalary());
    }
}

class Freelancer extends Employee {

    int salaryIndex;

    public Freelancer(String firstName, String surName, int age, int salary, int salaryIndex) {
        super(firstName, surName, age, salary);
        this.salaryIndex = salaryIndex;
    }

    @Override
    public double calculateSalary() {
        return salary * salaryIndex;
    }

    @Override
    public String toString() {
        return String.format("Сотрудник: %s %s; Возраст: %d; Ставка: %d; Кол-во рабочих дней: %d;" +
                        " Заработная плата в этом месяце: %.0f\n"
                , surName, firstName, age, salary, salaryIndex, calculateSalary());
    }
}
