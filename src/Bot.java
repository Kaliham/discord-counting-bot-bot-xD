
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Bot {
    static Robot robot;
    static LocalTime time = LocalTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss");

    public static void main(String[] args) throws AWTException {
        robot = new Robot();
        System.out.println("Print starting..");
        robot.delay(5000);

        call();
    }

    public static void call() {
        robot.delay(500);
        int timer = 01;
        for (int i = 2; i <= 5000; i += 2) {
            type(i + "");

            time = LocalTime.now();
            int timeInt = Integer.parseInt(time.format(formatter));
            while (timeInt != timer) {
                time = LocalTime.now();

                timeInt = Integer.parseInt(time.format(formatter));
                // System.out.println(time.format(formatter));
            }
            timer += 2;
            timer = timer % 60;
            // System.out.println("timer:" + timer);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
    }

    private static void type(String s) {
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            int code = b;
            // keycode only handles [A-Z] (which is ASCII decimal [65-90])
            if (code > 96 && code < 123)
                code = code - 32;
            robot.delay(40);
            robot.keyPress(code);
            robot.keyRelease(code);
        }
    }
}
