package hw15.q2;

public class InvalidInputData extends RuntimeException{

    public InvalidInputData() {
    }

    public InvalidInputData(String message) {
        super(message);
    }

    public InvalidInputData(String message, Throwable cause) {
        super(message, cause);
    }
}
