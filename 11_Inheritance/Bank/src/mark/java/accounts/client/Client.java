package mark.java.accounts.client;

import mark.java.accounts.bank.BankAccount;

public abstract class Client {

    protected BankAccount bankAccount;
    public Client() {
        bankAccount = new BankAccount();
    }
    public Client(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    // Списание  средтв
    public void withdrawCash(double amount){
        bankAccount.withdrawCash(amount);
    }

    // Внесение средств
    public void depositCash(double amount){
        bankAccount.depositCash(amount);
    }

    // Узнать остаток
    public String getInfo() {
        return "Баланс :" + bankAccount.getCash();
    }
}
