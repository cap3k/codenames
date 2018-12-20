package fr.codenames.dao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOSQL {
	protected Connection connection = null;
	
	protected void connect() throws SQLException {
		if (this.connection == null) {
			this.connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/codenames?serverTimezone=UTC", "root", "");
		}
	}
}