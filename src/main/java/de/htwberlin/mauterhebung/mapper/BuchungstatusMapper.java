package de.htwberlin.mauterhebung.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.exceptions.DataException;
import de.htwberlin.mauterhebung.AbstractDataGateway;
import de.htwberlin.mauterhebung.dao.Buchungstatus;

public class BuchungstatusMapper extends AbstractDataGateway {
  private static final Logger L = LoggerFactory.getLogger(BuchungstatusMapper.class);

  private static BuchungstatusMapper instance = null;

  private BuchungstatusMapper() {
    super();
  }

  public static BuchungstatusMapper getInstance() {
    if (instance == null) {
      instance = new BuchungstatusMapper();
    }
    return instance;
  }

  public Buchungstatus rsToBuchungstatus(ResultSet rs) {
    Buchungstatus buchungstatus = new Buchungstatus();
    try {
      buchungstatus.setBId(rs.getInt("b_id"));
      buchungstatus.setStatus(rs.getString("status"));
    } catch (Exception e) {
      L.error("Fehler beim Erstellen eines Buchungstatus-Objekts aus ResultSet", e);
    }
    return buchungstatus;
  }

  public Buchungstatus findById(long bId) {
    L.info("findById({})", bId);
    Buchungstatus buchungstatus = null;
    try {
      PreparedStatement statement =
          getConnection().prepareStatement("SELECT * FROM buchungstatus WHERE b_id = ?");
      statement.setLong(1, bId);
      try (ResultSet rs = statement.executeQuery()) {
        if (rs.next()) {
          buchungstatus = rsToBuchungstatus(rs);
        }
      }
    } catch (SQLException e) {
      L.error("Fehler beim Auslesen des Buchungstatus mit ID {}", bId, e);
      throw new DataException(e);
    }
    return buchungstatus;
  }

  public Buchungstatus findByStatus(String status) {
    L.info("findByStatus(\"{}\")", status);
    Buchungstatus buchungstatus = null;
    try (PreparedStatement ps =
        getConnection().prepareStatement("SELECT * FROM buchungstatus WHERE status = ?");) {
      ps.setString(1, status);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          buchungstatus = rsToBuchungstatus(rs);
        }
      }
    } catch (SQLException e) {
      L.error("Fehler beim Auslesen des Buchungstatus mit Status {}", status, e);
      throw new DataException(e);
    }
    return buchungstatus;
  }
}
