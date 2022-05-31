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

    // Υπολογισμός συνολικού ποσού μισθοδοσίας του μήνα
    public String calcTotalPayroll() {
        int salarySum = 0;

        // Πρόσθεση του μισθού του κάθε υπαλλήλου
        for(Employee employee : employeeList) {
            PaymentType paymentType = employee.getPaymentType();

            salarySum += paymentType.getSalary(employee);
        }

        // Επιστροφή του τελικού ποσού σε string
        return String.format("%d\n", salarySum);
    }

    // Αποθήκευση μισθοδοσίας μήνα
    public void save(String payroll) {
        try {
            // Δημιουργία αρχείου
            FileWriter file = new FileWriter("payroll.txt");

            // Εγγραφή στο αρχείο
            file.write(payroll);

            // Κλείσιμο αρχείου
            file.close();
        } catch (IOException e) {
            // Exception σε περίπτωση κάποιου σφάλματος στον χειρισμό του αρχείου
            System.out.println("Problem with file");
        }
    }

    // Εκτύπωση ετήσιας μισθοδοσίας διαβάζοντας το αρχείο
    public void annualPayroll() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("payroll.txt"));
            String line;
            int totalPayroll = 0;
            int i = 1;

            // Διάβασμα του αρχείου γραμμή προς γραμμή και εκτύπωση της
            do {
                line = reader.readLine();
                if(line != null) {
                    System.out.println(String.format("## Month %d: %s euro", i++, line));
                    totalPayroll += Integer.parseInt(line);
                }

            } while(line != null);

            // Εκτύπωση του συνολικού ποσού ετήσιας μισθοδοσίας
            System.out.println("#### Total Payroll: " + totalPayroll + " euro");

            System.out.println();

            reader.close();
        } catch (IOException e) {
            System.out.print("Problem with file");
        }
    }

    // Εισαγωγή του εργαζόμενου σε projects
    private void addEmployeeToProjects(Employee employee) {
        int projectType;
        Project project = null;

        // Προσθήκη του εργαζόμενου σε projects μέχρι να απαντηθεί 3
        do {
            // Επιλογή τύπου project, με αμυντικό προγραμματισμό
            do {
                projectType = 0;
                System.out.println("Add employee to project 1. Development Project, 2. Technical Project, 3. Nothing");

                // Έλεγχος αν έχει δοθεί integer
                try {
                    projectType = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please give an integer");
                    input.next();
                }
            } while (projectType<1 || projectType>3);

            // Αν δοθεί 3, τότε βγαίνει από το loop
            if (projectType == 3) {
                continue;
            }

            // Εισαγωγή ονόματος project
            System.out.println("Give project name");
            String projectName = input.next();

            // Δημιουργία αντικείμενου για το project
            switch (projectType) {
                case 1: project = new DevelopmentProject(projectName); break;
                case 2: project = new TechnicalProject(projectName);
            }

            // Προσθήκη του εργαζόμενου στo project
            addProjectToEmployee(employee.getName(), project);

        } while ((projectType > 0) && (projectType < 3));
    }

    // Εισαγωγή ωρών εργασίας για τον εργαζόμενο
    private void addWorkingHoursToEmployee(Employee employee) {
        int hours = 0;

        System.out.println("Give working hours for employee");

        // Εισαγωγή ωρών εργασίας με αμυντικό προγραμματισμό
        do {
            try {
                hours = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please give an integer");
                input.next();
            }
        } while (hours == 0);

        // Θέτει τις ώρες εργασίας για τον εργαζόμενο
        ((PerHour)employee.getPaymentType()).setHours(hours);
    }

    // Εισαγωγή μισθοδοσίας για κάθε μήνα
    public void addMonthlyPayroll() {
        boolean addingMonths;
        int month = 1;
        StringBuilder payrolls = new StringBuilder();

        // Προσθέτει δεδομένα για κάθε μήνα μέχρι να δοθεί "n" ή δοθούν πάνω από 12 μήνες
        do {
            System.out.println("###### Add data for month: " + month);

            // Για κάθε εργαζόμενο ζητούνται τα αντίστοιχα data,
            // ανάλογα τον τύπο πληρωμής που έχει
            for(Employee employee : employeeList) {
                System.out.println("## Employee name: " + employee.getName());

                // Αν έχει τύπο Salary, ζητούνται τα projects στα οποία συμμετέχει
                if (employee.getPaymentType() instanceof Salary) {
                    addEmployeeToProjects(employee);
                } else { // Αλλιώς ζητούνται οι ώρες που δούλεψε τον συγκεκριμένο μήνα
                    addWorkingHoursToEmployee(employee);
                }
            }

            // Υπολογισμός συνολικής μισθοδοσίας του μήνα και προσθήκη της στο string payrolls
            payrolls.append(calcTotalPayroll());

            month++;

            // Επιλογή συνέχισης ή τέλους εισαγωγής δεδομένων
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

        // Εισαγωγή εργαζόμενων μέχρι να επιλεχθεί "n", στην αντίστοιχη ερώτηση
        do {
            PaymentType paymentType = null;
            Employee employee = null;
            int employeeTypeNumber;
            int paymentTypeNumber;

            // Εισαγωγή ονόματος
            System.out.println("Give employee name");
            String name = input.next();

            // Εισαγωγή τύπου εργαζόμενου με αμυντικό προγραμματισμό
            do {
                employeeTypeNumber = 0;
                System.out.println("Give number for employee type. 1: Developer, 2: Manager, 3: Analyst, 4: Technical");

                // Έλεγχος αν έχει δοθεί integer
                try {
                    employeeTypeNumber = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please give an integer");
                    input.next();
                }
            } while ((employeeTypeNumber > 4) || (employeeTypeNumber < 1));

            // Εισαγωγή τύπου μισθοδοσίας, με αμυντικό προγραμματισμό
            do {
                paymentTypeNumber = 0;
                System.out.println("Give number for payment type. 1: Salary, 2: Per Hour");

                // Έλεγχος αν έχει δοθεί integer
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
