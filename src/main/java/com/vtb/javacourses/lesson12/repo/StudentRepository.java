package com.vtb.javacourses.lesson12.repo;

import com.vtb.javacourses.lesson12.DbConnector;
import com.vtb.javacourses.lesson12.annotations.DbColumn;
import com.vtb.javacourses.lesson12.annotations.DbId;
import com.vtb.javacourses.lesson12.annotations.DbTable;
import com.vtb.javacourses.lesson12.etities.Student;
import com.vtb.javacourses.lesson12.exceptions.DbColumnNotFoundException;
import com.vtb.javacourses.lesson12.exceptions.DbIdNotFoundException;
import com.vtb.javacourses.lesson12.exceptions.DbTableNotFoundException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository<T> extends ReflectionRepository<T> {
    private Class<T> myClass;
    private String tableName;
    private List<EntityField> entityFields = new ArrayList<>();

    class EntityField {
        String fieldName;   //maybe redundant
        Integer fieldIndex; //maybe redundant
        Field field;
//        Type type;
        Method getMethod;
        Method setMethod;

        public EntityField() {
        }

        public EntityField(Field field) {
            this.field = field;
            detectMethods(field);
        }

        public EntityField(Field field, Integer index) {
            this.field = field;
            this.fieldIndex = index;
            this.fieldName = field.getName(); //maybe redundant
            detectMethods(field);
        }
        private void detectMethods(Field curField){
            for (Method method: myClass.getDeclaredMethods()){
                if(method.getName().toLowerCase().contains(curField.getName().toLowerCase())
                        && method.getName().toLowerCase().contains("get")){
                    getMethod = method;
                }
                if(method.getName().toLowerCase().contains(curField.getName().toLowerCase())
                        && method.getName().toLowerCase().contains("set")){
                    setMethod = method;
                }
            }
        }
    }

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
        List<Field> fields = new ArrayList<>();


        int i = 1;
        for (Field field : myClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(DbId.class)) {
                fieldId = field;
            }

            if (field.isAnnotationPresent(DbColumn.class)) {
                fields.add(field);
                entityFields.add(new EntityField(field, i));
            }
            i++;
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

        for (Field field : myClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(DbColumn.class)) {
                field.setAccessible(true);
                query.append(field.getName());
                query.append(", ");

                if (field.getType() == String.class) {
                    queryValues.append("'");
                    queryValues.append(field.get(object));
                    queryValues.append("'");
                } else {
                    queryValues.append(field.get(object));
                }
                queryValues.append(", ");
            }
        }
        query.deleteCharAt(query.length() - 2);
        query.append(") ");
        queryValues.deleteCharAt(queryValues.length() - 2);
        queryValues.append(");");
        query.append(queryValues);

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
//            student.getClass().getDeclaredMethod("setName", String.class).invoke(student, "test"); //работает

            student.setId(rs.getLong("id"));
            for (EntityField entityField : entityFields){
                entityField.field.setAccessible(true);
                entityField.field.set(student, rs.getObject(entityField.fieldName));

//                student.getClass().getDeclaredField(entityField.fieldName).setAccessible(true);

                //worked!! but shitty
//                Field tempField = myClass.getDeclaredField(entityField.fieldName);
//                tempField.setAccessible(true);
//                System.out.println(tempField.getName() + "  " + tempField.canAccess(student));
//                tempField.set(student, rs.getObject(entityField.fieldName));


//                myClass.getDeclaredField(entityField.fieldName).setAccessible(true);
//                System.out.println(myClass.getDeclaredField(entityField.fieldName).isAccessible());
//                student.getClass().getDeclaredField(entityField.fieldName).set(student, rs.getObject(entityField.fieldName));

//                Method m = entityField.setMethod;
//                student.getClass().getMethod(entityField.setMethod.getName()); //не работает
//                student.getClass().getMethod(m.getName()).invoke(rs.getObject(entityField.fieldIndex)); //не работает


//                student.getClass().getDeclaredMethod(m.getName()).invoke(student,""); !!!!
//                entityField.setMethod.invoke(rs.getObject(entityField.fieldIndex));
//                rs.getObject(1, myClass);
            }

//            student.setId(rs.getLong("id"));
//            student.setName(rs.getString("name"));
//            student.s

//            student.setId(rs.getLong(1));
//            student.setName(rs.getString(2));
//            student.setScore(rs.getInt(3));

            return (T) student;
        } catch (IllegalAccessException e ) {
            e.printStackTrace();
            throw new RuntimeException(); // TODO: 15.07.2020 create new exception with setter not found or cant access
        }
    }

    @Override
    public List<T> findAll() throws SQLException {
        List<T> students = new ArrayList<>();
        try (ResultSet rs = DbConnector.getStatement().executeQuery("SELECT * FROM " + tableName + ";")) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong(1));
                student.setName(rs.getString(2));
                student.setScore(rs.getInt(3));
                students.add((T) student);
            }
        }

        return students;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        DbConnector.getStatement().executeUpdate("DELETE FROM " + tableName + " WHERE id = " + id + ";");
    }

    @Override
    public void deleteAll() throws SQLException {
        DbConnector.getStatement().executeUpdate("DROP TABLE IF EXISTS "+ tableName);
    }
}
