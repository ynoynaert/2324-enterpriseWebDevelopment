package exceptions;

import java.util.Locale;
import java.util.ResourceBundle;

public class ValueNotFoundError extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final ResourceBundle messages = ResourceBundle.getBundle("i18n/messages", Locale.getDefault());


	public ValueNotFoundError(String c, Long id) {
		super(String.format(messages.getString("valueNotFoundError"), new Object[] {c, id}));
	}
}
