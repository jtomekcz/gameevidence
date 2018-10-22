package com.tomek.gamevidence.dao;

import com.tomek.gamevidence.domain.Game;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class GameDaoImpl extends AbstractDaoImpl implements GameDao {

  @Override
  public List<Game> getAllGames() {
    StringBuilder sb = new StringBuilder(" FROM ");
    sb.append(Game.class.getName());
    sb.append(" WHERE validity.valid = :valid ");
    Query query = entityManager.createQuery(sb.toString());
    query.setParameter("valid", Boolean.TRUE);
    return query.getResultList();
  }
}
