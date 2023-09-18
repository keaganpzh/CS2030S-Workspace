package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * CS2030S Lab 5
 * AY22/23 Semester 2
 *
 * @author Keagan Pang (12H)
 */

public abstract class Maybe<T> {

  /**
   * The instance of None in this class.
   */
  private static final Maybe<?> none = new None();

  /**
   * Method that returns the None instance.
   *
   * @return The instance of None.
   */
  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    // the final field none is always a Maybe<T> object.
    Maybe<T> temp = (Maybe<T>) none; 
    return temp;
  }

  /**
   * Factory method that takes an object of type T
   * and wraps it in an instance of Some.
   *
   * @param t The item to be wrapped.
   *
   * @return  A Some object containing the item t.
   */
  public static <T> Maybe<T> some(T t) {
    return new Some<>(t);
  }

  /** Factory method that creates a Maybe object.
   *
   * @param t The item to be wrapped.
   *
   * @return  A Some object is t is not null, 
   *          or the None object otherwise.
   */
  public static <T> Maybe<T> of(T t) {
    if (t == null) {
      return none();
    }
    return some(t);
  }

  /**
   * Returns a String representation of the Maybe object.
   *
   * @return A String containing the item's String representation
   *         between [].
   */
  @Override
  public abstract String toString();

  /**
   * Checks if two Maybe objects are equal to each other.
   * A None is always equal to another None.
   * Two Some objects are equal iff their contents are also equal.
   * 
   * @param other The other object to be compared with.
   *
   * @return      True if both objects are equal, false otherwise.
   */
  public abstract boolean equals(Object other);

  /**
   * Retrieves the content inside a Maybe object.
   *
   * @return The item inside a Maybe object.
   */
  protected abstract T get();

  /**
   * Method that filters a Maybe object based on its content.
   *
   * @param cond A BooleanCondition subtype
   *
   * @return     None object if the content fails the condition, itself if 
   *             the content fulfills the condition.
   */
  public abstract Maybe<T> filter(BooleanCondition<? super T> cond);

  /**
   * Method that maps a Maybe object to another containing its content transformed.
   *
   * @param transformer A Transformer subtype.
   *
   * @return            A Some object containing a transformed item, or None.
   */
  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer);

  /** 
   * A variant of the map() method.
   *
   * @param transformer A Transformer subtype.
   *
   * @return            A Maybe object returned by the transformer.
   */
  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> transformer);

  /**
   * Affects the return type of a Maybe object.
   *
   * @param obj The alternative object
   *
   * @return    obj if invoked in None, or own content
   *            if invoked in Some.
   */
  public abstract T orElse(T obj);

  /**
   * Affects the return type of a Maybe object.
   *
   * @param producer A Producer subtype that produces a type T.
   *
   * @return         The produced T if invoked in None, or 
   *                 own content if invoked in Some.
   */
  public abstract T orElseGet(Producer<? extends T> producer); 

  /**
   * Offers an item to a Consumer.
   * Consumer takes in content in a Some object, or does nothing in a None object.
   *
   * @param consumer A Consumer subtype that consumes a type T.
   */
  public abstract void ifPresent(Consumer<? super T> consumer);


  /**
   * This class encapsulates a None object.
   */
  private static class None extends Maybe<Object> {

    public None() {
    }

    @Override 
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object other) {
      return other instanceof None;
    }

    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> cond) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> transformer) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U>> transformer) {
      return Maybe.none();
    }

    @Override
    public Object orElse(Object obj) {
      return obj;
    }

    @Override
    public Object orElseGet(Producer<? extends Object> producer) {
      return producer.produce();
    }

    @Override
    public void ifPresent(Consumer<? super Object> consumer) {
      return;
    }

  }

  /**
   * This class encapsulates a Some<T> object.
   */
  private static class Some<T> extends Maybe<T> {

    private final T item;

    public Some(T t) {
      this.item = t;
    }

    @Override
    public String toString() {
      return "[" + this.item + "]";
    }

    @Override
    public boolean equals(Object other) {
      if (other instanceof Some) {
        Some<?> temp = (Some<?>) other;
        if (this.get() == null) {
          return temp.get() == null;
        }
        if (temp.get() == null) {
          return this.get() == null;
        }
        return this.get().equals(temp.get());
      } 
      return false;
    }

    @Override
    protected T get() {
      return this.item;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> cond) {
      if (this.item == null) {
        return this;
      }
      return (cond.test(this.item))
        ? this
        : Maybe.none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer) {
      return Maybe.some(transformer.transform(this.item));
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> transformer) {
      @SuppressWarnings("unchecked")
      // the transformer will always return a Maybe<U> object.
      Maybe<U> temp = (Maybe<U>) transformer.transform(this.item);
      return temp;
    }

    @Override
    public T orElse(T obj) {
      return this.item;
    }

    @Override
    public T orElseGet(Producer<? extends T> producer) {
      return this.item;
    }

    @Override
    public void ifPresent(Consumer<? super T> consumer) {
      consumer.consume(this.item);
    }

  }

}
