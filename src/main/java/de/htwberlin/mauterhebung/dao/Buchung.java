package de.htwberlin.mauterhebung.dao;

import java.sql.Date;

public class Buchung {
  private int buchungId;
  private int bId;
  private int abschnittsId;
  private int kategorieId;
  private String kennzeichen;
  private Date buchungsdatum;
  private Date befahrungsdatum;
  private float kosten;

  public Buchung() {}

  public Buchung(int buchungId, int bId, int abschnittsId, int kategorieId, String kennzeichen,
      Date buchungsdatum, Date befahrungsdatum, float kosten) {
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

  public Date getBuchungsdatum() {
    return buchungsdatum;
  }

  public void setBuchungsdatum(Date buchungsdatum) {
    this.buchungsdatum = buchungsdatum;
  }

  public Date getBefahrungsdatum() {
    return befahrungsdatum;
  }

  public void setBefahrungsdatum(Date befahrungsdatum) {
    this.befahrungsdatum = befahrungsdatum;
  }

  public float getKosten() {
    return kosten;
  }

  public void setKosten(float kosten) {
    this.kosten = kosten;
  }

}
