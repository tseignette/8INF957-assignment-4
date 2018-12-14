import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SensorManager {

  // ===============================================================================================
  // CONSTANTS
  // ===============================================================================================
  public final static String SENSORS_PATH = "./sensors";
  public final static int FREQUENCY = 10; // Scan frequency in seconds


  // ===============================================================================================
  // PRIVATE ATTRIBUTES
  // ===============================================================================================
  private ArrayList<String> sensorFiles = new ArrayList<String>();


  // ===============================================================================================
  // PRIVATE METHODS
  // ===============================================================================================
  private ArrayList<String> listNewFiles() {
    String[] files = new File(SensorManager.SENSORS_PATH).list();
    ArrayList<String> newFiles = new ArrayList<String>();

    for (String file : files) {
      if (!this.sensorFiles.contains(file))
        newFiles.add(file);
    }

    return newFiles;
  }


  // ===============================================================================================
  // PUBLIC METHODS
  // ===============================================================================================
  public void start() {
    TimerTask timerTask = new TimerTask() {
      public void run() {
        ArrayList<String> newFiles = listNewFiles();

        if (newFiles.size() > 0) { 
          sensorFiles.addAll(newFiles);
          System.out.println("New file(s) detected: " + newFiles);
        }
      }
    };

    Timer timer = new Timer();
    timer.schedule(timerTask, 0, SensorManager.FREQUENCY * 1000);
  }

}
