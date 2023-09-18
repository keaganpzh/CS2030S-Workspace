/**
* This class encapsulates a service end event 
* in the simulation.
* @author Keagan Pang
*/

public class ServiceEndEvent extends Event {
   
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
   * Constructor for a service end event.
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
  public ServiceEndEvent(double time, int customerId, 
      double serviceTime, int counterId, Counter bankCounter) {
    super(time);
    this.serviceTime = serviceTime;
    this.bankCounter = bankCounter;
    this.counterId = counterId;
    this.customerId = customerId;
  }

  /**
   * Returns the string representation of the service end event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = String.format(": Customer %d service done (by Counter %d)", 
          this.customerId, this.counterId);
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
 
    // The current event is a service-end event.
    // Mark the counter as available, then schedule
    // a departure event at the current time.
    this.bankCounter.markAvailable(this.counterId);
    return new Event[] { 
      new DepartureEvent(this.getTime(), this.customerId)
    };
  } 


}
