package cs2030s.fp;

/**
 * This class encapsulates a Lazy Object.
 * Can be generated with a lambda expression.
 * CS2030S Lab 7
 * AY22/23 Semester 2
 *
 * @author Keagan Pang (12H)
 */
public class Lazy<T> {

  /**
   * A producer object.
   */
  private Producer<? extends T> producer;

  /**
   * A Maybe object which encapsulates the object
   * contained in Lazy.
   */
  private Maybe<T> value;

  /**
   * Constructor for a Lazy object.
   *
   * @param producer The producer to be called in the future.
   * @param value The object to be encapsulated.
   */
  private Lazy(Producer<? extends T> producer, Maybe<T> value) {
    this.producer = producer;
    this.value = value;
  }

  /**
   * Factory method for a Lazy object.
   * This method takes in a value.
   *
   * @param v The object to be encapsulated.
   *
   * @param <T> The type of the object.
   *
   * @return A Lazy object encapsulating the input object.
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(() -> v, Maybe.some(v)); 
  }

  /**
   * Factory method for a Lazy object.
   * This method takes in a producer.
   *
   * @param s The producer to be called in the future.
   *
   * @param <T> The type of the value produced.
   *
   * @return A lazy object encapsulating the input producer.
   */
  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<T>(s, Maybe.none());
  }

  /**
   * A method to retrieve the object encapsulated in a Lazy instance.
   * If the value has been evaluated, simply return the value.
   * Otherwise, invoke the producer only ONCE.
   * 
   * @return The object encapsulated/produced.
   */
  public T get() {
    T temp = this.value.orElseGet(this.producer);
    this.value = Maybe.some(temp);
    return temp;
  }

  /**
   * Returns a String representation of the object encapsulated.
   *
   * @return "?" if the value has not been evaluated, or
   *         the value's string representation otherwise.
   */
  @Override
  public String toString() {
    Transformer<T, String> stringify = t -> String.valueOf(t);
    return this.value.<String>map(stringify).orElse("?");
  }

  /**
   * Method that transforms the object in a Lazy object to another object.
   *
   * @param transformer The Transformer object to be used.
   *
   * @param <U> The return type of the transformer.
   *
   * @return A Lazy object with its content transformed.
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> transformer) {
    return Lazy.of(() -> transformer.transform(this.get())); 
  }

  /**
   * A variant of the map() method where the Transformer object itself returns
   * a new Lazy object.
   *
   * @param transformer The Transformer object that returns a Lazy object.
   * 
   * @param <U> The type of the content of the returned Lazy object.
   *
   * @return A Lazy object with its content transformed.
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> transformer) {
    return Lazy.of(() -> transformer.transform(this.get()).get()); 
  }

  /**
   * A method that returns a boolean depending on the Lazy object's content
   * and a BooleanCondition object.
   *
   * @param cond A BooleanCondition object.
   *
   * @return A Lazy object containing true if the content passes the test,
   *         otherwise a Lazy object containing false.
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> cond) {
    return Lazy.of(() -> cond.test(this.get()));
  }

  /**
   * Returns true if two Lazy objects are equal to each other.
   * Returns true iff the value of two Lazy objects are equal. Eager operation.
   *
   * @param obj Another Lazy object to be compared to the current Lazy.
   *
   * @return True if equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Lazy) {
      Lazy<?> temp = (Lazy<?>) obj;
      return this.get().equals(temp.get());
    }
    return false;
  }

  /**
   * Method that combines two Lazy objects into one.
   * The values of the Lazy objects may have different types,
   * and are put together based on the Combiner object passed in.
   *
   * @param s The second Lazy object to be combined.
   *
   * @param combiner A combiner object.
   *
   * @param <S> The type of the other Lazy object to be combined.
   *
   * @param <R> The type of the content of the combined Lazy object.
   *
   * @return The Lazy object resulting from applying the Combiner combiner to 
   *         the two Lazy objects.
   */
  public <S, R> Lazy<R> combine(Lazy<S> s, Combiner<? super T, ? super S, ? extends R> combiner) {
    return Lazy.of(() -> combiner.combine(this.get(), s.get()));
  }

}
