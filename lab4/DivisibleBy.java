/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Keagan Pang (12H)
 */

public class DivisibleBy implements BooleanCondition<Integer> {

  /**
   * The integer to be divided by.
   */
  private int divisor;

  /** 
   * Constructor for a DivisibleBy condition
   *
   * @param divisor The integer to be divided by.
   *
   */
  public DivisibleBy(Integer divisor) {
    this.divisor = (int) divisor;
  }

  /**
   * The test method associated with the DivisibleBy condition.
   *
   * @param t The integer to be tested for divisibility with the divisor
   *
   * @return  true if t can be divided by divisor, false otherwise.
   *
   */
  @Override
  public boolean test(Integer t) {
    int dividend = (int) t;
    return dividend % this.divisor == 0;
  }

}


