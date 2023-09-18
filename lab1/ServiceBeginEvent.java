/**
 * This class encapsulates a service begin event 
 * in the simulation.
 * @author Keagan Pang
 */

public class ServiceBeginEvent extends Event {

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
   * Constructor for a service begin event.
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
  public ServiceBeginEvent(double time, int customerId, 
      double serviceTime, int counterId, Counter bankCounter) {
    super(time);
    this.serviceTime = serviceTime;
    this.bankCounter = bankCounter;
    this.counterId = counterId;
    this.customerId = customerId;
  }

  /**
   * Returns the string representation of the service begin event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = String.format(": Customer %d service begin (by Counter %d)", 
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
    // The current event is a service-begin event.  
    // Mark the counter is unavailable, then schedule
    // a service-end event at the current time + service time.
    this.bankCounter.markUnavailable(this.counterId);  
    double endTime = this.getTime() + this.serviceTime;
    return new Event[] { 
      new ServiceEndEvent(endTime, this.customerId, 
          this.serviceTime, this.counterId, this.bankCounter)
    };
  }


}
