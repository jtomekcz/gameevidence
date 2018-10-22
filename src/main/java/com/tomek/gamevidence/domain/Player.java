package com.tomek.gamevidence.domain;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "PLAYER")
public class Player implements DomainObject {

  @Id
  @Column(name = "id_player")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idPlayer;

  @Column(name = "alias")
  private String alias;

  @Column(name = "winners_count")
  private int winnersCount;

  @Column(name = "losts_count")
  private int lostsCount;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "id_team")
  private Team team;

  @Embedded
  private Validity validity;

  public int getIdPlayer() {
    return idPlayer;
  }

  public void setIdPlayer(int idPlayer) {
    this.idPlayer = idPlayer;
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

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public Validity getValidity() {
    return validity;
  }

  public void setValidity(Validity validity) {
    this.validity = validity;
  }

  @Override
  public void setValidityAsValid(Validity validity) {
    this.setValidity(validity);
  }
}
