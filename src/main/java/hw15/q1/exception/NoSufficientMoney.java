package hw15.q1.exception;

public class NoSufficientMoney extends RuntimeException{

    public NoSufficientMoney() {
    }

    public NoSufficientMoney(String message) {
        super(message);
    }

    public NoSufficientMoney(String message, Throwable cause) {
        super(message, cause);
    }
}
