package hw15.q1.exception;

public class InvalidCreditCardData extends RuntimeException{

    public InvalidCreditCardData() {
    }

    public InvalidCreditCardData(String message) {
        super(message);
    }

    public InvalidCreditCardData(String message, Throwable cause) {
        super(message, cause);
    }
}
