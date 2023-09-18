/**
 * This class represents a bank in the simulation.
 * Only one bank object should be instantiated
 * per simulation.
 *
 * @author Keagan Pang
 *
 */
public class Bank {
 
  /**
   * An array of Counter objects.
   * Each entry holds an individual Counter object.
   */  
  private Array<Counter> counters;

  /**
   * The Queue object in the bank.
   * The bank has a queue.
   */
  private Queue<Customer> bankQueue;

  /**
   * The integer number of counters in the bank.
   */
  private int numOfCounters;

  /**
   * Constructor for a Bank object.
   *
   * @param numOfCounters The number of counters that 
   *                      exist in the simulation.
   * @param bankQueue     The Queue object in the bank.
   *
   */
  public Bank(int numOfCounters, int counterQueueLength, int bankQueueLength) {
    this.counters = new Array<Counter>(numOfCounters);
    this.numOfCounters = numOfCounters;
    for (int i = 0; i < numOfCounters; i++) {
      this.counters.set(i, new Counter(i, counterQueueLength));   
    } 
    this.bankQueue = new Queue<Customer>(bankQueueLength);
  }

  /**
   * Returns the preferred counter for a new customer to go to.
   * A counter is preferred is it has a shorter queue and/or it
   * is available.
   *
   * @return the preferred counter.
   *
   */
  public Counter counterToGo() {
    return this.counters.min();
  }

  // Accessors
 
  /**
   * Determines if there is an available counter.
   *
   * @return Boolean representation of whether a counter 
   *         is available.
   */
  public boolean counterAvailable() {
    boolean result = false;
    for (int i = 0; i < this.numOfCounters; i++) {
      if (this.counters.get(i).isAvailable()) {
        result = true;
      }
    }
    return result;
  }
 
  /**
   * Checks for the first available counter.
   *
   * @return The Counter object of the first available counter.
   *
   */
  public Counter firstFreeCounter() {
    Counter freeCounter = null;
    for (int i = 0; i < this.numOfCounters; i++) {
      if (this.counters.get(i).isAvailable()) {
        freeCounter = this.counters.get(i);
        break;
      }
    }
    return freeCounter;
  }


  /**
   * Checks whether the bank queue is full
   *
   * @return Boolean representation of whether
   *         the bank queue is full.
   */
  public boolean isQueueFull() {
    return this.bankQueue.isFull();
  }

  /**
   * Checks whether the bank queue is empty
   *
   * @return Boolean representation of whether
   *         the bank queue is empty.
   */ 
  public boolean isQueueEmpty() {
    return this.bankQueue.isEmpty();
  }

  /**
   * Adds a customer to the queue.
   *
   * @param customer Customer object to be added
   *                 to the queue.
   */
  public void addToQueue(Customer customer) {
    this.bankQueue.enq(customer);
  }

  /**
   * Removes the first customer from the queue and returns it.
   *
   * @return The next customer in the queue.
   *
   */
  public Customer removeFromQueue() {
    return this.bankQueue.deq();
  }
  
  /**
   * Converts the bank queue to its String representation.
   *
   * @return String representation of the bank queue.
   *
   */
  public String queueToString() {
    return this.bankQueue.toString();
  }

}

