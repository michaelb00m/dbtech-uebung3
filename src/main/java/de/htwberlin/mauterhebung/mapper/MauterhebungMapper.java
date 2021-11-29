package de.htwberlin.mauterhebung.mapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.exceptions.DataException;
import de.htwberlin.mauterhebung.AbstractDataGateway;
import de.htwberlin.mauterhebung.dao.Mauterhebung;
import de.htwberlin.mauterhebung.utils.UtilGateway;

public class MauterhebungMapper extends AbstractDataGateway {
  private static final Logger L = LoggerFactory.getLogger(MauterhebungMapper.class);
  private static UtilGateway utilGateway = UtilGateway.getInstance();

  private static MauterhebungMapper instance = null;

  private MauterhebungMapper() {
    super();
  }

  public static MauterhebungMapper getInstance() {
    if (instance == null) {
      instance = new MauterhebungMapper();
    }
    return instance;
  }

  public void insert(Mauterhebung mauterhebung) {
    mauterhebung.setMautId(utilGateway.getNextId("mauterhebung", "maut_id"));
    L.info("insertMauterhebung({})", mauterhebung);
    try (PreparedStatement ps = getConnection().prepareStatement(
        "INSERT INTO mauterhebung (maut_id, abschnitts_id, fzg_id, kategorie_id, befahrungsdatum, kosten) VALUES (?, ?, ?, ?, ?, ?)")) {
      ps.setInt(1, mauterhebung.getMautId());
      ps.setInt(2, mauterhebung.getAbschnittId());
      ps.setLong(3, mauterhebung.getFzgId());
      ps.setInt(4, mauterhebung.getKategorieId());
      ps.setDate(5, mauterhebung.getBefahrungsdatum());
      ps.setFloat(6, mauterhebung.getKosten());
      ps.executeUpdate();
    } catch (SQLException e) {
      L.error("Fehler beim Einfügen der Mauterhebung", e);
      throw new DataException("Fehler beim Einfügen der Mauterhebung");
    }
  }
}
