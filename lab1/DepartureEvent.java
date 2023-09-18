/**
* This class encapsulates a departure event 
* in the simulation.
* @author Keagan Pang
*/

public class DepartureEvent extends Event {
 
  /** 
   * The id of a customer associated with this event.  
   * First customer has id 0. Next is 1, 2, etc. 
   */
  private int customerId;

  /**
   * Constructor for a departure event.
   *
   * @param time       The time this event occurs.
   * @param customerId The customer associated with this
   *                   event.
   */
  public DepartureEvent(double time, int customerId) {
    super(time);
    this.customerId = customerId;
  }

  /**
   * Returns the string representation of the event,
   * depending on the type of event.
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = String.format(": Customer %d departed", this.customerId);
    return super.toString() + str;
  }  

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    return new Event[] {};
  } 


}
