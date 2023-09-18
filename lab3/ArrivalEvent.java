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
    
    if (this.bank.counterToGo().isAvailable()) {
      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, this.bank.counterToGo(), this.bank)
      };
    } else if (!this.bank.counterToGo().isAvailable() && !this.bank.counterToGo().isQueueFull()) {
      return new Event[] {
        new JoinQueueEvent(this.getTime(), this.bank, this.customer, this.bank.counterToGo())
      };
    } else if (!this.bank.isQueueFull()) {
      return new Event[] { 
        new JoinQueueEvent(this.getTime(), this.bank, this.customer)
      };
    } else {
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer)
      };
    }
  }

}
