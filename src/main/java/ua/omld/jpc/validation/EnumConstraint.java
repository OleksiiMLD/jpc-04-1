package ua.omld.jpc.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Oleksii Kostetskyi
 */
@Target(value = {FIELD, METHOD, PARAMETER, CONSTRUCTOR, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = EnumValidator.class)
public @interface EnumConstraint {

	Class<? extends Enum<?>> enumClass();

	String message() default "Not allowed value for enumeration.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
