public class Salary extends PaymentType {

    @Override
    public int getSalary(Employee employee) {
        return employee.getMonthlySalary() + (employee.getBonus() * employee.getProjectList().size());
    }
}
