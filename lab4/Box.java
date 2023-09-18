/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Keagan Pang (12H)
 */

public class Box<T> {

  /**
   * Item in the box.
   */
  private final T content;

  /**
   * The empty box to be used.
   */
  private static final Box<?> EMPTY_BOX = new Box<>(null); 

  /**
   * Constructor for a Box object.
   * This constructor is private because a box object
   * can only be created using the of() method.
   */
  private Box(T content) {
    this.content = content;
  }
 
  /**
   * Checks if two boxes are equal to each other.
   * Two boxes are equal iff they are both null or if
   * they contain the same object.
   *
   * @param object The object to be compared to with this box.
   *
   * @return       true if the two boxes are equal, false otherwise.
   *
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof Box) {
      Box<?> tempBox = (Box<?>) object;
      if (this.isPresent() && tempBox.isPresent()) {
        return this.content.equals(tempBox.content);
      } else if (!this.isPresent() && !tempBox.isPresent()) {
        return true;
      }
      return false;
    }
    return false;
  }

  /**
   * Returns a String representation of the box.
   *
   * @return String containing a box's content between
   *         two square brackets.
   */         
  @Override
  public String toString() {
    return !this.isPresent()
      ? "[]"
      : "[" + this.content.toString() + "]";
  }

  /**
   * Factory method to create a box.
   * This method does not reuse instances.
   *
   * @param content The object to be contained in the box.
   *
   * @return        The box containing the input content.
   *
   */
  public static <T> Box<T> of(T content) {
    if (content == null) {
      return null;
    } else {
      return new Box<>(content);
    }
  }

  /**
   * Method to create an empty box.
   * 
   * @return The typecasted EMPTY_BOX.
   *
   */
  public static <T> Box<T> empty() {
    @SuppressWarnings("unchecked")
    Box<T> tempBox = (Box<T>) EMPTY_BOX;
    return tempBox; 
  }

  /**
   * Checks if a box is containing something.
   *
   * @return true if the box is full, false otherwise.
   *
   */
  public boolean isPresent() {
    return this.content != null;
  }
  
  /**
   * Factory method that can also return an empty box.
   *
   * @param content null, or the item to be contained in 
   *                the box.
   * @return        An empty box, or a box containing the 
   *                input item.
   */                
  public static <T> Box<T> ofNullable(T content) {
    return content == null
      ? empty()
      : of(content);
  }

  /**
   * Filters a box based on the given BooleanCondition.
   *
   * @param cond A subtype of BooleanCondition.
   *
   * @return     An empty box if the test is failed,
   *             the current box if the test is passed.
   */             
  public Box<T> filter(BooleanCondition<? super T> cond) {
    if (!this.isPresent()) {
      return this.empty();
    } else {
      return cond.test(this.content)
        ? this
        : this.empty();
    }
  }

  /**
   * Maps a box with an item inside it to a box with its 
   * content transformed into another item.
   *
   * @param transformer A subtype of Transformer.
   *
   * @return            A box containing a transformed item.
   *
   */
  public <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
    return !this.isPresent()
      ? this.empty()
      : ofNullable(transformer.transform(this.content));
  }

}
