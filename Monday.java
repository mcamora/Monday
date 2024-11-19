import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Monday extends Weekday {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Monday monday = new Monday();
            
            while (true) {
                System.out.print("Enter the time for the alarm (HH:MM): ");
                String time = scanner.nextLine();
                monday.setAlarm(time);
                if (monday.showAlarm()) {
                    break;
                }
            }
        }
    }
}

interface Alarm {
    void setAlarm(String time);
    boolean showAlarm();
}

class Weekday implements Alarm {
    protected String time;

    @Override
    public void setAlarm(String time) {
        this.time = time;
    }

    @Override
    public boolean showAlarm() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        try {
            LocalTime alarm = LocalTime.parse(time, formatter);
            LocalTime now = LocalTime.now();
            
            if (alarm.isAfter(now)) {
                System.out.println("I'll wake you up later!");
            } else {
                System.out.println("Alarm is set for tomorrow!");
            }
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please enter the time in HH:MM format.");
            return false;
        }
    }
}