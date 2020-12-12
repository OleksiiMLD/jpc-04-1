package ua.omld.jpc.opencsv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Reads and parses CSV-file with the given name. Searches file as resource at classpath.
 * <ul>
 *  <b>NOTE:</b>
 *  <li>Header data must be present. Header must be consistent with the Entity fields.</li>
 *  <li>Empty lines are allowed.</li>
 *  <li>Quote character is ".</li>
 * </ul>
 *
 * @author Oleksii Kostetskyi
 */
public abstract class CSVDataReader<E> {

	private final String name;
	protected List<E> entities;

	protected CSVDataReader(String dataFilePath) {
		Class<E> entityClass = (Class<E>) (((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0]);
		this.name = entityClass.getSimpleName().toUpperCase();
		try {
			URL resource = getClass().getClassLoader().getResource(dataFilePath);
			if (resource == null) {
				throw new IllegalArgumentException("Bad file name: " + dataFilePath);
			}
			FileReader fileReader = new FileReader(resource.getFile());
			HeaderColumnNameMappingStrategy<E> mappingStrategy = new EntityHeaderColumnNameMappingStrategy<>();
			mappingStrategy.setType(entityClass);

			CsvToBeanBuilder<E> builder = new CsvToBeanBuilder<E>(fileReader)
					.withType(entityClass)
					.withIgnoreLeadingWhiteSpace(true)
					.withMappingStrategy(mappingStrategy)
					.withIgnoreEmptyLine(true)
					.withQuoteChar('"');
			CsvToBean<E> csvToBean = builder.build();
			entities = csvToBean.parse();
			if (entities.size() == 0) {
				throw new IllegalStateException("There are no entities in file or wrong file format.");
			}

		} catch (FileNotFoundException fnfEx) {
			throw new RuntimeException("", fnfEx);
		}
	}

	public E get(String constant) {
		if (entities.size() > 0 && constant.startsWith(name + "_")) {
			return entities.get(Integer.parseInt(constant.substring(name.length() + 1)) - 1);
		}
		return null;
	}

	public List<E> getList() {
		return Collections.unmodifiableList(entities);
	}
}
