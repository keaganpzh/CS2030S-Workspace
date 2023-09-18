/**
 * This class represents the status of the counters
 * in the simulation.
 * Only one Counter object should be instantiated
 * per simulation.
 *
 * @author Keagan Pang
 *
 */
public class Counter {
 
  /**
   * An array to indicate if a service counter is
   * available.  available[i] is true if and only
   * if service counter i is available to serve.
   */  
  private boolean[] available;

  /**
   * Constructor for a Counter object.
   *
   * @param numOfCounters The number of counters that 
   *                      exist in the simulation.
   */
  public Counter(int numOfCounters) {
    this.available = new boolean[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      this.available[i] = true;    
    } 
  }
  
  // Accessors
 
 /**
  * Retrieves the number of counters in the current simulation
  *
  * @return Integer representing the number of counters.
  *
  */
  public int getNumCounters() {
    return this.available.length;
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
      if (this.available[i]) {
        result = true;
      }
    }
    return result;
  }
 
  /**
   * Checks for the first available counter.
   *
   * @return The counter ID of the first available counter.
   *
   */
  public int firstFreeCounter() {
    int counterId = -1;
    for (int i = 0; i < this.getNumCounters(); i++) {
      if (this.available[i]) {
        counterId = i;
        break;
      }
    }
    return counterId;
  }

  // Mutators
  
  /**
   * Changes a counter's status to unavailable.
   *
   * @param counterId The counter ID of the counter to be
   *                  marked unavailable.
   */
  public void markUnavailable(int counterId) {
    this.available[counterId] = false;
  }

  /**
   * Changes a counter's status to available.
   *
   * @param counterId The counter ID of the counter to be
   *                  marked available.
   */
  public void markAvailable(int counterId) {
    this.available[counterId] = true;
  }

}
