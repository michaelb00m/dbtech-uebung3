package de.htwberlin.mauterhebung.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.exceptions.DataException;
import de.htwberlin.mauterhebung.AbstractDataGateway;
import de.htwberlin.mauterhebung.dao.Mautabschnitt;

public class MautabschnittMapper extends AbstractDataGateway {

  private static final Logger L = LoggerFactory.getLogger(MautabschnittMapper.class);

  private static MautabschnittMapper instance = null;

  private MautabschnittMapper() {
    super();
  }

  public static MautabschnittMapper getInstance() {
    if (instance == null) {
      instance = new MautabschnittMapper();
    }
    return instance;
  }

  public Mautabschnitt rsToMautabschnitt(ResultSet rs) throws SQLException {
    Mautabschnitt mautabschnitt = new Mautabschnitt();
    mautabschnitt.setAbschnittsId(rs.getInt("abschnitts_id"));
    mautabschnitt.setLaenge(rs.getFloat("laenge"));
    mautabschnitt.setStartKoordinate(rs.getString("start_koordinate"));
    mautabschnitt.setZielKoordinate(rs.getString("ziel_koordinate"));
    mautabschnitt.setName(rs.getString("name"));
    mautabschnitt.setAbschnittsTyp(rs.getString("abschnittstyp"));
    return mautabschnitt;
  }

  public Mautabschnitt findById(int mautabschnittId) {
		L.info("getMautabschnitt({})", mautabschnittId);
		Mautabschnitt mautabschnitt = null;
		try {
			PreparedStatement ps =
					getConnection().prepareStatement("SELECT * FROM mautabschnitt WHERE abschnitts_id= ?");
			ps.setInt(1, mautabschnittId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					mautabschnitt = rsToMautabschnitt(rs);
				}
			}
		} catch (SQLException e) {
			L.error("Fehler beim Auslesen des Mautabschnitts mit ID {}", mautabschnittId);
      L.error("", e);
			throw new DataException(e);
		}
		return mautabschnitt;
  }
}
