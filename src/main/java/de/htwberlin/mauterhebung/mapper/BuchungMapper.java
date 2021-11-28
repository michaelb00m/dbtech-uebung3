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
    buchung.setbId(rs.getInt("b_id"));
    buchung.setAbschnittsId(rs.getInt("abschnitts_id"));
    buchung.setKategorieId(rs.getInt("kategorie_id"));
    buchung.setKennzeichen(rs.getString("kennzeichen"));
    buchung.setBuchungsdatum(rs.getDate("buchungsdatum"));
    buchung.setBefahrungsdatum(rs.getDate("befahrungsdatum"));
    buchung.setKosten(rs.getDouble("kosten"));
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
}
