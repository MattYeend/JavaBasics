/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.basics;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author matthewyeend
 */
public final class EmployeeReport {

    private EmployeeReport() {
    }

    public record Employee(String name, String department, BigDecimal salary) {
    }

    public static Map<String, BigDecimal> totalSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.reducing(BigDecimal.ZERO, Employee::salary, BigDecimal::add)));
    }
}
