package de.htwberlin.mauterhebung.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.exceptions.DataException;
import de.htwberlin.mauterhebung.AbstractDataGateway;

public class UtilGateway extends AbstractDataGateway {

  private static final Logger L = LoggerFactory.getLogger(UtilGateway.class);

  private static UtilGateway instance = null;

  private UtilGateway() {
    super();
  }

  public static UtilGateway getInstance() {
    if (instance == null) {
      instance = new UtilGateway();
    }
    return instance;
  }

  public Integer getNextId(String tableName, String idColumnName) {
    Integer result = null;
    String sql = "SELECT MAX(" + idColumnName + ") AS id FROM " + tableName;
    L.debug("getNextId({}, {}): ", tableName, idColumnName);
    try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
      // statement.setString(1, idColumnName);
      // statement.setString(2, tableName);
      try (ResultSet rs = statement.executeQuery()) {
        if (rs.next()) {
          result = rs.getInt("id");
        }
      }
      if (result == null) {
        result = Integer.valueOf(0);
      }
      result = Integer.valueOf(result.intValue() + 1);
    } catch (SQLException e) {
      L.error("Fehler beim Ermitteln der nächsten {} des Tables {}", idColumnName, tableName, e);
      throw new DataException(e);
    }
    return result;
  }

  public Long getNextLongId(String tableName, String idColumnName) {
    Long result = null;
    String sql = "SELECT MAX(?) AS id FROM ?";
    L.debug("getNextId({}, {}): ", tableName, idColumnName);
    try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
      statement.setString(1, idColumnName);
      statement.setString(2, tableName);
      L.debug("getNextId(): {}", statement.toString());
      try (ResultSet rs = statement.executeQuery()) {
        if (rs.next()) {
          result = rs.getLong(idColumnName);
        }
      }
      if (result == null) {
        result = Long.valueOf(0);
      }
      result = Long.valueOf(result.intValue() + 1);
    } catch (SQLException e) {
      L.error("Fehler beim Ermitteln der nächsten {} des Tables {}", idColumnName, tableName, e);
      throw new DataException(e);
    }
    return result;
  }

}
