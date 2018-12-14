import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Log {

  
  public static void log(String log) {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
    Date date = new Date();

    System.out.println("[" + dateFormat.format(date) + "] " + log);
  }

}
