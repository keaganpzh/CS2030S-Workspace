/**
 * This class represents individual customers in the simulation.
 * One Customer object represents a single customer.
 *
 * @author Keagan Pang
 *
 */
public class Customer {

  /** 
   * Integer id associated with the customer.
   * id starts from 0.
   */
  private int id;

  /**
   * The Task object associated with the Customer.
   */
  private Task task;

  /**
   * Double representation of the duration of the customer's
   * service at the bank.
   */
  private double serviceTime;
  
  /**
   * Constructor for a Customer object.
   *
   * @param id          Integer id assigned to the customer.
   * @param intention   Integer representing customer's intended
   *                    task.
   * @param serviceTime Double representation of customer's 
   *                    service duration.
   */
  public Customer(int id, int intention, double serviceTime) {
    this.id = id;
    this.serviceTime = serviceTime;
    if (intention == 0) {
      this.task = new DepositTask();
    } else if (intention == 1) {
      this.task = new WithdrawalTask();
    } else {
      this.task = new OpenAccountTask();
    }
  }

  /**
   * Returns a String representation of a Customer object.
   *
   * @return String containing a customer and associated id
   */
  @Override
  public String toString() {
    return "C" + this.id;
  }

  /**
   * Returns a String representation of the Customer's task.
   *
   * @return String containing the customer's task.
   *
   */
  public String taskToString() {
    return this.task.toString();
  }

  /**
   * Returns the duration of the customer's service
   *
   * @return Double represenation of a customer's
   *         service duration.
   */         
  public double getServiceTime() {
    return this.serviceTime;
  }

}
