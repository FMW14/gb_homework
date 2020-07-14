package com.vtb.javacourses.lesson12;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository<T> extends ReflectionRepository<T> {
    private Class<T> myClass;
    private String tableName;
    private List<Field> fields = new ArrayList<>();

    public StudentRepository(Class<T> myClass){
        this.myClass = myClass;
        try {
            DbConnector.connect();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Cant connect to database");
        }
    }

    private void parseClass(){
        if (!myClass.isAnnotationPresent(DbTable.class)){
            throw new RuntimeException(myClass.getName() + " class has not annotation @DbTable");
        } else {
            tableName = myClass.getAnnotation(DbTable.class).name();
        }
        
        Field fieldId = null;


        for (Field field: myClass.getDeclaredFields()){
            if (field.isAnnotationPresent(DbId.class)){
                fieldId = field;
            }

            if (field.isAnnotationPresent(DbColumn.class)){
                fields.add(field);
            }
        }


        //---CHECK FIELDS---
        if (fieldId == null){
            throw new RuntimeException(myClass.getName() + " class has not field with annotation @DbId");
        }

        if (fields.isEmpty()){
            throw new RuntimeException(myClass.getName() + " class has not fields with annotation @DbColumn");
        }

    }


    @Override
    public void save(T object) {
//        try {
//            DbConnector.getStatement().executeUpdate("INSERT INTO students (name, score) VALUES ('Bob4', 60);");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


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
