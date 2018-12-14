
public class SmartParking {

  // ===============================================================================================
  // CONSTANTS
  // ===============================================================================================
  public final static int HTTP_SERVER_PORT = 8000;
  public final static String SENSORS_PATH = "./sensors";
  public final static int FREQUENCY = 10; // Scan frequency in seconds


  // ===============================================================================================
  // MAIN
  // ===============================================================================================
  public static void main(String[] args) {
    ParkingServer server = new ParkingServer();
    try {
      server.start(HTTP_SERVER_PORT);
      Log.log("HTTP server started!");
    } catch (Exception e) {
      Log.log("ERROR while loading HTTP server...");
    }

    SensorManager sensorManager = new SensorManager(
      SmartParking.FREQUENCY,
      SmartParking.SENSORS_PATH,
      server
    );
    sensorManager.start();

    Log.log("Sensor manager started!");
  }

}
