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

  private Counter counter;

  private boolean joinBankQueue;

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
    this.joinBankQueue = true;
  }

  /**
   * Overloaded constructor for a join queue event
   *
   * @param counter The counter that owns the queue to join.
   *
   */
  public JoinQueueEvent(double time, Bank bank, Customer customer,
      Counter counter) {
    super(time);
    this.bank = bank;
    this.customer = customer;
    this.counter = counter;
    this.joinBankQueue = false;
  }

  /**
   * Returns the string representation of the arrival event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = "";
    if (this.joinBankQueue) {
      str = String.format(": %s joined bank queue %s", 
          this.customer.toString(), this.bank.queueToString());
    } else {
      str = String.format(": %s joined counter queue (at %s)",
          this.customer.toString(), this.counter.toString());
    }
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
    if (this.joinBankQueue) {
      this.bank.addToQueue(this.customer);
    } else {
      this.counter.addToQueue(this.customer);
    }
    return new Event[] {};
  } 

}
