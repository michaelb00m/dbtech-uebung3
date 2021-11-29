package de.htwberlin.mauterhebung.dao;

import java.sql.Date;

public class Fahrzeug {
  private long fzId;
  private int ssklId;
  private int nutzerId;
  private String kennzeichen;
  private String fin;
  private int achsen;
  private double gewicht;
  private Date anmeldedatum;
  private Date abmeldedatum;
  private String zulassungsland;

  public Fahrzeug() {}

  public Fahrzeug(long fzId, int ssklId, int nutzerId, String kennzeichen, String find, int achsen,
      double gewicht, Date anmeldedatum, Date abmeldedatum, String zulassungsland) {
    this.fzId = fzId;
    this.ssklId = ssklId;
    this.nutzerId = nutzerId;
    this.kennzeichen = kennzeichen;
    this.fin = find;
    this.achsen = achsen;
    this.gewicht = gewicht;
    this.anmeldedatum = anmeldedatum;
    this.abmeldedatum = abmeldedatum;
    this.zulassungsland = zulassungsland;
  }

  public long getFzId() {
    return fzId;
  }

  public void setFzId(long fzId) {
    this.fzId = fzId;
  }

  public int getSsklId() {
    return ssklId;
  }

  public void setSsklId(int ssklId) {
    this.ssklId = ssklId;
  }

  public int getNutzerId() {
    return nutzerId;
  }

  public void setNutzerId(int nutzerId) {
    this.nutzerId = nutzerId;
  }

  public String getKennzeichen() {
    return kennzeichen;
  }

  public void setKennzeichen(String kennzeichen) {
    this.kennzeichen = kennzeichen;
  }

  public String getFin() {
    return fin;
  }

  public void setFin(String fin) {
    this.fin = fin;
  }

  public int getAchsen() {
    return achsen;
  }

  public void setAchsen(int achsen) {
    this.achsen = achsen;
  }

  public double getGewicht() {
    return gewicht;
  }

  public void setGewicht(double gewicht) {
    this.gewicht = gewicht;
  }

  public Date getAnmeldedatum() {
    return anmeldedatum;
  }

  public void setAnmeldedatum(Date anmeldedatum) {
    this.anmeldedatum = anmeldedatum;
  }

  public Date getAbmeldedatum() {
    return abmeldedatum;
  }

  public void setAbmeldedatum(Date abmeldedatum) {
    this.abmeldedatum = abmeldedatum;
  }

  public String getZulassungsland() {
    return zulassungsland;
  }

  public void setZulassungsland(String zulassungsland) {
    this.zulassungsland = zulassungsland;
  }

  @Override
  public String toString() {
    return "Fahrzeug{" + "fzId=" + fzId + ", ssklId=" + ssklId + ", nutzerId=" + nutzerId
        + ", kennzeichen=" + kennzeichen + ", fin=" + fin + ", achsen=" + achsen + ", gewicht="
        + gewicht + ", anmeldedatum=" + anmeldedatum + ", abmeldedatum=" + abmeldedatum
        + ", zulassungsland=" + zulassungsland + '}';
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + (int) (this.fzId ^ (this.fzId >>> 32));
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Fahrzeug other = (Fahrzeug) obj;
    if (this.fzId != other.fzId) {
      return false;
    }
    return true;
  }

}
