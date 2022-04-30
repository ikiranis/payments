import java.util.List;

public class Employee {
    private String name;
    private Payment payment;
    private List<Project> projectList;

    public Employee(String name, Payment payment) {
        this.name = name;
        this.payment = payment;
    }

    // Εισαγωγή έργου στη λίστα των έργων του υπαλλήλου
    public void addProject(Project project) {
        projectList.add(project);
    }
}
