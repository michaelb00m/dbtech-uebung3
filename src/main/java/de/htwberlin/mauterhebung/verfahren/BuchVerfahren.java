package de.htwberlin.mauterhebung.verfahren;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.mauterhebung.dao.Buchung;

public class BuchVerfahren {

	private static final Logger L = LoggerFactory.getLogger(BuchVerfahren.class);

  public static float berechneMaut(Buchung buchung, int mautAbschnitt, int achszahl) {
		L.info("berechneBuchungVerfahren({}, {}, {})", buchung, mautAbschnitt, achszahl);
    return 0;
  } 
}
