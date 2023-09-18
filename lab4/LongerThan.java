/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Keagan Pang (12H)
 */

public class LongerThan implements BooleanCondition<String> {

  /**
   * The limit length that compares with the String length.
   */
  private int length;

  /**
   * Constructor for a LongerThan condition.
   *
   * @param length The limit length to be compared to.
   *
   */
  public LongerThan(int length) {
    this.length = length;
  }

  /**
   * The test method associated with the LongerThan condition.
   *
   * @param s The String to be compared with the limit length.
   *
   * @return  true if the length of the String is longer than the limit,
   *          false otherwise.
   */         
  @Override
  public boolean test(String s) {
    return s.length() > this.length;
  }

}

