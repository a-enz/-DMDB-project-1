package ch.ethz.inf.dbproject.model;

/**
 * Object that represents a category of project (i.e. Theft, Assault...) 
 */
public final class Category {

	private final String name;
	private final String parent;

	public Category(final String name, final String parent) {
		this.name = name;
		this.parent = parent;
	}

	public final String getName() {
		return name;
	}

	public String getParent() {
		return parent;
	}
	
}
