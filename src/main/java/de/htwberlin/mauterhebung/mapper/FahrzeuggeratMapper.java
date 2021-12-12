package de.htwberlin.mauterhebung.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.htwberlin.exceptions.DataException;
import de.htwberlin.mauterhebung.AbstractDataGateway;
import de.htwberlin.mauterhebung.dao.Fahrzeuggerat;

public class FahrzeuggeratMapper extends AbstractDataGateway {
	private static final Logger L = LoggerFactory.getLogger(FahrzeuggeratMapper.class);

	private static FahrzeuggeratMapper instance = null;

	private FahrzeuggeratMapper() {
		super();
	}

	public static FahrzeuggeratMapper getInstance() {
		if (instance == null) {
			instance = new FahrzeuggeratMapper();
		}
		return instance;
	}

	public Fahrzeuggerat rsToFahrzeuggerat(ResultSet rs) throws SQLException {
		Fahrzeuggerat fahrzeuggerat = new Fahrzeuggerat();
		fahrzeuggerat.setFzgId(rs.getLong("fzg_id"));
		fahrzeuggerat.setFzId(rs.getLong("fz_id"));
		fahrzeuggerat.setStatus(rs.getString("status"));
		fahrzeuggerat.setTyp(rs.getString("typ"));
		fahrzeuggerat.setEinbaudatum(rs.getTimestamp("einbaudatum"));
		fahrzeuggerat.setAusbaudatum(rs.getTimestamp("ausbaudatum"));
		return fahrzeuggerat;
	}

	public Fahrzeuggerat findById(int fzgId) {
		L.info("findById({})", fzgId);
		Fahrzeuggerat fahrzeuggerat = null;
		try {
			PreparedStatement ps =
					getConnection().prepareStatement("SELECT * FROM fahrzeuggerat WHERE fzg_id = ?");
			ps.setInt(1, fzgId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					fahrzeuggerat = rsToFahrzeuggerat(rs);
				}
			}
		} catch (SQLException e) {
			L.error("Fehler beim Auslesen des Fahrzeuggerats mit ID {}", fzgId);
			L.error("", e);
			throw new DataException(e);
		}
		return fahrzeuggerat;
	}

	public Fahrzeuggerat findByFzId(long fzId) {
		L.info("findByFzId({})", fzId);
		Fahrzeuggerat fahrzeuggerat = null;
		try {
			PreparedStatement ps =
					getConnection().prepareStatement("SELECT * FROM fahrzeuggerat WHERE fz_id = ?");
			ps.setLong(1, fzId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					fahrzeuggerat = rsToFahrzeuggerat(rs);
				}
			}
		} catch (SQLException e) {
			L.error("Fehler beim Auslesen des Fahrzeuggerats mit FZ_ID {}", fzId, e);
			throw new DataException(e);
		}
		return fahrzeuggerat;
	}
}
