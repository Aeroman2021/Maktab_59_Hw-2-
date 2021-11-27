package hw15.q1.exception;

public class DataNotFound extends RuntimeException{
    public DataNotFound() {
    }

    public DataNotFound(String message) {
        super(message);
    }

    public DataNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
