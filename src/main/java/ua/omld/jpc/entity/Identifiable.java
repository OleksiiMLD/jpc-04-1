package ua.omld.jpc.entity;

/**
 * Provides basic interface for all entities.
 *
 * @author Oleksii Kostetskyi
 */
public interface Identifiable<ID extends Number> {

	ID getId();

	void setId(ID id);
}
