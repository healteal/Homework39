package Task2;

import java.io.*;
import java.util.ArrayList;

public class General {
    static ArrayList<Employee> employees = new ArrayList<>();
    static EmployeeCollection collection = new EmployeeCollection(employees);
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static File file = new File(System.getProperty("user.dir"), "CorporationTask2.txt");

    public static void main(String[] args) {
        if (!file.exists()) {
            try {
                file.createNewFile();
                employees.add(addEmployee());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Коллекция пуста.");
        } else {
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                collection = (EmployeeCollection) objectInputStream.readObject();
                employees = collection.getEmployees();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

        }
        try {
            int choose;
            while (true) {
                System.out.println("""
                        1. Ввод нового сотрудника.
                        2. Редактирование сотрудника.
                        3. Удаление сотрудника.
                        4. Информация о сотруднике.
                        5. Вывод информации о сотрудниках по параметру.
                        6. Сохранение изменений в файл.
                        7. Выход.""");
                choose = readerInt();
                choose(choose);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void choose(int choose) throws IOException {
        switch (choose) {
            case 1: {
                employees.add(addEmployee());
                System.out.println();
                break;
            }
            case 2: {
                modifyEmployee();
                System.out.println();
                break;
            }
            case 3: {
                System.out.println("Введите фамилию сотрудника:");
                String surname = reader.readLine();
                employees.remove(employees.stream().filter(x -> x.getSurname().equals(surname)).findAny().get());
                System.out.println();
                break;
            }
            case 4: {
                System.out.println("Введите фамилию сотрудника:");
                String surname = reader.readLine();
                employees.stream().filter(x -> x.getSurname().equals(surname)).forEach(System.out::println);
                System.out.println();
                break;
            }
            case 5: {
                showByParameter();
                break;
            }
            case 6: {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(collection);
                System.out.println();
                break;
            }
            case 7: {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(collection);
                System.exit(1);
            }
            default: {
                System.out.println("Неверный ввод.\n");
            }
        }
    }

    private static void showByParameter() throws IOException {
        System.out.println("""
                Выберите поле, для поиска:
                1. Возраст
                2. Должность
                3. Зарплата""");
        int chooseField = readerInt();
        switch (chooseField) {
            case 1: {
                System.out.println("Введите возраст:");
                int age = readerInt();
                employees.stream().filter(x -> x.getAge() == age).forEach(System.out::println);
                break;
            }
            case 2: {
                System.out.println("Введете должность:");
                String jobTitle = reader.readLine();
                employees.stream().filter(x -> x.getJobTitle().equals(jobTitle)).forEach(System.out::println);
                break;
            }
            case 3: {
                System.out.println("Введите зарплату:");
                double salary = readerDouble();
                employees.stream().filter(x -> x.getSalary() == salary).forEach(System.out::println);
                break;
            }
            default:
                System.out.println("Неверный ввод");
        }
    }

    private static void modifyEmployee() throws IOException {
        System.out.println("Введите фамилию сотрудника:");
        String surname = reader.readLine();
        Employee tempObject = employees.stream().filter(x -> x.getSurname().equals(surname)).findAny().get();
        employees.remove(tempObject);
        System.out.println("""
                Выберите поле, которое хотите изменить:
                1. Возраст
                2. Должность
                3. Зарплата""");
        int chooseField = readerInt();
        switch (chooseField) {
            case 1: {
                System.out.println("Введите возраст:");
                int age = readerInt();
                tempObject.setAge(age);
                employees.add(tempObject);
                break;
            }
            case 2: {
                System.out.println("Введете должность:");
                String jobTitle = reader.readLine();
                tempObject.setJobTitle(jobTitle);
                break;
            }
            case 3: {
                System.out.println("Введите зарплату:");
                double salary = readerDouble();
                tempObject.setSalary(salary);
                break;
            }
            default:
                System.out.println("Неверный ввод");
        }
    }

    private static Employee addEmployee() throws IOException {
        System.out.println("Введите фамилию сотрудника:");
        String surname = reader.readLine();
        System.out.println("Введите возраст:");
        int age = Integer.parseInt(reader.readLine());
        System.out.println("Введите должность:");
        String jobTitle = reader.readLine();
        System.out.println("Введите зарплату:");
        double salary = Double.parseDouble(reader.readLine());
        return new Employee(surname, age, jobTitle, salary);
    }

    private static int readerInt() {
        while (true) {
            try {
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Неверный ввод. Введите ещё раз:");
            }
        }
    }

    private static double readerDouble() {
        while (true) {
            try {
                return Double.parseDouble(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Неверный ввод. Введите ещё раз:");
            }
        }
    }
}
