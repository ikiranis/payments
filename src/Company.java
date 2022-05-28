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
    public void calcPayroll() {
        int salarySum = 0;

        // Εκτύπωση μισθού για κάθε υπάλληλο και υπολογισμός του
        // συνολικού ποσού μισθοδοσίας για την εταιρεία
        for(Employee employee : employeeList) {
            System.out.println("Name: " + employee.getName() + " Salary: " + employee.getMonthlySalary() + " euro");
            salarySum += employee.getMonthlySalary();
        }

        System.out.println("Total monthly salaries: " + salarySum + " euro");

    }

    // Αποθήκευση μισθοδοσίας μήνα
    public void save() {
    }

    // Υπολογισμός ετήσιας μισθοδοσίας
    public void annualPayroll() {

    }
}
