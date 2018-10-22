package com.tomek.gamevidence.dao;

public interface AbstractDao {
  <T> void insert(T entity);

  <C> C findByAlias(String alias, Class<C> entityClass);

}
