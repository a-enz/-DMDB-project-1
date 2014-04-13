package ch.ethz.inf.dbproject.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Object that represents a category of project (i.e. Theft, Assault...) 
 */
public final class Category {

	private final String CatName;
	private final String Parent;

	public Category(final String name, final String parent) {
		this.CatName = name;
		this.Parent = parent;
	}
	
	public Category(ResultSet rs) throws SQLException{
		this.CatName = rs.getString("CatName");
		this.Parent = rs.getString("Parent");
	}

	public final String getCatName() {
		return CatName;
	}

	public String getParent() {
		return Parent;
	}
	
}
