package Task1;

import java.io.*;

public class General {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir") + File.separator, "Corporation.txt");
        File file1 = new File(System.getProperty("user.dir") + File.separator, "CorporationWithChangedSalary.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             FileInputStream fileInputStream = new FileInputStream(file);
             FileOutputStream fileOutputStream1 = new FileOutputStream(file1);
             FileInputStream fileInputStream1 = new FileInputStream(file1)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (!file1.exists()) {
                file1.createNewFile();
            }
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(employee());
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Employee employee = (Employee) objectInputStream.readObject();
            changeSalary(employee);
            ObjectOutputStream outputStream1 = new ObjectOutputStream(fileOutputStream1);
            outputStream1.writeObject(employee);
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
            Employee changedEmployee = (Employee) objectInputStream1.readObject();
            System.out.println("изменённая зарплата, прочитанная из файла: " + changedEmployee.getSalary());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Employee employee() throws IOException {
        System.out.println("Введите фамилию:");
        String surname = reader.readLine();
        System.out.println("Введите возраст:");
        int age = Integer.parseInt(reader.readLine());
        System.out.println("Введите должность:");
        String jobTitle = reader.readLine();
        System.out.println("Введите оклад:");
        double salary = Double.parseDouble(reader.readLine());
        return new Employee(surname, age, jobTitle, salary);
    }

    static Employee changeSalary(Employee employee) throws IOException {
        System.out.println("На сколько увеличить оклад?");
        employee.setSalary(employee.getSalary() + Double.parseDouble(reader.readLine()));
        return employee;
    }
}