import java.util.List;

public class Employee {
    private String name;
    private PaymentType paymentType;
    private List<Project> projectList;
    private int monthlySalary;
    private int hourRate;

    public Employee(String name, PaymentType paymentType, int monthlySalary, int hourRate) {
        this.name = name;
        this.paymentType = paymentType;
        this.monthlySalary = monthlySalary;
        this.hourRate = hourRate;
    }

    // Εισαγωγή έργου στη λίστα των έργων του υπαλλήλου
    public void addProject(Project project) {
        projectList.add(project);
    }
}
