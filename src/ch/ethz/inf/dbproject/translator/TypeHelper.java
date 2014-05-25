package ch.ethz.inf.dbproject.translator;

import java.sql.Types;

public class TypeHelper {
	public static int typeConvert(int type) {
		switch (type) {
		case Types.VARCHAR:
			return 0;
		case Types.INTEGER:
			return 1;
		case Types.FLOAT:
			return 2;
		case Types.DOUBLE:
			return 3;
		case Types.DATE:
			return 4;
		default:
			return 0;
		}
	}
}
