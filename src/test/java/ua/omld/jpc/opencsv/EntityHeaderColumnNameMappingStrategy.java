package ua.omld.jpc.opencsv;

import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.bean.ConverterDate;
import com.opencsv.bean.CsvConverter;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvBadConverterException;
import ua.omld.jpc.entity.Identifiable;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

/**
 * Maps data to objects using the column names in the first row of the CSV file
 * as reference.
 * If the field of the Entity representing {@link Identifiable} object uses
 * {@link EntityCsvConverter} to convert id to an Entity.
 * If the field of the Entity is {@link LocalDate} uses {@link ConverterDate} with
 * the pattern "yyyy-MM-dd" to convert it to a field.
 *
 * @author Oleksii Kostetskyi
 */
public class EntityHeaderColumnNameMappingStrategy<T> extends HeaderColumnNameMappingStrategy<T> {

	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final String ISO = "ISO";

	/**
	 * Given the information provided, determines the appropriate built-in
	 * converter to be passed in to the {@link com.opencsv.bean.BeanField} being created.
	 *
	 * @param field           The field of the bean type in question
	 * @param elementType     The type to be generated by the converter (on reading)
	 * @param locale          The locale for conversion on reading. May be null or an
	 *                        empty string if a locale is not in use.
	 * @param writeLocale     The locale for conversion on writing. May be null or
	 *                        an empty string if a locale is not in use.
	 * @param customConverter An optional custom converter
	 * @return The appropriate converter for the necessary conversion
	 * @throws CsvBadConverterException If the converter cannot be instantiated
	 */
	@Override
	protected CsvConverter determineConverter(Field field, Class<?> elementType, String locale, String writeLocale,
											  Class<? extends AbstractCsvConverter> customConverter) throws CsvBadConverterException {
		CsvConverter converter = null;
		if (customConverter == null) {
			if (Arrays.asList(elementType.getInterfaces()).contains(Identifiable.class)) {
				converter = new EntityCsvConverter((Class<Identifiable>) elementType);
			}
			if (elementType.isAssignableFrom(LocalDate.class)) {
				converter = new ConverterDate(elementType, locale, writeLocale, Locale.getDefault(), DATE_PATTERN, DATE_PATTERN,
						ISO, ISO);
			}
		}
		return converter != null ? converter : super.determineConverter(field, elementType, locale, writeLocale,
				customConverter);
	}
}
