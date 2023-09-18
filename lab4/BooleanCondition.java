/**
 * A conditional statement that returns either true of false.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Keagan Pang (12H)
 */

public interface BooleanCondition<T> {

  /**
   * Abstract test method
   * 
   * @param t The item to be tested.
   *
   * @return  true or false depending on implementation.
   *
   */
  public abstract boolean test(T t);

}
