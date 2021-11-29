package de.htwberlin.mauterhebung;

import java.sql.Connection;

public interface DataGateway {
  void setConnection(Connection connection);
}
