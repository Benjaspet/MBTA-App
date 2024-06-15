package dev.benjaspet.model;

import java.math.BigDecimal;

public abstract class AHeavyRailStop implements IHeavyRailStop {

  private final BigDecimal latitude;
  private final BigDecimal longitude;
  private final String name;
  private final String municipality;
  private final String id;
  private final String address;

  public AHeavyRailStop(BigDecimal latitude, BigDecimal longitude, String name, String municipality, String id, String address) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.name = name;
    this.municipality = municipality;
    this.id = id;
    this.address = address;
  }

  public BigDecimal getLatitude() {
    return latitude;
  }

  public BigDecimal getLongitude() {
    return longitude;
  }

  public String getFriendlyName() {
    return name;
  }

  public String getMunicipality() {
    return municipality;
  }

  public String getId() {
    return id;
  }

  public String getAddress() {
    return address;
  }
}
