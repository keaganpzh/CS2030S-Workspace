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
   * Integer representation of the customer's
   * intended task at the bank.
   * 0 for Deposit, 1 for Withdrawal.
   */
  private int intention;

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
    this.intention = intention;
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
   * Returns a String corresponding to the customer's intention
   * at the bank.
   *
   * @return "Deposit" if the customer wants to deposit,
   *         "Withdrawal" if the customer wants to withdraw.
   */
  public String getServiceType() {
    if (this.intention == 0) {
      return "Deposit";
    } else if (this.intention == 1) {
      return "Withdrawal";
    } else {
      return "";
    }
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
