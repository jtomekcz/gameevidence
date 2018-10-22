package com.tomek.gamevidence.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEAM")
public class Team implements DomainObject{

  @Id
  @Column(name = "id_team")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idTeam;

  @Column(name = "alias")
  private String alias;

  @Column(name = "winners_count")
  private int winnersCount;

  @Column(name = "losts_count")
  private int lostsCount;

  private int maxPlayersCount;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "team")
  private List<Player> players;

  @Embedded
  protected Validity validity;

  public int getIdTeam() {
    return idTeam;
  }

  public void setIdTeam(int idTeam) {
    this.idTeam = idTeam;
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

  public int getMaxPlayersCount() {
    return maxPlayersCount;
  }

  public void setMaxPlayersCount(int maxPlayersCount) {
    this.maxPlayersCount = maxPlayersCount;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
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
