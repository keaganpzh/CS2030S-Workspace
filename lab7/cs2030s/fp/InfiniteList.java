package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates an InfiniteList.
 * An InfiniteList is a lazily evaluated linked list that makes use of Lazy objects.
 * CS2030S Lab 7 
 * AY22/23 Semester 2
 *
 * @author Keagan Pang (12H)
 */
public class InfiniteList<T> {

  /**
   * The head of an InfiniteList.
   * An object that can be generated with a lambda expression.
   */
  private final Lazy<Maybe<T>> head;

  /**
   * The tail of an InfiniteList.
   * An InfiniteList that can be generated with a lambda expression.
   */
  private final Lazy<InfiniteList<T>> tail;

  /**
   * The special tail that marks the end of the list.
   * Only one instance of Sentinel is produced and cached.
   */
  private static final Sentinel SENTINEL = new Sentinel();

  /**
   * This class encapsulates a Sentinel.
   * A Sentinel is a special tail that marks the end of the list.
   */
  private static class Sentinel extends InfiniteList<Object> {

    /** 
     * A constructor for a Sentinel.
     */
    Sentinel() {
    }

    /** Returns the string representation of a Sentinel.
     *
     * @return The string representation of a Sentinel.
     */
    @Override
    public String toString() {
      return "-";
    }

    /**
     * Checks if the InfiniteList object is a Sentinel.
     *
     * @return True if the InfiniteList is a Sentinel, false otherwise.
     */
    @Override
    public boolean isSentinel() {
      return true;
    }

    /**
     * Calls the head of a Sentinel.
     * Throws a NoSuchElementException.
     *
     * @return NoSuchElementException.
     */
    @Override
    public Object head() {
      return Maybe.none().get();
    }

    /**
     * Calls the tail of a Sentinel.
     * Returns itself.
     *
     * @return This Sentinel.
     */
    @Override
    public InfiniteList<Object> tail() {
      return this;
    }

    /**
     * Maps a Sentinel with a transformer.
     * Returns itself.
     *
     * @param mapper The Transformer object given.
     * @param <U> The return type of the Transformer::transform() method.
     *
     * @return This Sentinel.
     */
    @Override
    public <U> InfiniteList<U> map(Transformer<? super Object, ? extends U> mapper) {
      return InfiniteList.sentinel();
    }

    /**
     * Filters a Sentinel using a BooleanCondition.
     * Returns itself.
     *
     * @param predicate The BooleanConditon object given.
     *
     * @return This Sentinel.
     */
    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return this;
    }

    /**
     * Limits a Sentinel.
     * Returns itself.
     *
     * @param n The maximum number of elements in the returned list.
     *
     * @return This Sentinel.
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return this;
    }

    /** 
     * Converts a Sentinel into a java.util.List object.
     * Returns an empty Java List.
     *
     * @return An empty List.
     */
    @Override
    public List<Object> toList() {
      return List.of();
    }

    /** 
     * At the end of a list, takeWhile returns a Sentinel.
     * Returns itself.
     *
     * @param predicate The BooleanCondition Object given.
     *
     * @return This Sentinel.
     */
    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return this;
    }

    /**
     * Reduces/folds a sentinel.
     * Returns the given identity element.
     * 
     * @param accumulator The Combiner object given.
     * @param <U> Type of the identity element and the result of the reduction.
     *
     * @return The given identity element.
     */
    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      return identity;
    }

    /** 
     * Counts the length of a Sentinel.
     *
     * @return 0L.
     */
    @Override
    public long count() {
      return 0L;
    }

  }

  /**
   * Constructor for an empty InfiniteList.
   */
  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  /**
   * Generates an InfiniteList of one element produced by the given producer.
   *
   * @param producer A Producer object that produces an object of type T.
   * @param <T> The type of object in the InfiniteList.
   *
   * @return An InfiniteList of one unique element.
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    // TODO
    return new InfiniteList<T>(
        Lazy.of(() -> Maybe.some(producer.produce())), 
        Lazy.of(() -> generate(producer)));
  }

  /**
   * Generates an InfiniteList given a starting element and a Transformer.
   *
   * @param seed The starting element of the list.
   * @param next A Transformer object that produces the next element of the list.
   * @param <T> The type of object in the InfiniteList.
   *
   * @return An InfiniteList that is the result of applying a transformer to a starting element.
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    // TODO
    return new InfiniteList<T>(
        Lazy.of(Maybe.some(seed)), 
        Lazy.of(() -> iterate(next.transform(seed), next)));
  }

  /**
   * Constructor for an InfiniteList.
   *
   * @param head The object to be put in the head of the InfiniteList.
   * @param tail The Producer that produces the rest of the InfiniteList.
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    // TODO
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  /**
   * Constructor for an InfiniteList.
   *
   * @param head A Lazy object that encapsulates an object as the head.
   * @param tail A Lazy object that encapsulates the ret of the InfintieList.
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    // TODO
    this.head = head;
    this.tail = tail;
  }

  /**
   * Evaluates and returns the head of the InfiniteList.
   * 
   * @return The object contained in the head of the list.
   */
  public T head() {
    // TODO
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }
  
  /**
   * Returns the tail of the InfiniteList.
   * The rest of the list without the head.
   *
   * @return An InfiniteList that comes after the head of the list.
   */
  public InfiniteList<T> tail() {
    // TODO
    return this.head.get().map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  /**
   * Returns an InfiniteList where each value is transformed by a given transformer.
   * 
   * @param mapper The Transformer object that transforms elements in the list.
   * @param <R> The type of the transformed elements in the list.
   *
   * @return An InfiniteList of transformed elements.
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    // TODO
    return new InfiniteList<R>(
        Lazy.of(() -> this.head.get().map(mapper)),
        Lazy.of(() -> this.tail.get().map(mapper)));
  }

  /**
   * Filters an InfiniteList based on the given BooleanCondition.
   *
   * @param predicate The BooleanCondition object that checks an element of the list.
   *
   * @return A new InfiniteList containing only elements that pass the test given by the predicate.
   *         Elements that do not pass are replaced by a Maybe.None.
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<T>(
        Lazy.of(() -> this.head.get().filter(predicate)),
        Lazy.of(() -> this.tail.get().filter(predicate)));
  }

  /**
   * Returns a Sentinel object.
   * Returns the final cached Sentinel.
   *
   * @param <T> Type of the Sentinel.
   *
   * @return A Sentinel object.
   */
  public static <T> InfiniteList<T> sentinel() {
    // TODO
    @SuppressWarnings("unchecked")
    // it is safe to typecast as Sentinel marks the end of any InfiniteList.
    InfiniteList<T> sentinel = (InfiniteList<T>) SENTINEL;
    return sentinel;
  }

  /**
   * Truncates the InfiniteList to a list of n elements.
   *
   * @param n The number of elements to shorten the list to.
   *
   * @return The truncated InfiniteList which now has at most n elements.
   */
  public InfiniteList<T> limit(long n) {
    // TODO
    Lazy<InfiniteList<T>> newTail = Lazy.of(() -> this.head.get()
        .map(x -> this.tail.get().limit(n - 1))
        .orElseGet(() -> this.tail.get().limit(n)));
    return Maybe.of(n)
      .filter(x -> x > 0)
      .map(x -> new InfiniteList<T>(this.head, newTail))
      .orElseGet(() -> sentinel());
  }

  /**
   * Truncates the InfiniteList as soon as an element fails the given BooleanCondition.
   *
   * @param predicate The BooleanCondition object to test elements of the list.
   *
   * @return A finite list that is shortened once an element fails the test.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    Lazy<Maybe<T>> newHead = Lazy.of(() -> this.head.get().filter(predicate));
    Lazy<InfiniteList<T>> newTail = Lazy.of(() -> this.head.get()
        .map(x -> newHead.get()
          .map(y -> this.tail.get().takeWhile(predicate))
          .orElseGet(() -> sentinel()))
        .orElseGet(() -> this.tail.get().takeWhile(predicate)));
    return new InfiniteList<T>(newHead, newTail);
  }

  /**
   * Checks if the InfiniteList is a Sentinel.
   *
   * @return True if the list is a sentinel, false otherwise.
   */
  public boolean isSentinel() {
    return false;
  }

  /**
   * Reduces/folds an InfiniteList given an identity element and a Combiner.
   * Applies the combiner pairwise, starting from the identity element.
   *
   * @param identity The identity element.
   * @param accumulator The Combiner object that is applied pairwise to elements in the list.
   * @param <U> Type of the identity element and the final result of the reduction.
   *
   * @return The result of the reduction routine.
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    return this.tail.get()
      .reduce(
        this.head.get()
        .map(x -> accumulator.combine(identity, x))
        .orElseGet(() -> identity), 
      accumulator);
  }

  /**
   * Counts the number of elements in the InfiniteList.
   *
   * @return The number of elements in the list as a long.
   */
  public long count() {
    // TODO
    return this.reduce(0, (x1, x2) -> x1 + 1);
  }

  /**
   * Converts an InfiniteList to a java.util.List object.
   *
   * @return The list representation of the InfiniteList.
   */
  public List<T> toList() {
    // TODO
    List<T> temp = new ArrayList<T>();
    Consumer<T> consumer = x -> temp.add(x);
    this.head.get().ifPresent(consumer);
    temp.addAll(this.tail.get().toList());
    return temp;
  }

  /**
   * Returns the string representation of the InfiniteList.
   * Elements of the InfiniteList are lazily evaluated.
   *
   * @return The string representation of the InfiniteList.
   */
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

}
