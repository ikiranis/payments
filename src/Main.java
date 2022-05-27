import java.util.Scanner;

public class Main {
    private static Company c = new Company();

    // Εισαγωγή εργαζομένων
    private static void addEmployees() {
        Scanner input = new Scanner(System.in);
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

    public static void main(String[] args) {

        addEmployees();

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
