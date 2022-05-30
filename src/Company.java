import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Company {
    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

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
    public String calcPayroll() {
        int salarySum = 0;
        String payroll = "";

        // Εκτύπωση μισθού για κάθε υπάλληλο και υπολογισμός του
        // συνολικού ποσού μισθοδοσίας για την εταιρεία
        for(Employee employee : employeeList) {
            payroll += "Name: " + employee.getName() + " Salary: " + employee.getMonthlySalary() + " euro\n";
            salarySum += employee.getMonthlySalary();
        }

        payroll += "Total monthly salaries: " + salarySum + " euro\n";

        return payroll;
    }

    // Αποθήκευση μισθοδοσίας μήνα
    public void save(int month) {
        try {
            // Δημιουργία αρχείου
            FileWriter file = new FileWriter(String.format("payroll%d.txt", month));

            file.write(calcPayroll());

            // Κλείσιμο αρχείου
            file.close();
        } catch (IOException e) {
            // Exception σε περίπτωση κάποιου σφάλματος στον χειρισμό του αρχείου
            System.out.println("Problem with file");
            e.printStackTrace();
        }
    }

    // Εκτύπωση ετήσιας μισθοδοσίας
    public void annualPayroll() {
        for(int i=1; i<=12; i++) {
            try {
                FileReader file = new FileReader(String.format("payroll%d.txt", i));

                int character;
                while ((character = file.read()) != -1) {
                    System.out.print((char)character);
                }

                file.close();
            } catch (Exception e) {
                System.out.println("Υπάρχει πρόβλημα με το αρχείο");
            }
        }
    }
}
