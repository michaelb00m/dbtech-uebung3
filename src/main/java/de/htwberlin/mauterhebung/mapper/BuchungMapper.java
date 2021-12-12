package de.htwberlin.mauterhebung.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.exceptions.DataException;
import de.htwberlin.mauterhebung.AbstractDataGateway;
import de.htwberlin.mauterhebung.dao.Buchung;

public class BuchungMapper extends AbstractDataGateway {

  private static final Logger L = LoggerFactory.getLogger(BuchungMapper.class);

  private static BuchungMapper instance = null;

  private BuchungMapper() {
    super();
  }

  public static BuchungMapper getInstance() {
    if (instance == null) {
      instance = new BuchungMapper();
    }
    return instance;
  }

  public Buchung rsToBuchung(ResultSet rs) throws SQLException {
    Buchung buchung = new Buchung();
    buchung.setBuchungId(rs.getInt("buchung_id"));
    buchung.setBId(rs.getInt("b_id"));
    buchung.setAbschnittsId(rs.getInt("abschnitts_id"));
    buchung.setKategorieId(rs.getInt("kategorie_id"));
    buchung.setKennzeichen(rs.getString("kennzeichen"));
    buchung.setBuchungsdatum(rs.getTimestamp("buchungsdatum"));
    buchung.setBefahrungsdatum(rs.getTimestamp("befahrungsdatum"));
    buchung.setKosten(rs.getFloat("kosten"));
    return buchung;
  }

  public Buchung findByKennzeichen(String kennzeichen) {
    Buchung buchung = null;
    try (PreparedStatement ps =
        getConnection().prepareStatement("SELECT * FROM buchung WHERE kennzeichen = ?")) {
      ps.setString(1, kennzeichen);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          buchung = rsToBuchung(rs);
        }
      }
    } catch (SQLException e) {
      L.error("", e);
      throw new DataException(e);
    }
    return buchung;
  }

  public void updateBefahrungsdatum(Buchung buchung) {
    L.info("update({})", buchung);
    try {
      PreparedStatement ps = getConnection().prepareStatement(
          "UPDATE buchung SET befahrungsdatum = ? WHERE buchung_id = ?");
      ps.setTimestamp(1, buchung.getBefahrungsdatum());
      ps.setLong(2, buchung.getBuchungId());
    } catch (SQLException e) {
      L.error("Fehler beim Aktualisieren des Buchungstatus mit ID {}", buchung.getBuchungId(), e);
      throw new DataException(e);
    }
  }

  public void update(Buchung buchung) {
    L.info("update({})", buchung);
    try {
      PreparedStatement ps = getConnection().prepareStatement(
          "UPDATE buchung SET b_id = ?, abschnitts_id = ?, kategorie_id = ?, kennzeichen = ?, buchungsdatum = ?, befahrungsdatum = ?, kosten = ? WHERE buchung_id = ?");
      ps.setLong(1, buchung.getBId());
      ps.setLong(2, buchung.getAbschnittsId());
      ps.setLong(3, buchung.getKategorieId());
      ps.setString(4, buchung.getKennzeichen());
      ps.setTimestamp(5, buchung.getBuchungsdatum());
      ps.setTimestamp(6, buchung.getBefahrungsdatum());
      ps.setDouble(7, buchung.getKosten());
      ps.setLong(8, buchung.getBuchungId());
      ps.executeUpdate();
    } catch (SQLException e) {
      L.error("Fehler beim Aktualisieren des Buchungstatus mit ID {}", buchung.getBuchungId(), e);
      throw new DataException(e);
    }
  }
}
