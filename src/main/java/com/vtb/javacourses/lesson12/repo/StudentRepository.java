package com.vtb.javacourses.lesson12.repo;

import com.vtb.javacourses.lesson12.DbConnector;
import com.vtb.javacourses.lesson12.annotations.DbColumn;
import com.vtb.javacourses.lesson12.annotations.DbId;
import com.vtb.javacourses.lesson12.annotations.DbTable;
import com.vtb.javacourses.lesson12.entities.Student;
import com.vtb.javacourses.lesson12.exceptions.DbColumnNotFoundException;
import com.vtb.javacourses.lesson12.exceptions.DbIdNotFoundException;
import com.vtb.javacourses.lesson12.exceptions.DbTableNotFoundException;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository<T> extends ReflectionRepository<T> {
    private Class<T> myClass;
    private String tableName;
    private List<Field> fields = new ArrayList<>();

    public StudentRepository(Class<T> myClass) {
        this.myClass = myClass;
        try {
            DbConnector.connect();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Cant connect to database");
        }
        parseMyClass();
    }

    private void parseMyClass() {
        if (!myClass.isAnnotationPresent(DbTable.class)) {
            throw new DbTableNotFoundException(myClass.getName() + " class has not annotation @DbTable");
        } else {
            tableName = myClass.getAnnotation(DbTable.class).name();
        }

        Field fieldId = null;

        for (Field field : myClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(DbId.class)) {
                fieldId = field;
            }

            if (field.isAnnotationPresent(DbColumn.class)) {
                fields.add(field);
            }
        }

        //---CHECK FIELDS---
        if (fieldId == null) {
            throw new DbIdNotFoundException(myClass.getName() + " class has not field with annotation @DbId");
        }

        if (fields.isEmpty()) {
            throw new DbColumnNotFoundException(myClass.getName() + " class has not fields with annotation @DbColumn");
        }
    }

//    private PreparedStatement prepareInsertOld(T object) throws SQLException {
//        StringBuilder query = new StringBuilder();
//        StringBuilder queryArguments = new StringBuilder();
//        query.append("INSERT INTO students (");
//
//        for (Field field : object.getClass().getDeclaredFields()) {
//            if (field.isAnnotationPresent(DbColumn.class)) {
//                query.append(field.getName());
//                query.append(", ");
//                queryArguments.append("?, ");
//            }
//        }
//        query.deleteCharAt(query.length() - 2);
//        queryArguments.deleteCharAt(queryArguments.length() - 2);
//        query.append(") VALUES (");
//        query.append(queryArguments);
//        query.append(");");
//
//        return DbConnector.getPreparedStatement(query.toString());
//    }

//    public PreparedStatement prepareInsert(T object) throws SQLException {
//        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
//        StringBuilder queryValues = new StringBuilder("VALUES (");
//
//        for (Field field : myClass.getDeclaredFields()) {
//            if (field.isAnnotationPresent(DbColumn.class)) {
//                query.append(field.getName());
//                query.append(", ");
//                queryValues.append("?, ");
//            }
//        }
//        query.deleteCharAt(query.length() - 2);
//        query.append(") ");
//        queryValues.deleteCharAt(queryValues.length() - 2);
//        queryValues.append(");");
//        query.append(queryValues);
//        return DbConnector.getPreparedStatement(query.toString());
//    }

    @Override
    public void save(T object) throws SQLException, IllegalAccessException {
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder queryValues = new StringBuilder("VALUES (");

        for (Field curField : fields) {
            curField.setAccessible(true);
            query.append(curField.getName());
            query.append(", ");

            if (curField.getType() == String.class) {
                queryValues.append("'");
                queryValues.append(curField.get(object));
                queryValues.append("'");
            } else {
                queryValues.append(curField.get(object));
            }
            queryValues.append(", ");

        }
        query.deleteCharAt(query.length() - 2);
        query.append(") ");
        queryValues.deleteCharAt(queryValues.length() - 2);
        queryValues.append(");");
        query.append(queryValues);

        System.out.println(query.toString());

        DbConnector.getStatement().executeUpdate(query.toString());

//        Map<Integer, Field> objectValues = new TreeMap<>();
//        int i = 0;
//
//        for (Field field : myClass.getDeclaredFields()) {
//            if (field.isAnnotationPresent(DbColumn.class)) {
//                objectValues.put(i, field);
//            }
//        }
//        PreparedStatement preparedStatement = prepinss(object);
//        for (Map.Entry<Integer, Field> entry : objectValues.entrySet()){ // FIXME: 14.07.2020
//            preparedStatement.setString(entry.getKey(), (String) entry.getValue().get(object));
//        }
//
//        preparedStatement.setString(1, object.getClass().);
//
//        preparedStatement.executeUpdate();
    }

    @Override
    public T findById(Long id) throws SQLException {
        try (ResultSet rs = DbConnector.getStatement().executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id + ";")) {
            Student student = new Student();
//            Object student = new T();

            student.setId(rs.getLong("id"));
            for (Field curField : fields) {
                curField.setAccessible(true);
                curField.set(student, rs.getObject(curField.getName()));
            }

            return (T) student;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<T> findAll() throws SQLException {
        List<T> students = new ArrayList<>();
        try (ResultSet rs = DbConnector.getStatement().executeQuery("SELECT * FROM " + tableName + ";")) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong("id"));
                for (Field curField : fields) {
                    curField.setAccessible(true);
                    curField.set(student, rs.getObject(curField.getName()));
                }
                students.add((T) student);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
//            throw new RuntimeException();
        }

        return students;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        DbConnector.getStatement().executeUpdate("DELETE FROM " + tableName + " WHERE id = " + id + ";");
    }

    @Override
    public void deleteAll() throws SQLException {
        DbConnector.getStatement().executeUpdate("DROP TABLE IF EXISTS " + tableName);
    }
}