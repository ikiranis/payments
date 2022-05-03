import java.util.List;

public class Employee {
    private String name;
    private PaymentType paymentType;
    private List<Project> projectList;
    private int monthlySalary;
    private int hourRate;
    private int bonus = 80;

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

    // Υπολογίζει τον μισθό για έναν μήνα
    public int getMonthlySalary() {
        if (paymentType instanceof Salary) {
            return monthlySalary * (bonus * projectList.size());
        }

        return hourRate * ((PerHour)paymentType).getHours();
    }
}
