package hw15.q1.exception;

public class DuplicateInputData extends RuntimeException {
    public DuplicateInputData() {
    }

    public DuplicateInputData(String message) {
        super(message);
    }

    public DuplicateInputData(String message, Throwable cause) {
        super(message, cause);
    }
}
