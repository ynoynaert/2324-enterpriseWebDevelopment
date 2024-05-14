package exceptions;

public class ValueNotFoundError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValueNotFoundError(String c, Long id) {
		super(String.format("%s with ID %s not found", c, id));
	}
}
