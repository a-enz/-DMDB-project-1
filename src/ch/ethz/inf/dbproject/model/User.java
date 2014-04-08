package ch.ethz.inf.dbproject.model;

/**
 * Object that represents a registered in user.
 */
public final class User {

	private final String username;
	private final String passwort;
	private final String name;
	
	public User(final String username, final String passwort, final String name) {
		this.passwort = passwort;
		this.username = username;
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswort() {
		return passwort;
	}

	public String getName() {
		return name;
	}

}
