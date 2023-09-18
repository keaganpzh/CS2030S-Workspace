/**
 * This class encapsulates a departure event 
 * in the simulation.
 *
 * @author Keagan Pang
 *
 */

public class DepartureEvent extends Event {
 
  /** 
   * The Customer object associated with this event.  
   */
  private Customer customer;
  
  /**
   * Constructor for a departure event.
   *
   * @param time       The time this event occurs.
   * @param customer   The Customer object associated with this
   *                   event.
   */
  public DepartureEvent(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  /**
   * Returns the string representation of the departure event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = String.format(": %s departed", this.customer.toString());
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
