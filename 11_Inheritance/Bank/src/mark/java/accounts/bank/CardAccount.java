package mark.java.accounts.bank;

public class CardAccount extends BankAccount{

    public CardAccount(double cash) {
        super(cash);
    }

    public CardAccount() {}

    @Override
    public void withdrawCash(double amount){
        System.out.println("При снятии денег будет взиматься комиссия 1%");
        amount *= 0.99;
        cash -= amount;
    }

    @Override
    public void depositCash(double amount) {
        super.depositCash(amount);
    }

    @Override
    public double getCash() {
        return super.getCash();
    }

    @Override
    public void send(BankAccount receiver, double amount) {
        super.send(receiver, amount);
    }

    @Override
    public String getInfo() {
        return "Карточный счёт:\n" +
                cash + " ₽";
    }
}
