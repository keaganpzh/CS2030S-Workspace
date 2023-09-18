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
  private Counter[] counters;

  /**
   * The Queue object in the bank.
   * The bank has a queue.
   */
  private Queue bankQueue;

  /**
   * Constructor for a Bank object.
   *
   * @param numOfCounters The number of counters that 
   *                      exist in the simulation.
   * @param bankQueue     The Queue object in the bank.
   *
   */
  public Bank(int numOfCounters, Queue bankQueue) {
    this.counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      this.counters[i] = new Counter(i);    
    } 
    this.bankQueue = bankQueue;
  }
  
  // Accessors
 
  /**
   * Retrieves the number of counters in the current simulation
   *
   * @return Integer representing the number of counters.
   *
   */
  public int getNumCounters() {
    return this.counters.length;
  }

  /**
   * Determines if there is an available counter.
   *
   * @return Boolean representation of whether a counter 
   *         is available.
   */
  public boolean counterAvailable() {
    boolean result = false;
    for (int i = 0; i < this.getNumCounters(); i++) {
      if (this.counters[i].isAvailable()) {
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
    for (int i = 0; i < this.getNumCounters(); i++) {
      if (this.counters[i].isAvailable()) {
        freeCounter = this.counters[i];
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
    return (Customer) this.bankQueue.deq();
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
