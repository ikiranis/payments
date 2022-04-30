import java.util.List;

public class Company {
    private List<Employee> employeeList;

    // Εισαγωγή υπαλλήλου στην εταιρεία
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    // Εισαγωγή έργου στον υπάλληλο
    public void addProjectToEmployee(String name, Project project) {
        employeeList.get(employeeList.indexOf(name)).addProject(project);
    }

    public void calcPayroll() {
    }
}
