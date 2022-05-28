public class PerHour extends PaymentType {
    private int hours;

    public PerHour(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
