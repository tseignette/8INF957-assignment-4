import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SensorManager {

  // ===============================================================================================
  // PRIVATE ATTRIBUTES
  // ===============================================================================================
  private ArrayList<String> sensorFiles = new ArrayList<String>();
  private ArrayList<Adapter> adapters = new ArrayList<Adapter>();
  private ParkingServer server;


  // ===============================================================================================
  // PUBLIC ATTRIBUTES
  // ===============================================================================================
  public int frequency; // Scan frequency in seconds
  public String sensorsPath;


  // ===============================================================================================
  // CONSTRUCTOR
  // ===============================================================================================
  public SensorManager(int frequency, String sensorsPath, ParkingServer server) {
    this.frequency = frequency;
    this.sensorsPath = sensorsPath;
    this.server = server;
  }


  // ===============================================================================================
  // PRIVATE METHODS
  // ===============================================================================================
  private ArrayList<String> listNewFiles() {
    String[] files = new File(this.sensorsPath).list();
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
      String path = this.sensorsPath + "/" + file;

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
      Adapter adapter = new Adapter(sensor, server).addRandomFilters(3);
      server.addParkingSpot(sensor.getId());
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
    timer.schedule(timerTask, 0, this.frequency * 1000);
  }

}
