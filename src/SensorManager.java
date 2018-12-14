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
  private ArrayList<Adapter> adapters = new ArrayList<Adapter>();


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
        Log.log("ERROR while reading sensor file \"" + file + "\"");
      }
    });

    return newSensors;
  }

  private void addNewSensors() {
    ArrayList<Sensor> newSensors = listNewSensors();

    newSensors.forEach(sensor -> {
      Adapter adapter = new Adapter(sensor).addRandomFilters(3);
      adapters.add(adapter);
      adapter.start();

      Log.log("New sensor added: " + adapter.toString());
    });
  }


  // ===============================================================================================
  // PUBLIC METHODS
  // ===============================================================================================
  public void start() {
    TimerTask timerTask = new TimerTask() {
      public void run() {
        addNewSensors();
      }
    };

    Timer timer = new Timer();
    timer.schedule(timerTask, 0, SensorManager.FREQUENCY * 1000);
  }

}
