/**
 * This class encapsulates an arrival event 
 * in the simulation.
 * @author Keagan Pang
 */

public class ArrivalEvent extends Event {
 
  /** 
   * The id of a customer associated with this event.  
   * First customer has id 0. Next is 1, 2, etc. 
   */
  private int customerId;
  
  /**
   * The service time of the customer associated
   * this event.  
   */
  private double serviceTime;

  /**
   * The current instance of Counter.
   */
  private Counter bankCounter;


  /**
   * The id of the counter associated with this event.
   */
  private int counterId;
 
  /**
   * Constructor for an arrival event.
   *
   * @param time       The time this event occurs.
   * @param customerId The customer associated with this
   *                   event.
   * @param serviceTime The time this customer takes 
   *                    for service.
   * @param counterId   The id of the counter associated with
   *                    this event.
   * @param bankCounter The Counter object that represents 
   *                    the current counter state.
   */
  public ArrivalEvent(double time, int customerId, 
      double serviceTime, Counter bankCounter) {
    super(time);
    this.serviceTime = serviceTime;
    this.bankCounter = bankCounter;
    this.customerId = customerId;
  }

   /**
   * Returns the string representation of the arrival event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = String.format(": Customer %d arrives", this.customerId);
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

    if (!this.bankCounter.counterAvailable()) {
      // If there are no available counters, the customer
      // should depart.
      return new Event[] { 
          new DepartureEvent(this.getTime(), this.customerId)
      };
      
    } else {
        // Else, the customer should go the the first 
        // available counter and get served.
        return new Event[] { 
          new ServiceBeginEvent(this.getTime(), this.customerId, 
              this.serviceTime, this.bankCounter.firstFreeCounter(), this.bankCounter)
        };
    }

  }

}
