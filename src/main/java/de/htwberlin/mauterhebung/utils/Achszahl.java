package de.htwberlin.mauterhebung.utils;

public class Achszahl {
  private boolean greater;
  private int value;

  public Achszahl(String achszahl) {
    String[] split = achszahl.split(" ");
    this.greater = split[0].length() > 1;
    this.value = Integer.parseInt(split[1]);
  }

  public boolean isValid(int value) {
    return greater ? value >= this.value : value == this.value;
  }
}
