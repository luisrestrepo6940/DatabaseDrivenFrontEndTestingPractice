package co.com.certification.practiceautomatedtesting.exceptions;

public class PropertiesDoesNotLoadException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PropertiesDoesNotLoadException(Exception e) {
        super(e);
    }
}