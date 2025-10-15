
class Employee {

    String name;
    int id;
    double salary;

    Employee(String n, int id, double salary) {
        this.name = n;
        this.id = id;
        this.salary = salary;
    }

    void displayDetails() {
        System.out.println("NAME : " + this.name + ", ID : " + this.id + ", SALARY : " + this.salary);
    }
}

public class EmployeeDetails{
    public static void main(String args[]){
        Employee e1 = new Employee("Nayaj", 18, 500050);
        Employee e2 = new Employee("Pius", 69, 79993);
        e1.displayDetails();
        e2.displayDetails();
    }
}