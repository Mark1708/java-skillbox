package exceptions;

public class MyException extends Exception{
    private String data;

    public MyException(String message, String num) {
        super(message);
        this.data = num;
    }
}
