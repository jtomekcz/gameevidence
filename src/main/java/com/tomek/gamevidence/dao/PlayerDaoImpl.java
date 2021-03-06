package com.tomek.gamevidence.dao;

import com.tomek.gamevidence.domain.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PlayerDaoImpl extends AbstractDaoImpl implements PlayerDao {

  @Override
  public List<Player> getAllPlayers() {
    StringBuilder sb = new StringBuilder(" FROM ");
    sb.append(Player.class.getName());
    sb.append(" WHERE validity.valid = :valid ");
    sb.append(" ORDER BY winnersCount DESC");
    Query query = entityManager.createQuery(sb.toString());
    query.setParameter("valid", Boolean.TRUE);
    return query.getResultList();
  }

}
