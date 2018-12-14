import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sensor {

  // ===============================================================================================
  // PRIVATE ATTRIBUTES
  // ===============================================================================================
  private int id;
  private int frequency;
  private String link;


  // ===============================================================================================
  // CONSTRUCTOR
  // ===============================================================================================
  public Sensor(int id, int frq, String lnk) {
    this.id = id;
    this.frequency = frq;
    this.link = lnk;
  }


  // ===============================================================================================
  // STATIC METHODS
  // ===============================================================================================
  public static Sensor fileToSensor(File file) throws IOException {
    FileInputStream tmp = new FileInputStream(file);
    BufferedReader buffer = new BufferedReader(new InputStreamReader(tmp));

    int sid = -1;
    int frq = -1;
    String lnk = "";

    String line = null;
    while ((line = buffer.readLine()) != null) {
      String key = line.substring(0, 3);
      String info = line.substring(5);

      switch (key) {
        case "SID":
          sid = Integer.parseInt(info);
          break;
        case "FRQ":
          frq = Integer.parseInt(info);
          break;
        case "LNK":
          lnk = info;
          break;
        default:
          break;
      }
    }

    buffer.close();

    if (sid == -1 || frq == -1 || lnk == "") {
      throw new IOException();
    }

    return new Sensor(sid, frq, lnk);
  }


  // ===============================================================================================
  // PUBLIC METHODS
  // ===============================================================================================
  public String toString() {
    return "Sensor " + id + " (" + frequency + "s, \"" + link + "\")";
  }

}
