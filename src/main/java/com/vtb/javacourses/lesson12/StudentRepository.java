package com.vtb.javacourses.lesson12;

import java.util.List;

public class StudentRepository<T> extends ReflectionRepository<T> {
    private Class<T> myClass;

    public StudentRepository(Class<T> myClass) {
        this.myClass = myClass;
    }


    @Override
    public void save(T object) {

    }

    @Override
    public T findById() {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public void deleteById() {

    }

    @Override
    public void deleteAll() {

    }
}
