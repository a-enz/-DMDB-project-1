package ch.ethz.inf.dbproject.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ch.ethz.inf.dbproject.translator.Translator;

import com.mysql.jdbc.Driver;

/**
 * A Wrapper around an SQL Connection
 */
public final class MySQLConnection {

	/*
	 * The connection parameters. You should change these to point to your
	 * installation specific values.
	 */
	public static final String USERNAME = "root";//"sql336107";
	public static final String PASSWORD = "1234";//"kR7!vN3*";
	public static final String HOSTNAME = "localhost";//"sql3.freesqldatabase.com";
	public static final int PORT = 3306;
	public static final String DATABASE = "dmdb2014";//"sql336107";

	private final Connection connection;

	/**
	 * Singleton instance: We want to avoid re-establishing connections across
	 * web server requests.
	 */
	private static MySQLConnection instance = null;

	public static synchronized  MySQLConnection getInstance() {
		if(instance == null) {
			 instance = new MySQLConnection();
		}
		return instance;
	}

	private MySQLConnection() {
		Connection connection = null;
		try {
			new Driver();
			connection = DriverManager.getConnection("jdbc:mysql://" + HOSTNAME	+ ":" + PORT + "/" + DATABASE, USERNAME, PASSWORD);
		} catch (final SQLException e) {
			/**
			 * Make sure that we really see this error.
			 */
			System.err.println("Could not connect to MYSQL. Is the server running?");
			JOptionPane.showMessageDialog(null,	
					"Could not connect to MYSQL. Is the server running?\n" + "Error in " + this.getClass().getName() + ".",
					"Critical Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		this.connection = connection;
		
		//---------generate byte files--------
		Test(connection);
		//---------------------------------
	}

	public final Connection getConnection() {
		return this.connection;
	}
	
	public final void Test(Connection sqlConnection){
		String[] tables = {"Person"};
		String saveInDist = "/home/andi/Documents/DMDB/";
		
		String extension = ".txt";
		
		Translator t = new Translator(sqlConnection, tables, saveInDist, extension);
		
		t.translate();
	}
}