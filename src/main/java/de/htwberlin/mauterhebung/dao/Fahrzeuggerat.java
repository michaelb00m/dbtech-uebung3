package de.htwberlin.mauterhebung.dao;

import java.sql.Timestamp;

public class Fahrzeuggerat {
  private long fzgId;
  private long fzId;
  private String status;
  private String typ;
  private Timestamp einbaudatum;
  private Timestamp ausbaudatum;

  public Fahrzeuggerat() {}

  public Fahrzeuggerat(int fzgId, long fzId, String status, String typ, Timestamp einbaudatum,
      Timestamp ausbaudatum) {
    this.fzgId = fzgId;
    this.fzId = fzId;
    this.status = status;
    this.typ = typ;
    this.einbaudatum = einbaudatum;
    this.ausbaudatum = ausbaudatum;
  }

  public long getFzgId() {
    return fzgId;
  }

  public void setFzgId(long fzgId) {
    this.fzgId = fzgId;
  }

  public long getFzId() {
    return fzId;
  }

  public void setFzId(long fzId) {
    this.fzId = fzId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTyp() {
    return typ;
  }

  public void setTyp(String typ) {
    this.typ = typ;
  }

  public Timestamp getEinbaudatum() {
    return einbaudatum;
  }

  public void setEinbaudatum(Timestamp einbaudatum) {
    this.einbaudatum = einbaudatum;
  }

  public Timestamp getAusbaudatum() {
    return ausbaudatum;
  }

  public void setAusbaudatum(Timestamp ausbaudatum) {
    this.ausbaudatum = ausbaudatum;
  }
}
