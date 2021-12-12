package de.htwberlin.mauterhebung.verfahren;

import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.exceptions.InvalidVehicleDataException;
import de.htwberlin.mauterhebung.MauterServiceImpl;
import de.htwberlin.mauterhebung.dao.Fahrzeug;
import de.htwberlin.mauterhebung.dao.Fahrzeuggerat;
import de.htwberlin.mauterhebung.dao.Mautabschnitt;
import de.htwberlin.mauterhebung.dao.Mauterhebung;
import de.htwberlin.mauterhebung.dao.Mautkategorie;
import de.htwberlin.mauterhebung.mapper.FahrzeuggeratMapper;
import de.htwberlin.mauterhebung.mapper.MautabschnittMapper;
import de.htwberlin.mauterhebung.mapper.MauterhebungMapper;
import de.htwberlin.mauterhebung.mapper.MautkategorieMapper;

public class AutoVerfahren {
  private static final Logger L = LoggerFactory.getLogger(MauterServiceImpl.class);
  private static MautabschnittMapper mautabschnittMapper = MautabschnittMapper.getInstance();
  private static MautkategorieMapper mautkategorieMapper = MautkategorieMapper.getInstance();
  private static MauterhebungMapper mauterhebungMapper = MauterhebungMapper.getInstance();
  private static FahrzeuggeratMapper fahrzeuggeratMapper = FahrzeuggeratMapper.getInstance();

  public static float berechneMaut(Fahrzeug fahrzeug, int mautAbschnitt, int achszahl) {
    L.info("berechneAutoVerfahren({}, {}, {})", fahrzeug, mautAbschnitt, achszahl);
    if (fahrzeug.getAchsen() != achszahl) {
      L.error(
          "Fahrzeug mit Kennzeichen {} hat laut Datenbank {} Achsen, aber {} Achsen wurden erkannt",
          fahrzeug.getKennzeichen(), fahrzeug.getAchsen(), achszahl);
      throw new InvalidVehicleDataException(
          "Fahrzeug mit Kennzeichen " + fahrzeug.getKennzeichen() + " hat laut Datenbank "
              + fahrzeug.getAchsen() + " Achsen, aber " + achszahl + " Achsen wurden erkannt");
    }
    Fahrzeuggerat fahrzeuggerat = fahrzeuggeratMapper.findByFzId(fahrzeug.getFzId());
    Mautabschnitt mautabschnitt = mautabschnittMapper.findById(mautAbschnitt);
    Mautkategorie mautkategorie =
        mautkategorieMapper.findBySsklIdAndAchszahl(fahrzeug.getSsklId(), achszahl);
    float maut = mautabschnitt.getLaenge() * mautkategorie.getMautsatzJeKm() / 100000;
    Mauterhebung mauterhebung =
        new Mauterhebung(mautabschnitt.getAbschnittsId(), fahrzeuggerat.getFzgId(),
            mautkategorie.getKategorieId(), new Timestamp(System.currentTimeMillis()), maut);
    mauterhebungMapper.insert(mauterhebung);
    return maut;
  }
}
