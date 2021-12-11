package de.htwberlin.mauterhebung.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.exceptions.DataException;
import de.htwberlin.mauterhebung.AbstractDataGateway;
import de.htwberlin.mauterhebung.dao.Mautkategorie;
import de.htwberlin.mauterhebung.utils.Achszahl;

public class MautkategorieMapper extends AbstractDataGateway {

  private static final Logger L = LoggerFactory.getLogger(MautkategorieMapper.class);

  private static MautkategorieMapper instance = null;

  private MautkategorieMapper() {
    super();
  }

  public static MautkategorieMapper getInstance() {
    if (instance == null) {
      instance = new MautkategorieMapper();
    }
    return instance;
  }

  public Mautkategorie rsToMautkategorie(ResultSet rs) throws SQLException {
    Mautkategorie mautkategorie = new Mautkategorie();
    mautkategorie.setKategorieId(rs.getInt("kategorie_id"));
    mautkategorie.setSsklId(rs.getInt("sskl_id"));
    mautkategorie.setKatBezeichnung(rs.getString("kat_bezeichnung").charAt(0));
    mautkategorie.setAchszahl(new Achszahl(rs.getString("achszahl")));
    mautkategorie.setMautsatzJeKm(rs.getFloat("mautsatz_je_km"));
    return mautkategorie;
  }

  public Mautkategorie findById(int kategorieId) {
    L.info("getMautkategorieFromSskl({})", kategorieId);
    Mautkategorie mautkategorie = null;
    try (PreparedStatement stmt =
        getConnection().prepareStatement("SELECT * FROM mautkategorie WHERE kategorie_id = ?")) {
      stmt.setInt(1, kategorieId);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        mautkategorie = rsToMautkategorie(rs);
      }
    } catch (SQLException e) {
      L.error("Fehler beim Auslesen der Mautkategorie mit SSKL-ID {}", kategorieId, e);
      throw new DataException(e);
    }
    return mautkategorie;
  }

  public Mautkategorie findBySsklId(int ssklId) {
    L.info("getMautkategorieFromSskl({})", ssklId);
    Mautkategorie mautkategorie = null;
    try (PreparedStatement stmt =
        getConnection().prepareStatement("SELECT * FROM mautkategorie WHERE sskl_id = ?")) {
      stmt.setInt(1, ssklId);

      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        mautkategorie = rsToMautkategorie(rs);
      }
    } catch (SQLException e) {
      L.error("Fehler beim Auslesen der Mautkategorie mit SSKL-ID {}", ssklId);
      L.error("", e);
      throw new DataException(e);
    }
    return mautkategorie;
  }

  public Mautkategorie findBySsklIdAndAchszahl(int ssklId, int achszahl) {
    L.info("getMautkategorieFromSskl({})", ssklId);
    List<Mautkategorie> mautkategorieListe = new ArrayList<>();
    try (PreparedStatement stmt =
        getConnection().prepareStatement("SELECT * FROM mautkategorie WHERE sskl_id = ?")) {
      stmt.setInt(1, ssklId);

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        mautkategorieListe.add(rsToMautkategorie(rs));
      }
    } catch (SQLException e) {
      L.error("Fehler beim Auslesen der Mautkategorie mit SSKL-ID {}", ssklId);
      L.error("", e);
      throw new DataException(e);
    }
    return mautkategorieListe.stream().filter((a) -> a.getAchszahl().isValid(achszahl)).findFirst()
        .orElse(null);
  }
}
