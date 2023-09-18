/**
 * This class represents individual counters in the simulation.
 * One Counter object represents one counter in the simulation.
 *
 * @author Keagan Pang
 *
 */
public class Counter implements Comparable<Counter> {

  /**
   * The id associated with this counter object.
   * id starts from 0.
   */
  private int counterId;

  /**
   * Boolean representation of whether a counter is
   * available for service.
   */
  private boolean availability;

  /**
   * The integer representation of the counter queue length. 
   */
  private int counterQueueLength;

  /**
   * The Queue object associated with the counter.
   */
  private Queue<Customer> counterQueue;

  /**
   * Constructor for a Counter object
   *
   * @param id                  The id assigned to this counter.
   *
   * @param counterQueueLength  The length of the counter queue.
   *
   */
  public Counter(int id, int counterQueueLength) {
    this.counterId = id;
    this.availability = true;
    this.counterQueueLength = counterQueueLength;
    counterQueue = new Queue<Customer>(counterQueueLength);
  }

  /**
   * Returns the string representation of the counter.
   * 
   * @return A string consisting of the string representation of 
   * the counter and its id.
   */ 
  @Override
  public String toString() {
    return "S" + this.counterId + " " + this.counterQueue.toString();
  }

  /**
   * Compares 2 Counter objects.
   * x.compareTo(y) == -1 iff x is the preferred counter.
   * x.compareTo(y) == 1 iff y is the preferred counter.
   *
   * @param c  The counter object to be compared with.
   *
   * @return -1 or 1
   *
   */
  @Override
  public int compareTo(Counter c) {
    if (!this.isAvailable() && !c.isAvailable()) {
      // if both counters are not available, compare lengths of queues
      if (this.getCounterQueueLength() < c.getCounterQueueLength()) {
        return -1;
      } else if (this.getCounterQueueLength() > c.getCounterQueueLength()) {
        return 1;
      } else {
        return this.getId() < c.getId() ? -1 : 1;
      }

    } else if (this.isAvailable() && !c.isAvailable()) {
      // one counter is available
      return -1;

    } else if (!this.isAvailable() && c.isAvailable()) {
      // one counter is available
      return 1;

    } else {
      // if both counters available, differentiate
      // using id
      return this.getId() < c.getId() ? -1 : 1;
    }
  }

  /**
   * Returns the Id of this counter.
   *
   * @return The integer representation of this counter Id.
   *
   */
  public int getId() {
    return this.counterId;
  }

  /**
   * Returns the length of the counter queue.
   *
   * @return The integer length of the counter queue.
   *
   */
  public int getCounterQueueLength() {
    return this.counterQueue.length();
  }


  /** 
   * Checks if this counter is available.
   *
   * @return Boolean representation of whether this counter
   *         is available.
   */
  public boolean isAvailable() {
    return this.availability && this.counterQueue.isEmpty();
  }

  /** 
   * Labels the counter as unavailable.
   */
  public void markUnavailable() {
    this.availability = false;
  }

  /**
   * Labels the counter as available.
   */
  public void markAvailable() {
    this.availability = true;
  }

  /**
   * Checks whether the bank queue is full
   *
   * @return Boolean representation of whether
   *         the bank queue is full.
   */
  public boolean isQueueFull() {
    return this.counterQueue.isFull();
  }

  /**
   * Checks whether the bank queue is empty
   *
   * @return Boolean representation of whether
   *         the bank queue is empty.
   */ 
  public boolean isQueueEmpty() {
    return this.counterQueue.isEmpty();
  }

  /**
   * Adds a customer to the queue.
   *
   * @param customer Customer object to be added
   *                 to the queue.
   */
  public void addToQueue(Customer customer) {
    this.counterQueue.enq(customer);
  }

  /**
   * Removes the first customer from the queue and returns it.
   *
   * @return The next customer in the queue.
   *
   */
  public Customer removeFromQueue() {
    return this.counterQueue.deq();
  }

}
