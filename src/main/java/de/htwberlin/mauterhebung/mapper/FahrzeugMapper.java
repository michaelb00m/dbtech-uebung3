package de.htwberlin.mauterhebung.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.exceptions.DataException;
import de.htwberlin.mauterhebung.AbstractDataGateway;
import de.htwberlin.mauterhebung.dao.Fahrzeug;

public class FahrzeugMapper extends AbstractDataGateway {

  private static final Logger L = LoggerFactory.getLogger(FahrzeugMapper.class);

  private static FahrzeugMapper instance = null;

  private FahrzeugMapper() {
    super();
  }

  public static FahrzeugMapper getInstance() {
    if (instance == null) {
      instance = new FahrzeugMapper();
    }
    return instance;
  }

  public Fahrzeug rsToFahrzeug(ResultSet rs) throws SQLException {
    Fahrzeug fahrzeug = new Fahrzeug();
    fahrzeug.setFzId(rs.getLong("fz_id"));
    fahrzeug.setSsklId(rs.getInt("sskl_id"));
    fahrzeug.setNutzerId(rs.getInt("nutzer_id"));
    fahrzeug.setKennzeichen(rs.getString("kennzeichen"));
    fahrzeug.setFin(rs.getString("fin"));
    fahrzeug.setAchsen(rs.getInt("achsen"));
    fahrzeug.setGewicht(rs.getDouble("gewicht"));
    fahrzeug.setAnmeldedatum(rs.getTimestamp("anmeldedatum"));
    fahrzeug.setAbmeldedatum(rs.getTimestamp("abmeldedatum"));
    fahrzeug.setZulassungsland(rs.getString("zulassungsland"));
    return fahrzeug;
  }

  public Fahrzeug findByKennzeichen(String kennzeichen) {
    Fahrzeug fahrzeug = null;
    try (PreparedStatement ps =
        getConnection().prepareStatement("SELECT * FROM FAHRZEUG WHERE KENNZEICHEN = ?")) {
      ps.setString(1, kennzeichen);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          fahrzeug = rsToFahrzeug(rs);
        }
      }
    } catch (SQLException e) {
      L.error("", e);
      throw new DataException(e);
    }
    return fahrzeug;

  }
}
