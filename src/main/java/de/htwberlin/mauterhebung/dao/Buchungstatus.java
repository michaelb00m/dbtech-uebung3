package de.htwberlin.mauterhebung.dao;

public class Buchungstatus {
  private int bId;
  private String status;

  public Buchungstatus(int bId, String status) {
    this.bId = bId;
    this.status = status;
  }

  public Buchungstatus() {}

  public int getBId() {
    return bId;
  }

  public void setBId(int bId) {
    this.bId = bId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
