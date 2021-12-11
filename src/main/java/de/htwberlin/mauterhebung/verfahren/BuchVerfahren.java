package de.htwberlin.mauterhebung.verfahren;

import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.exceptions.AlreadyCruisedException;
import de.htwberlin.exceptions.InvalidVehicleDataException;
import de.htwberlin.mauterhebung.dao.Buchung;
import de.htwberlin.mauterhebung.dao.Buchungstatus;
import de.htwberlin.mauterhebung.dao.Mautkategorie;
import de.htwberlin.mauterhebung.mapper.BuchungMapper;
import de.htwberlin.mauterhebung.mapper.BuchungstatusMapper;
import de.htwberlin.mauterhebung.mapper.MautkategorieMapper;

public class BuchVerfahren {

  private static final Logger L = LoggerFactory.getLogger(BuchVerfahren.class);
  private static final MautkategorieMapper mautkategorieMapper = MautkategorieMapper.getInstance();
  private static final BuchungstatusMapper buchungstatusMapper = BuchungstatusMapper.getInstance();
  private static final BuchungMapper buchungMapper = BuchungMapper.getInstance();

  public static float berechneMaut(Buchung buchung, int mautAbschnitt, int achszahl) {
    L.info("berechneBuchungVerfahren({}, {}, {})", buchung, mautAbschnitt, achszahl);
    Mautkategorie mautkategorie = mautkategorieMapper.findById(buchung.getKategorieId());
    if (!mautkategorie.getAchszahl().isValid(achszahl)) {
      L.error("Fahrzeug mit Kennzeichen {} hat laut Datenbank {} Achsen, aber {} Achsen wurden gebucht",
          buchung.getKennzeichen(), mautkategorie.getAchszahl(), achszahl);
      throw new InvalidVehicleDataException(
          "Fahrzeug mit Kennzeichen " + buchung.getKennzeichen() + " hat laut Datenbank " + mautkategorie.getAchszahl()
              + " Achsen, aber " + achszahl + " Achsen wurden angegeben");
    }
    Buchungstatus buchungstatus = buchungstatusMapper.findById(buchung.getBId());
    if (!buchungstatus.getStatus().equals("offen")) {
      L.error("Buchung mit ID {} ist nicht offen, sondern {}", buchung.getBId(), buchungstatus.getStatus());
      throw new AlreadyCruisedException("Buchung mit ID " + buchung.getBId() + " ist nicht offen");
    }
    buchung.setBId(buchungstatusMapper.findByStatus("abgeschlossen").getBId());
    buchung.setBefahrungsdatum(new Date(System.currentTimeMillis()));
    buchungMapper.update(buchung);
    return buchung.getKosten();
  }
}
