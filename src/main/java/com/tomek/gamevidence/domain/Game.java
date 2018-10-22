package com.tomek.gamevidence.domain;

import javax.persistence.*;

@Entity
@Table(name = "GAME")
public class Game implements DomainObject {

  @Id
  @Column(name = "id_game")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idPlayer;

  @Column(name = "alias")
  private String alias;

  @OneToOne
  @JoinColumn(name = "id_winner_team")
  private Team winnerTeam;

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

  public Team getWinnerTeam() {
    return winnerTeam;
  }

  public void setWinnerTeam(Team winnerTeam) {
    this.winnerTeam = winnerTeam;
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
