/**
 * This class encapsulates an arrival event 
 * in the simulation. 
 * 
 * @author Keagan Pang
 *
 */

public class ArrivalEvent extends Event {
 
  /** 
   * The Customer object associated with this event.  
   */
  private Customer customer;
  
  /**
   * The current instance of Bank in the simulation.
   */
  private Bank bank;

  /**
   * Constructor for an arrival event.
   *
   * @param time        The time this event occurs.
   * @param customer    The Customer object associated with this
   *                    event.
   * @param bank        The Bank object that represents 
   *                    the current bank state.
   */
  public ArrivalEvent(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  /**
   * Returns the string representation of the arrival event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = String.format(": %s arrived %s",
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

    if (!this.bank.counterAvailable() && !this.bank.isQueueFull()) {
      // If there are no available counters, the customer should try to join the queue.
      return new Event[] {
        new JoinQueueEvent(this.getTime(), this.bank, this.customer)
      };

    } else if (!this.bank.counterAvailable() && this.bank.isQueueFull()) {
      // If the queue is full, customer should leave.
      return new Event[] { 
        new DepartureEvent(this.getTime(), this.customer)
      };
         
    } else if (this.bank.counterAvailable() && this.bank.isQueueEmpty()) {
      // Else, the customer should go the the first 
      // available counter and get served.
      return new Event[] { 
        new ServiceBeginEvent(this.getTime(), this.customer, this.bank.firstFreeCounter(), 
            this.bank)
      };

    } else {
      return new Event[] {};
    }

  }

}
