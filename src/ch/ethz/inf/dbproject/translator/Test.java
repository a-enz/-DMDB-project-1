package ch.ethz.inf.dbproject.translator;

import java.sql.*;
import ch.ethz.inf.dbproject.database.MySQLConnection;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection sqlConnection = MySQLConnection.getInstance().getConnection();
		String[] tables = {"Person", "Case"};
		String saveInDist = "src/ch/ethz/inf/dbproject.translator";
		String extension = ".txt";
		
		Translator t = new Translator(sqlConnection, tables, saveInDist, extension);
		
		t.translate();
		
	}
}
