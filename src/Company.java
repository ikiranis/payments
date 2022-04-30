import java.util.List;

public class Company {
    private List<Employee> employeeList;

    // Εισαγωγή υπαλλήλου στην εταιρεία
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    // Εισαγωγή έργου στον υπάλληλο
    public void addProjectToEmployee(String name, Project project) {
        // Εύρεση υπαλλήλου με το όνομα name και προσθήκη του έργου σε αυτόν
        employeeList.get(employeeList.indexOf(name)).addProject(project);
    }

    // Υπολογισμός μισθοδοσίας
    public void calcPayroll() {
    }

    // Αποθήκευση μισθοδοσίας μήνα
    public void save() {
    }

    // Υπολογισμός ετήσιας μισθοδοσίας
    public void annualPayroll() {

    }
}
