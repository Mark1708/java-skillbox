package mark.java.accounts.client;

import mark.java.accounts.bank.BankAccount;

public class LegaEntity extends Client{
    public LegaEntity() {
    }

    public LegaEntity(BankAccount bankAccount) {
        super(bankAccount);
    }

    @Override
    public void withdrawCash(double amount) {
        super.withdrawCash(amount * 1.01);

    }


    @Override
    public String getInfo() {
        return "У юридических лиц — снятие с комиссией 1%.\n" +
                "Баланс :" + bankAccount.getCash();
    }
}
