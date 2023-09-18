/**
 * This class encapsulates a service begin event 
 * in the simulation.
 *
 * @author Keagan Pang
 *
 */

public class ServiceBeginEvent extends Event {

  /** 
   * The Customer object associated with this event.  
   */
  private Customer customer;

  /**
   * The current instance of Bank in the simulation.
   */
  private Bank bank;

  /**
   * The Counter object associated with this event.
   */
  private Counter counter;
  
  /**
   * Constructor for a service begin event.
   *
   * @param time        The time this event occurs.
   * @param customer    The Customer object associated with this
   *                    event.
   * @param counter     The Counter object associated with
   *                    this event.
   * @param bank        The Bank object that represents 
   *                    the current bank state.
   */
  public ServiceBeginEvent(double time, Customer customer, Counter counter, Bank bank) {
    super(time);
    this.bank = bank;
    this.counter = counter;
    this.customer = customer;
  }

  /**
   * Returns the string representation of the service begin event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = String.format(": %s %s begin (by %s)", 
        this.customer.toString(), this.customer.getServiceType(), this.counter.toString());
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
    // The current event is a service-begin event.  
    // Mark the counter is unavailable, then schedule
    // a service-end event at the current time + service time.
    this.counter.markUnavailable();  
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] { 
      new ServiceEndEvent(endTime, this.customer, this.counter, this.bank)
    };
  }

}
