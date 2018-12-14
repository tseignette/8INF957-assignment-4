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

  private ArrayList<Sensor> listNewSensors() {
    // Listing new files
    ArrayList<String> newFiles = listNewFiles();

    if (newFiles.size() > 0)
      this.sensorFiles.addAll(newFiles);

    // Reading new files
    ArrayList<Sensor> newSensors = new ArrayList<Sensor>();

    newFiles.forEach(file -> {
      String path = SensorManager.SENSORS_PATH + "/" + file;

      try {
        newSensors.add(Sensor.fileToSensor(new File(path)));
      } catch (Exception e) {
        System.out.println("ERROR while reading sensor file \"" + file + "\"");
      }
    });

    return newSensors;
  }


  // ===============================================================================================
  // PUBLIC METHODS
  // ===============================================================================================
  public void start() {
    TimerTask timerTask = new TimerTask() {
      public void run() {
        ArrayList<Sensor> newSensors = listNewSensors();

        if (newSensors.size() > 0) {
          System.out.println("New sensor(s) added: " + newSensors);
        }
      }
    };

    Timer timer = new Timer();
    timer.schedule(timerTask, 0, SensorManager.FREQUENCY * 1000);
  }

}
