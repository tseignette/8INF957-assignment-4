
public class DuplicationFilter extends Filter {

  protected Character applyFilter(Character value) {
    if (previousValue == value)
      return Filter.NO_DATA;

    return value;
  }

  public String toString() {
    return "Duplication filter";
  }

}
