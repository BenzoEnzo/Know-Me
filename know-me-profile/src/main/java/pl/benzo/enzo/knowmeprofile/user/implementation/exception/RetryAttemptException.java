package pl.benzo.enzo.knowmeprofile.user.implementation.exception;

public class RetryAttemptException extends IllegalArgumentException {
    public RetryAttemptException(String message){
        super(message);
    }
}
