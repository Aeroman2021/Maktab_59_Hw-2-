package hw16.q1.exception;

public class DuplicateInputDataException extends RuntimeException{
    public DuplicateInputDataException() {
    }

    public DuplicateInputDataException(String message) {
        super(message);
    }

    public DuplicateInputDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
