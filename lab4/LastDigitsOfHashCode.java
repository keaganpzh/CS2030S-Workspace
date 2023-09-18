/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Keagan Pang (12H)
 */

public class LastDigitsOfHashCode implements Transformer<Object, Integer> {

  /**
   * Represents the number of digits of the hash value
   * to be returned.
   */
  private int k;

  /**
   * Constructor for a LastDigitsOfHashCode transformer.
   *
   * @param k The number of digits for this object.
   *
   */
  public LastDigitsOfHashCode(int k) {
    this.k = k;
  }

  /**
   * Transform method for LastDigitsOfHashCode.
   *
   * @param o The object whose partial hash value to be returned.
   *
   * @return  The last k digits of o's hash value.
   *
   */
  @Override
  public Integer transform(Object o) {
    String hashString = Integer.toString(o.hashCode());
    String lastK = hashString.substring(hashString.length() - k);
    return Integer.valueOf(lastK);
  }

}
