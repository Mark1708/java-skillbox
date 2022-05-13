package mark.java.accounts.bank;

public class BankAccount {

    protected double cash;

    public BankAccount() {
        this.cash = 0.0;
    }

    protected BankAccount(double cash) {
        this.cash = cash;
    }

    // Списание  средтв
    public void withdrawCash(double amount){
        cash -= amount;

    }

    // Внесение средств
    public void depositCash(double amount){
        cash += amount;
    }

    // Узнать остаток
    public double getCash() {
        return cash;
    }

    // Метод для отправки денег с одного счёта на другой
    protected void send(BankAccount receiver, double amount) {
        withdrawCash(amount);
        receiver.depositCash(amount);
    }

    public String getInfo() {
        return "BankAccount{" +
                "cash=" + cash +
                '}';
    }
}
