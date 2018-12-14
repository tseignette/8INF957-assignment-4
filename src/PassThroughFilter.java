
public class PassThroughFilter extends Filter {

  protected Character applyFilter(Character value) {
    return value;
  }

  public String toString() {
    return "Pass through filter";
  }

}
