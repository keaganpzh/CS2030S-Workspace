/**
 * This class encapsulates a join queue event 
 * in the simulation.
 *
 * @author Keagan Pang
 *
 */

public class JoinQueueEvent extends Event {
  
  /** 
   * The Customer object associated with this event.  
   */
  private Customer customer;
  
  /**
   * The current instance of Bank in the simulation.
   */
  private Bank bank;

  /**
   * Constructor for a join queue event.
   *
   * @param time       The time this event occurs.
   * @param bank       The Bank object that represents
   *                   the current bank state.
   * @param customer   The Customer object associated with this
   *                   event.
   */
  public JoinQueueEvent(double time, Bank bank, Customer customer) {
    super(time);
    this.bank = bank;
    this.customer = customer;
  }

  /**
   * Returns the string representation of the arrival event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = String.format(": %s joined queue %s", 
        this.customer.toString(), this.bank.queueToString());
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
    this.bank.addToQueue(this.customer);
    return new Event[] {};
  } 

}
