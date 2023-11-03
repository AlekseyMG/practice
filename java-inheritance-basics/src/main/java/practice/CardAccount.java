package practice;

public class CardAccount extends BankAccount {
    // не забывайте, обращаться к методам и конструкторам родителя
    // необходимо используя super, например, super.put(10D);


    @Override
    public void take(double amountToTake) {
        super.take(amountToTake * 1.01);
    }
}
