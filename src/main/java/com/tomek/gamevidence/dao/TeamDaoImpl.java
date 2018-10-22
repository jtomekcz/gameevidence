package com.tomek.gamevidence.dao;

import com.tomek.gamevidence.domain.Player;
import com.tomek.gamevidence.domain.Team;
import com.tomek.gamevidence.service.TeamService;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TeamDaoImpl extends AbstractDaoImpl implements TeamDao {

  @Override
  public List<Team> getAllTeams() {
    StringBuilder sb = new StringBuilder(" FROM ");
    sb.append(Team.class.getName());
    sb.append(" WHERE validity.valid = :valid ");
    Query query = entityManager.createQuery(sb.toString());
    query.setParameter("valid", Boolean.TRUE);
    return query.getResultList();
  }
}
