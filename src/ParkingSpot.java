
public class ParkingSpot {

  public final static int FREE = 0;
  public final static int TAKEN = 1;
  public final static int UNKNOWN = 2;

  private int id;
  private int state = UNKNOWN;

  public ParkingSpot(int id) {
    this.id = id;
  }

  public static String stateToString(int state) {
    if (state == FREE)
      return "FREE";
    else if (state == TAKEN)
      return "TAKEN";

    return "UNKNOWN";
  }

  public static int parseSensorState(Character state) {
    if (state == '1' || state == 'D')
      return TAKEN;
    else if (state == '0' || state == 'N')
      return FREE;

    return UNKNOWN;
  }

  public void setState(int state) {
    this.state = state;
  }

  public int getState() {
    return this.state;
  }

  public int getId() {
    return this.id;
  }

  public String toString() {
    return "Parking spot " + id + ": " + stateToString(state);
  }

}
