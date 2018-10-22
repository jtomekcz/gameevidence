package com.tomek.gamevidence.dao;

public interface AbstractDao {
  <T> void setDomainObject(T entity);

  <C> C findByAlias(String alias, Class<C> entityClass);

}
