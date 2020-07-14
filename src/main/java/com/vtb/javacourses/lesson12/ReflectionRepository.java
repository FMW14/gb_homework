package com.vtb.javacourses.lesson12;

import java.sql.SQLException;
import java.util.List;

public abstract class ReflectionRepository<T>{

    public abstract void save(T object) throws SQLException, IllegalAccessException;

    public abstract T findById();

    public abstract List<T> findAll();

    public abstract void deleteById();

    public abstract void deleteAll();

}
