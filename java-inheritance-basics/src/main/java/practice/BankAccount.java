package practice;

public class BankAccount {
  protected double amount;
  public double getAmount() {
    return amount;
  }

  public void put(double amountToPut) {
    if (amountToPut >= 0) {
      amount += amountToPut;
    }
  }

  public void take(double amountToTake) {
    if (amountToTake >= 0 &&  amount >= amountToTake) {
      amount -= amountToTake;
    }
  }
}
