package de.htwberlin.mauterhebung.dao;

import java.sql.Date;

public class Mauterhebung {
  private int mautId;
  private int abschnittId;
  private long fzgId;
  private int kategorieId;
  private Date befahrungsdatum;
  private float kosten;

  public Mauterhebung(int abschnittId, long fzgId, int kategorieId, Date befahrungsdatum,
      float kosten) {
    this.abschnittId = abschnittId;
    this.fzgId = fzgId;
    this.kategorieId = kategorieId;
    this.befahrungsdatum = befahrungsdatum;
    this.kosten = kosten;
  }

  public int getMautId() {
    return mautId;
  }

  public void setMautId(int mautId) {
    this.mautId = mautId;
  }

  public int getAbschnittId() {
    return abschnittId;
  }

  public void setAbschnittId(int abschnittId) {
    this.abschnittId = abschnittId;
  }

  public long getFzgId() {
    return fzgId;
  }

  public void setFzgId(long fzgId) {
    this.fzgId = fzgId;
  }

  public int getKategorieId() {
    return kategorieId;
  }

  public void setKategorieId(int kategorieId) {
    this.kategorieId = kategorieId;
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

  @Override
  public String toString() {
    return "Mauterhebung {mautId=" + mautId + ", abschnittId=" + abschnittId + ", fzgId=" + fzgId
        + ", kategorieId=" + kategorieId + ", befahrungsdatum=" + befahrungsdatum + ", kosten="
        + kosten + "}";
  }

}
