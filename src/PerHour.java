public class PerHour implements PaymentType {
    private int hours;

    public PerHour(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }
}
