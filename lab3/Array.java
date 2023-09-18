/**
 * The Array<T> for CS2030S 
 *
 * @author Keagan Pang
 * @version CS2030S AY22/23 Semester 2
 */
class Array<T extends Comparable<T>> { // TODO: Change to bounded type parameter
  private T[] array;

  /**
   * Constructor for an Array Object.
   *
   * @param size The size of the array.
   *
   */
  Array(int size) {
    // TODO
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] a = (T[]) new Comparable[size];
    this.array = a;
  }

  /**
   * Sets an item into the array at the specified index.
   *
   * @param index  The index of the array for the 
   *               item to be inserted in.
   * @param item   The item to be inserted in the 
   *               specified index.
   */
  public void set(int index, T item) {
    // TODO
    this.array[index] = item; 
  }

  /**
   * Retrieves the item in the specified index of the array.
   *
   * @param index The specified index of the item to be returned.
   *
   */
  public T get(int index) {
    // TODO
    return this.array[index];
  }

  /**
   * Returns the "smallest" item in the array.
   * Based on Comparable<T>::compareTo() method.
   *
   * @return The "minimum" object of type T in the array.
   *
   */
  public T min() {
    T lowest = this.array[0];
    for (int i = 0; i < this.array.length; i++) {
      if (this.array[i].compareTo(lowest) <= 0) {
        lowest = this.array[i];
      } 
    }
    return lowest;
  }

  /**
   * Returns a String represenation of the array
   *
   * @return A String containing the representation of the array.
   *
   */
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }

}
