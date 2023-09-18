/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Keagan Pang (12H)
 */

public interface Transformer<T, U> {

  /**
   * Abstract transform method.
   * Takes in an object of type T and transforms it into an
   * object of type U.
   *
   * @param item The item of type T to be transformed.
   *
   * @return     The item of type U to be returned.
   *
   */
  public abstract U transform(T item);

}

