package com.tomek.gamevidence.dao;

import com.tomek.gamevidence.domain.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PlayerDaoImpl implements PlayerDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Player> getAllPlayers() {
    StringBuilder sb = new StringBuilder(" FROM ");
    sb.append(Player.class.getName());
    sb.append(" WHERE valid = 'Y'");
    Query query = entityManager.createQuery(sb.toString());
    return query.getResultList();
  }

  @Override
  public Player insert(Player player) {
    entityManager.persist(player);
    return player;
  }

  @Override
  public Player findByAlias(String alias) {
    return null;
  }

  @Override
  public void deletePlayer(Player player) {

  }
}
