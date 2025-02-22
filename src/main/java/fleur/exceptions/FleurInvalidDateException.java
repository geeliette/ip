package fleur.exceptions;

public class FleurInvalidDateException extends FleurException {
    public FleurInvalidDateException() {
        super("Alors, your date format must be in dd/mm/yyyy");
    }
}
