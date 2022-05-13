package mark.java.accounts.client;

import mark.java.accounts.bank.BankAccount;

public class IndividualEntrepreneur extends Client{
    public IndividualEntrepreneur() {
    }

    public IndividualEntrepreneur(BankAccount bankAccount) {
        super(bankAccount);
    }

    @Override
    public void withdrawCash(double amount) {
        if (amount < 1000){
            super.withdrawCash(amount * 1.01);
        } else {
            super.withdrawCash(amount * 1.005);
        }

    }


    @Override
    public String getInfo() {
        return "У ИП — пополнение с комиссией 1%, если сумма меньше 1000 рублей. \n" +
                "И с комиссией 0,5%, если сумма больше либо равна 1000 рублей.\n" +
                "Баланс :" + bankAccount.getCash();
    }
}
