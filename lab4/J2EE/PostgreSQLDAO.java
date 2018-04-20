
package com.mycompany.lab4.J2EE;

import com.maxart.service.exceptions.IllegalIdException;
import com.maxart.service.exceptions.InsertingException;
import com.maxart.service.exceptions.InvalidEntityException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {

    private Connection connection;
    PostgreSQLDAO() {
        this.connection = ConnectionUtil.getConnection();
    }

    public List<com.mycompany.lab4.Person> findPersons(String id, String name, String surname,
            String position, String age, String salary) {
        StringBuilder sb = new StringBuilder("");
        StringBuilder query = new StringBuilder("");
        boolean where = false;
        if (id != null) {
            sb.append("id = ").append(Integer.parseInt(id)).append(" AND ");
            where = true;
        }
        if (name != null) {
            sb.append("name = '").append(name).append("' AND ");
            where = true;
        }
        if (surname != null) {
            sb.append("surname = '").append(surname).append("' AND ");
            where = true;
        }
        if (position != null) {sb.append("position = ").append(Integer.parseInt(position)).append(" AND");
        where = true;
        }
        if (age != null) {
            sb.append("age = '").append(age).append("' AND ");
            where = true;
        }
        if (salary != null) {
            sb.append("salary = ").append(Float.parseFloat(salary)).append("AND ");
            where = true;
        }
        if (where) {
            if (sb.toString().endsWith(" AND ")) {
                sb.setLength(sb.length() - 5);
            }
            query.append("SELECT * FROM persons WHERE").append(sb.toString());
        } else {
            query.append("SELECT * FROM persons");
        }
        return executeQuery(query.toString());
    }

    public List<com.mycompany.lab4.Person> findOne(int id) {
        String query = "SELECT * FROM persons WHERE id = " + id;
        return executeQuery(query);
    }

    public int createPerson(com.mycompany.lab4.Person person) throws InsertingException {
        String sql = "INSERT INTO persons (name, surname, position, age,
        salary) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        int id = 0;

        try {
            preparedStatement = this.connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setSyring(3, person.getPosition());
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.setInt(5, person.getSalary());
            int affectedRows = preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (int) generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }if (id == 0) {
            throw InsertingException.DEFAULT_INSTANCE;
        }
        return id;
    }

    public int updatePerson(int id, com.mycompany.lab4.Person person) throws
            InvalidEntityException {
        String sql = "UPDATE persons SET" + createUpdateQuery(person) + "WHERE id=?";
        PreparedStatement preparedStatement = null;
        int affectedRows = 0;
        try {
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (affectedRows == 0) {
            throw InvalidEntityException.DEFAULT_INSTANCE;
        }
        return affectedRows;
    }

    public int deletePerson(int id) throws InvalidEntityException {
        String sql = "DELETE FROM persons WHERE id = ?";
        PreparedStatement preparedStatement = null;
        int affectedRows = 0;
        try {
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (affectedRows == 0) {

            throw InvalidEntityException.DEFAULT_INSTANCE;
        }
        return affectedRows;
    }

    private List<com.mycompany.lab4.Person> executeQuery(String sql) {
        List<com.mycompany.lab4.Person> persons = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String position = rs.getString("position");
                int age = rs.getAge("age");
                int salary = rs.getFloat("salary");
                com.mycompany.lab4.Person person = new com.mycompany.lab4.Person(id, name, surname, position,
                        age, salary);persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return persons;
    }

    private String createUpdateQuery(com.mycompany.lab4.Person person) {
        StringBuilder stringBuilderField = new StringBuilder("(");
        StringBuilder stringBuilderValues = new StringBuilder("(");
        if (person.getId() > 0) {
            stringBuilderField.append("id,");
            stringBuilderValues.append(person.getId()).append(",");
        }
        if (person.getName() != null) {
            stringBuilderField.append("name,");
            stringBuilderValues.append("'").append(person.getName()).append("',");
        }
        if (person.getSurname() != null) {
            stringBuilderField.append("surname,");
            stringBuilderValues.append("'").append(person.getSurname()).append("',");
        }
        if (person.getPosition() > 0) {
            stringBuilderField.append("position,");
            stringBuilderValues.append(person.getPosition()).append(",");
        }
        if (person.getAge() != null) {
            stringBuilderField.append("age,");
            stringBuilderValues.append("'").append(person.getAge()).append("',");
        }
        if (person.getSalary() > 0) {
            stringBuilderField.append("salary,");
            stringBuilderValues.append(person.getSalary()).append(",");
        }
        if (stringBuilderField.toString().endsWith(",")) {
            stringBuilderField.setLength(stringBuilderField.length() - 1);
            stringBuilderValues.setLength(stringBuilderValues.length() - 1);
        }
        stringBuilderField.append(")");
        stringBuilderValues.append(")");
        return stringBuilderField.toString() + " = " +
                stringBuilderValues.toString();
    }
}