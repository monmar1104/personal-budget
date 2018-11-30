package exception;

public class EmptyArgumentException extends RuntimeException {

    private String message;

    public EmptyArgumentException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
