package ua.omld.jpc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Oleksii Kostetskyi
 */
public class EnumValidator implements ConstraintValidator<EnumConstraint, CharSequence> {

	private List<String> enumValues;

	/**
	 * Initializes the validator in preparation for
	 * {@link #isValid(Object, ConstraintValidatorContext)} calls.
	 * The constraint annotation for a given constraint declaration
	 * is passed.
	 * <p>
	 * This method is guaranteed to be called before any use of this instance for
	 * validation.
	 * <p>
	 * The default implementation is a no-op.
	 *
	 * @param constraintAnnotation annotation instance for a given constraint declaration
	 */
	@Override
	public void initialize(EnumConstraint constraintAnnotation) {
		enumValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
				.map(Enum::name).collect(Collectors.toList());
	}

	/**
	 * Implements the validation logic.
	 * The state of {@code value} must not be altered.
	 * <p>
	 * This method can be accessed concurrently, thread-safety must be ensured
	 * by the implementation.
	 *
	 * @param value   object to validate
	 * @param context context in which the constraint is evaluated
	 * @return {@code false} if {@code value} does not pass the constraint
	 */
	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		return value != null && enumValues.contains(value.toString().toUpperCase());
	}
}
