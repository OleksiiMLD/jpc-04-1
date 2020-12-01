package ua.omld.jpc.opencsv;

import com.opencsv.bean.AbstractCsvConverter;
import org.apache.commons.lang3.StringUtils;
import ua.omld.jpc.entity.Identifiable;

import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Converts CSV field representing Entity id to the Entity with the specified Id.
 * Entity must implement {@link Identifiable} interface.
 *
 * @author Oleksii Kostetskyi
 */
public class EntityCsvConverter extends AbstractCsvConverter {

	private final Class<? extends Identifiable> entityClass;

	public EntityCsvConverter(Class<? extends Identifiable> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Method for converting from a string to the proper data type of the
	 * destination field.
	 *
	 * @param value The string from the selected field of the CSV file. If the
	 *              field is marked as required in the annotation, this value is guaranteed
	 *              not to be {@code null}, empty or blank according to
	 *              {@link StringUtils#isBlank(CharSequence)}
	 * @return An {@link Object} representing the input data converted
	 * into the proper type
	 */
	@Override
	public Object convertToRead(String value) {
		try {
			Identifiable<Number> entity = entityClass.getDeclaredConstructor().newInstance();
			entity.setId(NumberFormat.getInstance().parse(value.trim()));
			return entity;
		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
			throw new RuntimeException("Cannot instantiate Entity: ", ex);
		} catch (ParseException pEx) {
			throw new IllegalArgumentException("Bad number: " + value);
		}
	}
}
