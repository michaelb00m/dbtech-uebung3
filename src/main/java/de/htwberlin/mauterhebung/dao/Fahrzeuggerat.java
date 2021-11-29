package de.htwberlin.mauterhebung.dao;

import java.sql.Date;

public class Fahrzeuggerat {
  private long fzgId;
  private long fzId;
  private String status;
  private String typ;
  private Date einbaudatum;
  private Date ausbaudatum;

  public Fahrzeuggerat() {

  }

  public Fahrzeuggerat(int fzgId, long fzId, String status, String typ, Date einbaudatum,
      Date ausbaudatum) {
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

  public Date getEinbaudatum() {
    return einbaudatum;
  }

  public void setEinbaudatum(Date einbaudatum) {
    this.einbaudatum = einbaudatum;
  }

  public Date getAusbaudatum() {
    return ausbaudatum;
  }

  public void setAusbaudatum(Date ausbaudatum) {
    this.ausbaudatum = ausbaudatum;
  }
}
