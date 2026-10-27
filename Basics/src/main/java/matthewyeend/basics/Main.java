/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.basics;

import matthewyeend.basics.EmployeeReport.Employee;
import matthewyeend.basics.Shapes.Circle;
import matthewyeend.basics.Shapes.Rectangle;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author matthewyeend
 */
public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        Customer customer = new Customer("  Ada Lovelace ", "ada@example.com");
        System.out.println(customer);

        System.out.printf("Circle area: %.2f%n", Shapes.area(new Circle(2.0)));
        System.out.printf("Rectangle area: %.2f%n", Shapes.area(new Rectangle(3.0, 4.0)));

        List<Employee> staff = List.of(
                new Employee("Alice", "Engineering", new BigDecimal("52000")),
                new Employee("Bob", "Engineering", new BigDecimal("48000")),
                new Employee("Carol", "Finance", new BigDecimal("45000")));
        System.out.println(EmployeeReport.totalSalaryByDepartment(staff));

        String stored = PasswordHasher.hash("correct horse battery".toCharArray());
        System.out.println("Valid password accepted: "
                + PasswordHasher.verify("correct horse battery".toCharArray(), stored));
        System.out.println("Wrong password accepted: "
                + PasswordHasher.verify("wrong".toCharArray(), stored));
    }
}
