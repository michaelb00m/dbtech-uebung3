package de.htwberlin.mauterhebung.dao;

import de.htwberlin.mauterhebung.utils.Achszahl;

public class Mautkategorie {
  private int kategorieId;
  private int ssklId;
  private char katBezeichnung;
  private Achszahl achszahl;
  private float mautsatzJeKm;

  public Mautkategorie() {}

  public Mautkategorie(int kategorieId, int ssklId, char katBezeichung, Achszahl achszahl, float mautsatzJeKm) {
    this.kategorieId = kategorieId;
    this.ssklId = ssklId;
    this.katBezeichnung = katBezeichung;
    this.achszahl = achszahl;
    this.mautsatzJeKm = mautsatzJeKm;
  }

  public int getKategorieId() {
    return kategorieId;
  }

  public void setKategorieId(int kategorieId) {
    this.kategorieId = kategorieId;
  }

  public int getSsklId() {
    return ssklId;
  }

  public void setSsklId(int ssklId) {
    this.ssklId = ssklId;
  }

  public char getKatBezeichnung() {
    return katBezeichnung;
  }

  public void setKatBezeichnung(char katBezeichung) {
    this.katBezeichnung = katBezeichung;
  }

  public Achszahl getAchszahl() {
    return achszahl;
  }

  public void setAchszahl(Achszahl achszahl) {
    this.achszahl = achszahl;
  }

  public float getMautsatzJeKm() {
    return mautsatzJeKm;
  }

  public void setMautsatzJeKm(float mautsatzJeKm) {
    this.mautsatzJeKm = mautsatzJeKm;
  }
}
