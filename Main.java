import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Time {
    private int seconds;

    public Time(int seconds) {
        this.seconds = seconds % (24 * 60 * 60);
        if (this.seconds < 0) {
            this.seconds += 24 * 60 * 60;
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds % (24 * 60 * 60);
        if (this.seconds < 0) {
            this.seconds += 24 * 60 * 60;
        }
    }

    public int getHours() {
        return seconds / 3600;
    }

    public int getMinutes() {
        return (seconds % 3600) / 60;
    }

    public int getSecondsOfMinute() {
        return seconds % 60;
    }

    public String toString() {
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}

class House {
    private final int floors;

    public House(int floors) {
        this.floors = floors;
    }

    public int getFloors() {
        return floors;
    }

    public String toString() {
        return "Дом с " + floors + " " + getFloorWord(floors);
    }

    private String getFloorWord(int n) {
        int abs = Math.abs(n);
        if (abs==1) return "этажом";
        return "этажами";
    }
}

class Department {
    private String name;
    private Employee head;
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getHead() {
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
        }
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public String toString() {
        return name;
    }
}

class Employee {
    private String name;
    private Department department;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
        if (department != null) {
            department.addEmployee(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        if (this.department != null) {
            this.department.removeEmployee(this);
        }
        this.department = department;
        if (department != null) {
            department.addEmployee(this);
        }
    }

    public List<Employee> getDepartmentEmployees() {
        if (department != null) {
            return department.getEmployees();
        }
        return new ArrayList<>();
    }

    public String toString() {
        if (department != null && department.getHead() == this) {
            return name + " начальник отдела " + department.getName();
        }
        if (department != null) {
            String headName = (department.getHead() != null) ? department.getHead().getName() : "не назначен";
            return name + " работает в отделе " + department.getName() + ", начальник которого " + headName;
        }
        return name;
    }
}

class Gun {
    private int bullets;

    public Gun() {
        this.bullets = 5;
    }

    public Gun(int bullets) {
        this.bullets = bullets;
    }

    public int getBullets() {
        return bullets;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }

    public void shoot() {
        if (bullets > 0) {
            System.out.println("Бах!");
            bullets--;
        } else {
            System.out.println("Клац!");
        }
    }

    public String toString() {
        return "Пистолет с " + bullets + " патронами";
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\nЛабораторная работа №2. Вариант 6");
            System.out.println("1. Задание 1.4");
            System.out.println("2. Задание 1.5");
            System.out.println("3. Задание 2.4");
            System.out.println("4. Задание 3.4");
            System.out.println("5. Задание 4.3");
            System.out.println("6. Задание 5.1");
            System.out.println("7. Запустить все задания");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт: ");

            int choice = readInt();

            switch (choice) {
                case 1: task1_4(); break;
                case 2: task1_5(); break;
                case 3: task2_4(); break;
                case 4: task3_4(); break;
                case 5: task4_3(); break;
                case 6: task5_1(); break;
                case 7: runAll(); break;
                case 0:
                    running = false;
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Неверный пункт меню!");
            }
        }
        scanner.close();
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Ошибка! Введите целое число: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static int readIntMin(int min) {
        int value;
        while (true) {
            value = readInt();
            if (value >= min) return value;
            System.out.print("Значение должно быть не меньше " + min + ". Повторите: ");
        }
    }

    private static void task1_4() {
        System.out.println("\nЗадание 1.4 — Время");
        System.out.println("Демонстрация:");
        System.out.println("10 секунд    -> " + new Time(10));
        System.out.println("10000 секунд -> " + new Time(10000));
        System.out.println("100000 секунд-> " + new Time(100000));

        System.out.print("\nВведите количество секунд: ");
        int seconds = readIntMin(0);
        Time t = new Time(seconds);
        System.out.println("Время в формате ЧЧ:ММ:СС -> " + t);
    }

    private static void task1_5() {
        System.out.println("\nЗадание 1.5 — Дом");
        System.out.println("Демонстрация:");
        System.out.println("  " + new House(1));
        System.out.println("  " + new House(5));
        System.out.println("  " + new House(23));

        System.out.print("\nВведите количество этажей: ");
        int floors = readIntMin(1);
        System.out.println("  " + new House(floors));
    }

    private static void task2_4() {
        System.out.println("\nЗадание 2.4 — Сотрудники и отделы");
        Department it = new Department("IT");
        Employee petrov  = new Employee("Петров", it);
        Employee kozlov  = new Employee("Козлов", it);
        Employee sidorov = new Employee("Сидоров", it);
        it.setHead(kozlov);

        System.out.println("Результат (у всех один отдел и начальник):");
        System.out.println("  " + petrov);
        System.out.println("  " + kozlov);
        System.out.println("  " + sidorov);
    }

    private static void task3_4() {
        System.out.println("\nЗадание 3.4 — Сотрудники и отделы (список сотрудников)");
        Department it = new Department("IT");
        Employee petrov  = new Employee("Петров", it);
        Employee kozlov  = new Employee("Козлов", it);
        Employee sidorov = new Employee("Сидоров", it);
        it.setHead(kozlov);

        System.out.println("Сотрудники отдела IT (получены через ссылку на Петрова):");
        for (Employee emp : petrov.getDepartmentEmployees()) {
            System.out.println("  - " + emp.getName());
        }

        System.out.println("\nСотрудники отдела IT (получены через ссылку на Козлова):");
        for (Employee emp : kozlov.getDepartmentEmployees()) {
            System.out.println("  - " + emp.getName());
        }

        System.out.println("\nПереводим Сидорова в другой отдел...");
        Department hr = new Department("HR");
        sidorov.setDepartment(hr);

        System.out.println("Сотрудники отдела IT после перевода:");
        for (Employee emp : petrov.getDepartmentEmployees()) {
            System.out.println("  - " + emp.getName());
        }

        System.out.println("Сотрудники отдела HR:");
        for (Employee emp : sidorov.getDepartmentEmployees()) {
            System.out.println("  - " + emp.getName());
        }
    }

    private static void task4_3() {
        System.out.println("\nЗадание 4.3 — Создаем Дом");
        House h1 = new House(2);
        House h2 = new House(35);
        House h3 = new House(91);

        System.out.println("Созданные дома:");
        System.out.println("  " + h1);
        System.out.println("  " + h2);
        System.out.println("  " + h3);

        System.out.println("\nПопытка изменить количество этажей у дома h1:");
        System.out.println("  До 'изменения': " + h1);
        System.out.println("  Изменить количество этажей после создания невозможно.");
        System.out.println("  После попытки:  " + h1);
    }

    private static void task5_1() {
        System.out.println("\nЗадание 5.1 — Пистолет");
        Gun gun = new Gun(3);
        System.out.println("Создан пистолет: " + gun);
        System.out.println("Стреляем 5 раз:");
        for (int i = 0; i < 5; i++) {
            System.out.print("  Выстрел " + (i + 1) + " -> ");
            gun.shoot();
        }
        System.out.println("После выстрелов: " + gun);
    }

    private static void runAll() {
        task1_4();
        task1_5();
        task2_4();
        task3_4();
        task4_3();
        task5_1();
    }
}
