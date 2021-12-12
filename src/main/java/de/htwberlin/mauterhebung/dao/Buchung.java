package de.htwberlin.mauterhebung.dao;

import java.sql.Timestamp;

public class Buchung {
  private int buchungId;
  private int bId;
  private int abschnittsId;
  private int kategorieId;
  private String kennzeichen;
  private Timestamp buchungsdatum;
  private Timestamp befahrungsdatum;
  private float kosten;

  public Buchung() {}

  public Buchung(int buchungId, int bId, int abschnittsId, int kategorieId, String kennzeichen,
      Timestamp buchungsdatum, Timestamp befahrungsdatum, float kosten) {
    this.buchungId = buchungId;
    this.bId = bId;
    this.abschnittsId = abschnittsId;
    this.kategorieId = kategorieId;
    this.kennzeichen = kennzeichen;
    this.buchungsdatum = buchungsdatum;
    this.befahrungsdatum = befahrungsdatum;
    this.kosten = kosten;
  }

  public long getBuchungId() {
    return buchungId;
  }

  public void setBuchungId(int buchungId) {
    this.buchungId = buchungId;
  }

  public long getBId() {
    return bId;
  }

  public void setBId(int bId) {
    this.bId = bId;
  }

  public long getAbschnittsId() {
    return abschnittsId;
  }

  public void setAbschnittsId(int abschnittsId) {
    this.abschnittsId = abschnittsId;
  }

  public int getKategorieId() {
    return kategorieId;
  }

  public void setKategorieId(int kategorieId) {
    this.kategorieId = kategorieId;
  }

  public String getKennzeichen() {
    return kennzeichen;
  }

  public void setKennzeichen(String kennzeichen) {
    this.kennzeichen = kennzeichen;
  }

  public Timestamp getBuchungsdatum() {
    return buchungsdatum;
  }

  public void setBuchungsdatum(Timestamp buchungsdatum) {
    this.buchungsdatum = buchungsdatum;
  }

  public Timestamp getBefahrungsdatum() {
    return befahrungsdatum;
  }

  public void setBefahrungsdatum(Timestamp befahrungsdatum) {
    this.befahrungsdatum = befahrungsdatum;
  }

  public float getKosten() {
    return kosten;
  }

  public void setKosten(float kosten) {
    this.kosten = kosten;
  }

}
