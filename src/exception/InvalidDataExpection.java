package exception;

public class InvalidDataExpection extends RuntimeException{
    public InvalidDataExpection(String message) {
        super(message);
    }
}
