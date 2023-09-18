package cs2030s.fp;

/**
 * CS2030S Lab 5
 * AY22/23 Semester 2
 *
 * @author Keagan Pang (12H) 
 */

public interface Consumer<T> {

  /**
   * Takes in an object of type T.
   */
  public void consume(T t);

}
