import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Company {
    private List<Employee> employeeList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    // Εισαγωγή υπαλλήλου στην εταιρεία
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    // Εισαγωγή έργου στον υπάλληλο
    public void addProjectToEmployee(String name, Project project) {
        // Εύρεση υπαλλήλου με το όνομα name
        Employee employee = employeeList.stream()
                .filter(e -> (e.getName().equals(name)))
                .findFirst()
                .orElse(null);

        // Προσθήκη του έργου στον employee
        employee.addProject(project);
    }

    // Υπολογισμός μηνιαίας μισθοδοσίας
    public void calcPayroll() {
        int salarySum = 0;

        // Εκτύπωση μισθού για κάθε υπάλληλο και υπολογισμός του
        // συνολικού ποσού μισθοδοσίας για την εταιρεία
        for(Employee employee : employeeList) {
            PaymentType paymentType = employee.getPaymentType();

            System.out.println("Name: " + employee.getName() + " Salary: " + paymentType.getSalary(employee) + " euro");
            salarySum += paymentType.getSalary(employee);
        }

        System.out.println("Total monthly salaries: " + salarySum + " euro");
    }

    public String calcTotalPayroll() {
        int salarySum = 0;

        // Υπολογισμός του συνολικού ποσού μισθοδοσίας για την εταιρεία
        for(Employee employee : employeeList) {
            PaymentType paymentType = employee.getPaymentType();

            salarySum += paymentType.getSalary(employee);
        }

        String payroll = String.format("%d\n", salarySum);

        return payroll;
    }

    // Αποθήκευση μισθοδοσίας μήνα
    public void save(String payroll) {
        try {
            // Δημιουργία αρχείου
            FileWriter file = new FileWriter("payroll.txt");

            file.write(payroll);

            // Κλείσιμο αρχείου
            file.close();
        } catch (IOException e) {
            // Exception σε περίπτωση κάποιου σφάλματος στον χειρισμό του αρχείου
            System.out.println("Problem with file");
        }
    }

    // Εκτύπωση ετήσιας μισθοδοσίας
    public void annualPayroll() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("payroll.txt"));
            String line;
            int totalPayroll = 0;
            int i = 1;

            do {
                line = reader.readLine();
                if(line != null) {
                    System.out.println(String.format("## Month %d: %s euro", i++, line));
                    totalPayroll += Integer.parseInt(line);
                }

            } while(line != null);

            System.out.println("#### Total Payroll: " + totalPayroll + " euro");

            System.out.println();

            reader.close();
        } catch (Exception e) {
            System.out.print("Problem with file");
        }
    }

    // Εισαγωγή του εργαζόμενου σε projects
    private void addEmployeeToProjects(Employee employee) {
        int projectType;
        Project project = null;

        do {
            do {
                projectType = 0;

                System.out.println("Add employee to project 1. Development Project, 2. Technical Project, 3. Nothing");
                try {
                    projectType = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please give an integer");
                    input.next();
                }
            } while (projectType<1 || projectType>3);

            if (projectType == 3) {
                continue;
            }

            System.out.println("Give project name");
            String projectName = input.next();

            switch (projectType) {
                case 1: project = new DevelopmentProject(projectName); break;
                case 2: project = new TechnicalProject(projectName);
            }

            addProjectToEmployee(employee.getName(), project);

        } while ((projectType > 0) && (projectType < 3));
    }

    // Εισαγωγή ωρών εργασίας για τον εργαζόμενο
    private void addWorkingHoursToEmployee(Employee employee) {
        int hours = 0;

        System.out.println("Give working hours for employee");

        do {
            try {
                hours = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please give an integer");
                input.next();
            }
        } while (hours == 0);

        ((PerHour)employee.getPaymentType()).setHours(hours);
    }

    // Εισαγωγή μισθοδοσίας για κάθε μήνα
    public void addMonthlyPayroll() {
        boolean addingMonths;
        int month = 1;
        StringBuilder payrolls = new StringBuilder();

        do {
            System.out.println("###### Add data for month: " + month);

            for(Employee employee : employeeList) {
                System.out.println("## Employee name: " + employee.getName());

                if (employee.getPaymentType() instanceof Salary) {
                    addEmployeeToProjects(employee);
                } else {
                    addWorkingHoursToEmployee(employee);
                }
            }

            // Υπολογισμός συνολικής μισθοδοσίας του μήνα
            payrolls.append(calcTotalPayroll());

            month++;

            System.out.println("Do you want to add another month? (y/n)");
            addingMonths = (input.next().charAt(0) == 'y' && month<=12);
            System.out.println();
        } while (addingMonths);

        // Αποθήκευση των μισθοδοσιών σε αρχείο
        save(payrolls.toString());
    }

    // Εισαγωγή εργαζομένων
    public void addEmployees() {
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
                employeeTypeNumber = 0;
                System.out.println("Give number for employee type. 1: Developer, 2: Manager, 3: Analyst, 4: Technical");

                try {
                    employeeTypeNumber = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please give an integer");
                    input.next();
                }

            } while ((employeeTypeNumber > 4) || (employeeTypeNumber < 1));

            // Εισαγωγή τύπου μισθοδοσίας
            do {
                paymentTypeNumber = 0;
                System.out.println("Give number for payment type. 1: Salary, 2: Per Hour");

                try {
                    paymentTypeNumber = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please give an integer");
                    input.next();
                }
            } while ((paymentTypeNumber > 2) || (paymentTypeNumber < 1));

            // Δημιουργία αντικειμένου PaymentType
            switch (paymentTypeNumber) {
                case 1: paymentType = new Salary(); break;
                case 2: paymentType = new PerHour();
            }

            // Δημιουργία του αντικειμένου Employee
            switch (employeeTypeNumber) {
                case 1: employee = new Developer(name, paymentType); break;
                case 2: employee = new Manager(name, paymentType); break;
                case 3: employee = new Analyst(name, paymentType); break;
                case 4: employee = new Technical(name, paymentType);
            }

            // Προσθήκη εργαζομένου στην εταιρεία
            addEmployee(employee);

            // Ερώτηση για συνέχεια εισαγωγής εργαζομένων
            System.out.println("Do you want to add another employee? (y/n)");
            addingEmployees = input.next().charAt(0) == 'y';

            System.out.println();
        } while (addingEmployees);
    }
}
