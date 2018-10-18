package com.tomek.gamevidence.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;

@Table
@Entity(name = "PLAYER")
public class Player {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "alias")
  private String alias;

  @Column(name = "winners_count")
  private int winnersCount;

  @Column(name = "losts_count")
  private int lostsCount;

  @Type(type = "yes_no")
  @Column(name = "valid")
  private Boolean valid;

  @Column(name = "valid_from")
  private Instant validFrom;

  @Column(name = "valid_to")
  private Instant validTo;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public int getWinnersCount() {
    return winnersCount;
  }

  public void setWinnersCount(int winnersCount) {
    this.winnersCount = winnersCount;
  }

  public int getLostsCount() {
    return lostsCount;
  }

  public void setLostsCount(int lostsCount) {
    this.lostsCount = lostsCount;
  }

  public Boolean getValid() {
    return valid;
  }

  public void setValid(Boolean valid) {
    this.valid = valid;
  }

  public Instant getValidFrom() {
    return validFrom;
  }

  public void setValidFrom(Instant validFrom) {
    this.validFrom = validFrom;
  }

  public Instant getValidTo() {
    return validTo;
  }

  public void setValidTo(Instant validTo) {
    this.validTo = validTo;
  }
}
