import java.util.List;
import java.util.Scanner;

public class Main {
    private static Company c = new Company();
    private static Scanner input = new Scanner(System.in);

    // Εισαγωγή εργαζομένων
    private static void addEmployees() {
        boolean addingEmployees;

        // Εισαγωγή εργαζόμενων μέχρι να επιλεχθεί Ν, στην αντίστοιχη ερώτηση
        do {
            PaymentType paymentType = null;
            Employee employee = null;
            int employeeTypeNumber;
            int paymentTypeNumber;

            // Εισαγωγή ονόματος
            System.out.println("Give employee name");
            String name = input.next();

            // Εισαγωγή τύπου εργαζόμενου
            do {
                System.out.println("Give number for employee type. 1: Developer, 2: Manager, 3: Analyst, 4: Technical");
                employeeTypeNumber = input.nextInt();
            } while ((employeeTypeNumber > 4) || (employeeTypeNumber < 1));

            // Εισαγωγή τύπου μισθοδοσίας
            do {
                System.out.println("Give number for payment type. 1: Salary, 2: Per Hour");
                paymentTypeNumber = input.nextInt();
            } while ((paymentTypeNumber > 2) || (paymentTypeNumber < 1));

            // Δημιουργία αντικειμένου PaymentType
            switch (paymentTypeNumber) {
                case 1: paymentType = new Salary(); break;
                case 2: paymentType = new PerHour(0);
            }

            // Δημιουργία του αντικειμένου Employee
            switch (employeeTypeNumber) {
                case 1: employee = new Developer(name, paymentType); break;
                case 2: employee = new Manager(name, paymentType); break;
                case 3: employee = new Analyst(name, paymentType); break;
                case 4: employee = new Technical(name, paymentType);
            }

            // Προσθήκη εργαζομένου στην εταιρεία
            c.addEmployee(employee);

            // Ερώτηση για συνέχεια εισαγωγής εργαζομένων
            System.out.println("Do you want to add another employee? (y/n)");
            addingEmployees = input.next().charAt(0) == 'y';
        } while (addingEmployees);
    }

    // Εισαγωγή του εργαζόμενου σε projects
    private static void addEmployeeToProjects(Employee employee) {
        int projectType;
        Project project = null;

        do {
            System.out.println("Add employee to project 1. Development Project, 2. Technical Project, 3. Nothing");
            projectType = input.nextInt();

            if (projectType == 3) {
                continue;
            }

            System.out.println("Give project name");
            String projectName = input.next();

            switch (projectType) {
                case 1: project = new DevelopmentProject(projectName); break;
                case 2: project = new TechnicalProject(projectName);
            }

            c.addProjectToEmployee(employee.getName(), project);

        } while ((projectType > 0) && (projectType < 3));
    }

    // Εισαγωγή ωρών εργασίας για τον εργαζόμενο
    private static void addWorkingHoursToEmployee(Employee employee) {
        System.out.println("Give working hours for employee");
        int hours = input.nextInt();

        ((PerHour)employee.getPaymentType()).setHours(hours);
    }

    // Εισαγωγή μισθοδοσίας για κάθε μήνα
    private static void addMonthlyPayroll() {
        List<Employee> employeeList = c.getEmployeeList();
        boolean addingMonths;
        int month = 1;

        do {
            System.out.println("Add data for month: " + month);

            for(Employee employee : employeeList) {
                System.out.println("Employee name: " + employee.getName());

                if (employee.getPaymentType() instanceof Salary) {
                    addEmployeeToProjects(employee);
                } else {
                    addWorkingHoursToEmployee(employee);
                }
            }

            month++;

            System.out.println("Do you want to add another month? (y/n)");
            addingMonths = (input.next().charAt(0) == 'y' && month<=12);
        } while (addingMonths);
    }

    public static void main(String[] args) {

        addEmployees();

        addMonthlyPayroll();

        c.calcPayroll();

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

    }
}
