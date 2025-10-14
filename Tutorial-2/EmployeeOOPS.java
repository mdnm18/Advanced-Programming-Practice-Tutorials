
class Employee {

    private String names;
    private double salary;

    public Employee(String name, double salary) {
        this.names = name;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return names;
    }
}

public class EmployeeOOPS {

    public static void main(String args[]) {
        Employee[] employees = {
            new Employee("Nayaj", 55000.00),
            new Employee("Virat", 487464.9),
            new Employee("Rohit", 48463.9)
        };
        Employee highestPaid = employees[0];
        for (int i = 1; i < employees.length; i++) {
            if (employees[i].getSalary() > highestPaid.getSalary()) {
                highestPaid = employees[i];
            }
        }
        System.out.println("HIGHEST PAID: " + highestPaid.getName() + " with ₹" + highestPaid.getSalary());
    }
}
