/**
 * Takes an item and return the item in a box.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Keagan Pang (12H)
 */

public class BoxIt<T> implements Transformer<T, Box<T>> {

  /** 
   * Constructor for a BoxIt transformer.
   */
  public BoxIt() {
  }

  /**
   * Transform method for BoxIt.
   * Takes an item and returns a box containing the item.
   *
   * @param item The item to be boxed.
   *
   * @return     Box containing the item.
   *
   */
  @Override
  public Box<T> transform(T item) {
    return Box.ofNullable(item);
  }

}

