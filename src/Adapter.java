import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Adapter {

  // ===============================================================================================
  // PRIVATE ATTRIBUTES
  // ===============================================================================================
  private Sensor sensor;


  // ===============================================================================================
  // CONSTRUCTOR
  // ===============================================================================================
  public Adapter(Sensor sensor) {
    this.sensor = sensor;
  }


  // ===============================================================================================
  // PRIVATE METHODS
  // ===============================================================================================
  public int readSensor() {
    return new Random().nextInt(2);
  }


  // ===============================================================================================
  // PUBLIC METHODS
  // ===============================================================================================
  public void start() {
    TimerTask timerTask = new TimerTask() {
      public void run() {
        int read =  readSensor();
        Log.log(sensor.toString() + " reads " + read);
      }
    };

    Timer timer = new Timer();
    timer.schedule(timerTask, 0, this.sensor.getFrequency() * 1000);
  }

}
