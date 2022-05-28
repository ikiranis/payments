import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private PaymentType paymentType;
    private List<Project> projectList = new ArrayList<>();
    private int monthlySalary;
    private int hourRate;
    private int bonus = 80;

    public Employee(String name, PaymentType paymentType, int monthlySalary, int hourRate) {
        this.name = name;
        this.paymentType = paymentType;
        this.monthlySalary = monthlySalary;
        this.hourRate = hourRate;
    }

    public String getName() {
        return name;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    // Εισαγωγή έργου στη λίστα των έργων του υπαλλήλου
    public void addProject(Project project) {
        projectList.add(project);
    }

    // Υπολογίζει τον μισθό για έναν μήνα
    public int getMonthlySalary() {
        // An το paymentType είναι Salary, υπολογίζει μηνιαίο μισθό
        if (paymentType instanceof Salary) {
            return monthlySalary + (bonus * projectList.size());
        }

        // Αλλιώς υπολογίζει τον μισθό με βάση την ώρα
        return hourRate * ((PerHour)paymentType).getHours();
    }
}
