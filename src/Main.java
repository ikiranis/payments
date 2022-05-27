import java.sql.Struct;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Company c = new Company();
        Scanner input = new Scanner(System.in);
        boolean addingEmployees = true;

        do {
            System.out.println("Give employee name");
            String name = input.next();
            System.out.println("Give number for employee type. 1: Developer, 2: Manager, 3: Analyst, 4: Technical");
            int employeeType = input.nextInt();
            System.out.println("Give number for payment type. 1: Salary, 2: Per Hour");
            int paymentType = input.nextInt();

            System.out.println("Do you want to add another employee? (Y/N)");
            addingEmployees = input.next().charAt(0) == 'Y';

        } while (addingEmployees);


//        c.addEmployee(new Developer("AA", new Salary()));
//        c.addEmployee(new Manager("BB", new Salary()));
//        c.addEmployee(new Analyst("VV", new PerHour(15)));
//        c.addEmployee(new Technical("CC", new Salary()));
//        c.addEmployee(new Developer("KK", new Salary()));
//        c.addProjectToEmployee("AA", new DevelopmentProject("Website for UoM"));
//        c.addProjectToEmployee("BB", new DevelopmentProject("Website for UoM "));
//        c.addProjectToEmployee("VV", new DevelopmentProject("Website for UoM "));
//        c.addProjectToEmployee("CC", new TechnicalProject("Network setup for EAP"));
//        c.addProjectToEmployee("KK", new DevelopmentProject("Website for UoM"));
//        c.addProjectToEmployee("BB", new TechnicalProject("Network setup for EAP"));
//        c.calcPayroll();
    }
}
