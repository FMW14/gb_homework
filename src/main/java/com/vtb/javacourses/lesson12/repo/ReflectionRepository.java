package com.vtb.javacourses.lesson12.repo;

import java.sql.SQLException;
import java.util.List;

public abstract class ReflectionRepository<T>{

    public abstract void save(T object) throws SQLException, IllegalAccessException;

    public abstract T findById(Long id) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract void deleteById(Long id) throws SQLException;

    public abstract void deleteAll() throws SQLException;

}
