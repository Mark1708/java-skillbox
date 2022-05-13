import exceptions.MyException;

public class Customer {
    private String name;
    private String phone;
    private String eMail;

    public Customer(String name, String phone, String eMail) throws MyException {
        if (!isPhoneNum(phone)) {
            throw new MyException("Неправильный формат номера", phone);

        } else if (!isMail(eMail)) {
            throw new MyException("Wrong mail! Available mail examples:\n" +
                "test@mail.ru\n" +
                "test1@mail.ru\n" +
                "TEST@mail.ru", eMail);
        }
        this.name = name;
        this.phone = phone;
        this.eMail = eMail;
    }

    private boolean isPhoneNum(String num) {
        return num.matches(".*((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}.*");
    }

    private boolean isMail(String mail) {
        return mail.matches(".*(|(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6}).*");
    }

    @Override
    public String toString()
    {
        return name + " - " + eMail + " - " + phone;
    }
}
