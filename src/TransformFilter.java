
public class TransformFilter extends Filter {

  protected Character applyFilter(Character value) {
    switch (value) {
      case '1':
        return 'D';
      case '0':
        return 'N';
      case 'D':
        return 'D';
      case 'N':
        return 'N';
      default:
        return Filter.NO_DATA;
    }
  }

  public String toString() {
    return "Transorm filter";
  }

}
