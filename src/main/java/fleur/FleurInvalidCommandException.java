package fleur;

public class FleurInvalidCommandException extends FleurException {
    public FleurInvalidCommandException() {
        super("Désolé, I do not know what you mean!");
    }
}
