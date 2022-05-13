package mark.java.accounts.bank;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DepositAccount extends BankAccount {

    private Calendar firstDeposit = null;

    public DepositAccount() {}

    public DepositAccount(double cash) {
        super(cash);
        firstDeposit = new GregorianCalendar();
    }

    @Override
    public void depositCash(double amount){
        cash += amount;
        firstDeposit = new GregorianCalendar();
    }

    @Override
    public void withdrawCash(double amount){
        if (firstDeposit == null) {
            System.out.println("Сначала внесите деньги");
        } else if (new GregorianCalendar().getTime().getTime() - firstDeposit.getTime().getTime() >=  2628000000L){
            cash -= amount;

        } else {
            System.out.println("Нельзя снимать деньги в течение месяца после последнего внесения\n" +
                    "Осталоь: " + (new GregorianCalendar().getActualMaximum(Calendar.DAY_OF_MONTH) - firstDeposit.getTime().getDay()) + " дней\n" +
                    "Дата последнего внесения: " + new SimpleDateFormat("dd.MM.yyyy").format(firstDeposit.getTime()) + "\n" +
                    "Сегодня: " + new SimpleDateFormat("dd.MM.yyyy").format(new GregorianCalendar().getTime()));
        }
    }

    @Override
    public void send(BankAccount receiver, double amount) {
        cash -= amount;
        receiver.depositCash(amount);
    }

    @Override
    public String getInfo() {
        return "Депозитный расчётный счёт:\n" +
                "Дата открытия:" + new SimpleDateFormat("dd.MM.yyyy").format(firstDeposit.getTime()) + "\n"+
                cash + " ₽";
    }
}
