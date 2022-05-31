public class Salary extends PaymentType {

    // Υπολογισμός μισθού μηνιαία και με βάση το bonus από
    // την συμμετοχή σε projects
    @Override
    public int getSalary(Employee employee) {
        return employee.getMonthlySalary() + (employee.getBonus() * employee.getProjectList().size());
    }
}
