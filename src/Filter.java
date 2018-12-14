import java.util.Random;

public abstract class Filter {

  // ===============================================================================================
  // CONSTANTS
  // ===============================================================================================
  public final static Character NO_DATA = ' ';


  // ===============================================================================================
  // PRIVATE ATTRIBUTES
  // ===============================================================================================
  protected Character previousValue = Filter.NO_DATA;


  // ===============================================================================================
  // STATIC METHODS
  // ===============================================================================================
  public static Filter getRandomFilter() {
    Filter[] filters = {
      new DuplicationFilter(),
      new TransformFilter(),
      new PassThroughFilter()
    };

    return filters[new Random().nextInt(3)];
  }


  // ===============================================================================================
  // PROTECTED METHODS
  // ===============================================================================================
  protected abstract Character applyFilter(Character value);


  // ===============================================================================================
  // PUBLIC METHODS
  // ===============================================================================================
  public abstract String toString();

  public Character apply(Character value) {
    Character nextValue = this.applyFilter(value);

    if (nextValue != Filter.NO_DATA)
      this.previousValue = value;

    return nextValue;
  }

}
