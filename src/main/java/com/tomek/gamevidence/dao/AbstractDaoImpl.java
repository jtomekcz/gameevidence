package com.tomek.gamevidence.dao;

import com.tomek.gamevidence.domain.DomainObject;
import com.tomek.gamevidence.domain.Validity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractDaoImpl implements AbstractDao {

  private final Logger LOGGER = LoggerFactory.getLogger(AbstractDaoImpl.class);

  @PersistenceContext
  protected EntityManager entityManager;

  @Override
  @Transactional
  public <T> void setDomainObject(T entity) {
    if (entity instanceof DomainObject) {
      Validity validity = new Validity(true);
      ((DomainObject) entity).setValidityAsValid(validity);
    }
    entityManager.persist(entity);
  }

  @Override
  public <C> C findByAlias(String alias, Class<C> entityClass) {
    try {
      StringBuilder sb = new StringBuilder(" FROM ");
      sb.append(entityClass.getName());
      sb.append(" WHERE alias = :alias");
      sb.append(" AND validity.valid = :valid ");
      Query query = entityManager.createQuery(sb.toString());
      query.setParameter("alias", alias);
      query.setParameter("valid", Boolean.TRUE);
      return (C) query.getSingleResult();
    } catch (NoResultException e) {
      LOGGER.warn("No player found for alias - {}", alias);
      return null;
    }
  }
}
