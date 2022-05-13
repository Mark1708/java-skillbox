package mark.java.accounts.client;

import mark.java.accounts.bank.BankAccount;

public class Individual extends Client{

    public Individual() {
    }

    public Individual(BankAccount bankAccount) {
        super(bankAccount);
    }


    @Override
    public String getInfo() {
        return "У физических лиц пополнение и снятие происходит без комиссии.\nБаланс :" + bankAccount.getCash();
    }
}
