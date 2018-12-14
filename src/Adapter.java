import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Adapter {

  // ===============================================================================================
  // PRIVATE ATTRIBUTES
  // ===============================================================================================
  private Sensor sensor;
  private ArrayList<Filter> filters = new ArrayList<Filter>();


  // ===============================================================================================
  // CONSTRUCTOR
  // ===============================================================================================
  public Adapter(Sensor sensor) {
    this.sensor = sensor;
  }


  // ===============================================================================================
  // PRIVATE METHODS
  // ===============================================================================================
  private int readSensor() {
    return new Random().nextInt(2);
  }


  // ===============================================================================================
  // PUBLIC METHODS
  // ===============================================================================================
  public void addFilter(Filter filter) {
    this.filters.add(filter);
  }

  public Adapter addRandomFilters(int n) {
    Random r = new Random();

    for (int i = 0; i < n; i++) {
      int add = r.nextInt(2);
      if (add == 1) this.addFilter(Filter.getRandomFilter());
    }

    return this;
  }

  public Character read() {
    Character value = (char) (this.readSensor() + '0');

    for (Filter filter : this.filters) {
      value = filter.apply(value);
    }

    return value;
  }

  public void start() {
    TimerTask timerTask = new TimerTask() {
      public void run() {
        Character value = read();

        if (value != Filter.NO_DATA)
          Log.log("Reading " + sensor.toString() + ": " + value);
      }
    };

    Timer timer = new Timer();
    timer.schedule(timerTask, 0, this.sensor.getFrequency() * 1000);
  }

  public String toString() {
    String output = this.sensor.toString();

    for (Filter filter : this.filters) {
      output += " > " + filter.toString();
    }

    return output;
  }

}
