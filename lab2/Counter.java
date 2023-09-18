/**
 * This class represents individual counters in the simulation.
 * One Counter object represents one counter in the simulation.
 *
 * @author Keagan Pang
 *
 */
public class Counter {

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
   * Constructor for a Counter object
   *
   * @param id The id assigned to this counter.
   *
   */
  public Counter(int id) {
    this.counterId = id;
    this.availability = true;
  }
  
  /**
   * Returns the string representation of the counter.
   * 
   * @return A string consisting of the string representation of 
   * the counter and its id.
   */ 
  @Override
  public String toString() {
    return "S" + this.counterId;
  }
  
  /** 
   * Checks if this counter is available.
   *
   * @return Boolean representation of whether this counter
   *         is available.
   */
  public boolean isAvailable() {
    return this.availability;
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

}
