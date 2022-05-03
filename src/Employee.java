import java.util.List;

public class Employee {
    private String name;
    private PaymentType paymentType;
    private List<Project> projectList;

    public Employee(String name, PaymentType paymentType) {
        this.name = name;
        this.paymentType = paymentType;
    }

    // Εισαγωγή έργου στη λίστα των έργων του υπαλλήλου
    public void addProject(Project project) {
        projectList.add(project);
    }
}
