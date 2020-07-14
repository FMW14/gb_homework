package com.vtb.javacourses.lesson12;

import com.vtb.javacourses.lesson12.exceptions.DbTableNotFoundException;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
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
        parseMyClass();
    }

    private void parseMyClass(){
        if (!myClass.isAnnotationPresent(DbTable.class)){
            throw new DbTableNotFoundException(myClass.getName() + " class has not annotation @DbTable");
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

    private PreparedStatement prepareInsert(T object) throws SQLException {
        //---CHECK FIELD---
        StringBuilder query = new StringBuilder();
        StringBuilder queryArguments = new StringBuilder();
//        List<Object> values = new ArrayList<>();
        query.append("INSERT INTO students (");

        for (Field field : object.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(DbColumn.class)){
                query.append(field.getName());
                query.append(", ");
                queryArguments.append("?, ");
            }
        }
        query.deleteCharAt(query.length()-2);
        queryArguments.deleteCharAt(queryArguments.length()-2);
        query.append(") VALUES (");

//        query.append("?, ".repeat(object.getClass().getDeclaredFields().length));
//        query.deleteCharAt(query.length()-2);
        query.append(queryArguments);
        query.append(");");
//        query.append(") VALUES (?, ?");

//        return DbConnector.getPreparedStatement("INSERT INTO students (name, score) VALUES (?, ?);");
        return DbConnector.getPreparedStatement(query.toString());
    }


    @Override
    public void save(T object) throws SQLException, IllegalAccessException {
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder queryValues = new StringBuilder("VALUES (");

        for (Field field: myClass.getDeclaredFields()){
            if (field.isAnnotationPresent(DbColumn.class)){
                query.append(field.getName());
                query.append(", ");

                if (field.getType() == String.class){
                    queryValues.append("'");
                    queryValues.append(field.get(object));
                    queryValues.append("'");
                } else {
                    queryValues.append(field.get(object));
                }
                queryValues.append(", ");
            }
        }
        query.deleteCharAt(query.length()-2);
        query.append(") ");
        queryValues.deleteCharAt(queryValues.length()-2);
        queryValues.append(");");
        query.append(queryValues);

        DbConnector.getStatement().executeUpdate(query.toString());
    }

    //TODO дефолтные реализации
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
