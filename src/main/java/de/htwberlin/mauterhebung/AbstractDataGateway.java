package de.htwberlin.mauterhebung;

import java.sql.Connection;
import de.htwberlin.exceptions.DataException;

public abstract class AbstractDataGateway implements DataGateway {
  protected Connection connection;

  public void setConnection(Connection connection) {
    this.connection = connection;
  }

  protected Connection getConnection() {
		if (connection == null) {
			throw new DataException("Connection not set");
		}
    return connection;
  }

}
