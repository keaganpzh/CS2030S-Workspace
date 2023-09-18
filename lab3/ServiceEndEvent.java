/**
 * This class encapsulates a service end event 
 * in the simulation.
 *
 * @author Keagan Pang
 *
 */
public class ServiceEndEvent extends Event {
 
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
   * Constructor for a service end event.
   *
   * @param time        The time this event occurs.
   * @param customer    The Customer object associated with this
   *                    event.
   * @param counter     The Counter object associated with
   *                    this event.
   * @param bank        The Bank object that represents 
   *                    the current bank state.
   */
  public ServiceEndEvent(double time, Customer customer, Counter counter, Bank bank) {
    super(time);
    this.bank = bank;
    this.counter = counter;
    this.customer = customer;
  }

  /**
   * Returns the string representation of the service end event,
   *
   * @return A string representing the event.
   */
  @Override
  public String toString() {
    String str = String.format(": %s %s done (by %s)",
        this.customer.toString(), this.customer.taskToString(), this.counter.toString());
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
    // mark the current counter as available.
    this.counter.markAvailable();
    if (!this.counter.isQueueEmpty()) {
      Customer nextInCounterQueue = this.counter.removeFromQueue();
      // if there are customers in the queue, serve the next in line.
      if (!this.bank.isQueueEmpty()) {
        Customer nextInBankQueue = this.bank.removeFromQueue();
        // if there are customers in bank queue,
        // add the first one to counter queue.
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), nextInCounterQueue, this.counter, this.bank),
          new JoinQueueEvent(this.getTime(), this.bank, nextInBankQueue, this.counter)
        };
      } else {
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), nextInCounterQueue, this.counter, this.bank)
        };
      }

    } else if (this.counter.isQueueEmpty() && !this.bank.isQueueEmpty()) { 
      // if there are no customers in the counter queue,
      // serve the next customer in the bank queue.
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer),
        new ServiceBeginEvent(this.getTime(), this.bank.removeFromQueue(), this.counter, this.bank)
      };

    } else {
      return new Event[] {
        // customer who is done leaves.
        new DepartureEvent(this.getTime(), this.customer)
      };
    }

  }

}
