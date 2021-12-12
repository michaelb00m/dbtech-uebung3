package de.htwberlin.mauterhebung;

import java.sql.Connection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.exceptions.AlreadyCruisedException;
import de.htwberlin.exceptions.DataException;
import de.htwberlin.exceptions.InvalidVehicleDataException;
import de.htwberlin.exceptions.UnkownVehicleException;
import de.htwberlin.mauterhebung.dao.Buchung;
import de.htwberlin.mauterhebung.dao.Buchungstatus;
import de.htwberlin.mauterhebung.dao.Fahrzeug;
import de.htwberlin.mauterhebung.dao.Fahrzeuggerat;
import de.htwberlin.mauterhebung.mapper.BuchungMapper;
import de.htwberlin.mauterhebung.mapper.BuchungstatusMapper;
import de.htwberlin.mauterhebung.mapper.FahrzeugMapper;
import de.htwberlin.mauterhebung.mapper.FahrzeuggeratMapper;
import de.htwberlin.mauterhebung.mapper.MautabschnittMapper;
import de.htwberlin.mauterhebung.mapper.MauterhebungMapper;
import de.htwberlin.mauterhebung.mapper.MautkategorieMapper;
import de.htwberlin.mauterhebung.utils.UtilGateway;
import de.htwberlin.mauterhebung.verfahren.AutoVerfahren;
import de.htwberlin.mauterhebung.verfahren.BuchVerfahren;

/**
 * Die Klasse realisiert den AusleiheService.
 * 
 * @author Patrick Dohmeier
 */
public class MauterServiceImpl implements IMauterhebung {

	private static final Logger L = LoggerFactory.getLogger(MauterServiceImpl.class);
	private Connection connection;

	private static final FahrzeugMapper fahrzeugMapper = FahrzeugMapper.getInstance();
	private static final BuchungMapper buchungMapper = BuchungMapper.getInstance();
	private static final FahrzeuggeratMapper fahrzeuggeratMapper = FahrzeuggeratMapper.getInstance();

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
		configureGateways(connection);
	}

	private void configureGateways(Connection connection) {
		List<DataGateway> gateways =
				List.of(MautkategorieMapper.getInstance(), FahrzeugMapper.getInstance(),
						MautabschnittMapper.getInstance(), BuchungMapper.getInstance(),
						MauterhebungMapper.getInstance(), FahrzeuggeratMapper.getInstance(),
						UtilGateway.getInstance(), BuchungstatusMapper.getInstance());
		for (DataGateway gateway : gateways) {
			gateway.setConnection(connection);
		}
	}

	private Connection getConnection() {
		if (connection == null) {
			throw new DataException("Connection not set");
		}
		return connection;
	}

	@Override
	public float berechneMaut(int mautAbschnitt, int achszahl, String kennzeichen)
			throws UnkownVehicleException, InvalidVehicleDataException, AlreadyCruisedException {
		L.info("berechneMaut({}, {}, {})", mautAbschnitt, achszahl, kennzeichen);
		Fahrzeug fahrzeug = fahrzeugMapper.findByKennzeichen(kennzeichen);
		Buchung buchung = buchungMapper.findByKennzeichen(kennzeichen);
		if (fahrzeug != null) {
			Fahrzeuggerat fahrzeuggerat = fahrzeuggeratMapper.findByFzId(fahrzeug.getFzId());
			if (fahrzeuggerat != null && fahrzeuggerat.getStatus() != "inactive")
				return Math.round(AutoVerfahren.berechneMaut(fahrzeug, mautAbschnitt, achszahl) * 100)
						/ 100f;
		}
		if (buchung != null) {
			if (buchung.getAbschnittsId() == mautAbschnitt) {
				return Math.round(BuchVerfahren.berechneMaut(buchung, mautAbschnitt, achszahl) * 100)
						/ 100f;
			}
		}
		L.error("Fehler beim Auslesen des Fahrzeugs mit Kennzeichen {}", kennzeichen);
		throw new UnkownVehicleException("Fahrzeug mit Kennzeichen " + kennzeichen + " nicht gefunden");
	}

}
