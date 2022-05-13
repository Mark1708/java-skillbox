package mark.java;


public class Operation {
    private String type;
    private String cardNumber;
    private String currency;
    private String date;
    private  String operationNumber;
    private String description;
    private Double incomingAmount;
    private Double expensesAmount;


    public Operation(String type, String cardNumber, String currency, String date, String operationNumber, String description, Double incomingAmount, Double expensesAmount) {
        this.type = type;
        this.cardNumber = cardNumber;
        this.currency = currency;
        this.date = date;
        this.operationNumber = operationNumber;
        this.description = description;
        this.incomingAmount = incomingAmount;
        this.expensesAmount = expensesAmount;
    }

    public Operation(String[] line){
        this.type = line[0];
        this.cardNumber = line[1];
        this.currency = line[2];
        this.date = line[3];
        this.operationNumber = line[4];
        this.description = line[5];
        this.incomingAmount = Double.valueOf(line[6].replace("\"", "").replace(",","."));
        this.expensesAmount = Double.valueOf(line[7].replace("\"", "").replace(",","."));
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getIncomingAmount() {
        return incomingAmount;
    }

    public void setIncomingAmount(Double incomingAmount) {
        this.incomingAmount = incomingAmount;
    }

    public Double getExpensesAmount() {
        return expensesAmount;
    }

    public void setExpensesAmount(Double expensesAmount) {
        this.expensesAmount = expensesAmount;
    }
}
