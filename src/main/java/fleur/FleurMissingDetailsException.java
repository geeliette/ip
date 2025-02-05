package fleur;

public class FleurMissingDetailsException extends FleurException {
    public FleurMissingDetailsException() {
        super("Ah, non! Your task eez missing le description and/or date.");
    }
}
