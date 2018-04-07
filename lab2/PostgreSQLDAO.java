
package com.mycompany.lab2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;import java.util.logging.Logger;
import static javax.persistence.criteria.Predicate.BooleanOperator.OR;

public class PostgreSQLDAO {

    private Connection connection;

    PostgreSQLDAO(Connection connection) {

        this.connection = connection;
}

    PostgreSQLDAO() {

        this.connection = ConnectionUtil.getConnection();
}

    public List<Person> getAllPersons() {
        
        return executeQuery("SELECT * FROM persons");
}

    public List<Person> findPersons(MyRequest request) {

        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM persons WHERE " + "(id = " + request.getId() + " OR " + request.getId() + " = 0) AND " +
"(name = '" + request.getName() + "' OR '" + request.getName() + "' = '' OR '" + request.getName() + "' = '?') AND " +
"(surname = '" + request.getSurname() + "' OR '" + request.getSurname() + "' = '' OR '" + request.getSurname() + "' = '?') AND " + 
                "(position = " + request.getPosition() + " OR " + request.getPosition() + " = 0) AND " +
"(age = '" + request.getAge() + "' OR '" + request.getAge() + "' = '' OR '" + request.getAge() + "' = '?') AND
" + "(salary = " + request.getSalary() + " OR " + request.getSalary() + " = 0);

return executeQuery(sql);
}
    
    public int createPerson(String name, String surname, String position, int
age, int salary) throws SQLException {
        String sql = "INSERT INTO persons (name, surname, position, age,
salary) VALUES(?, ?, ?, ?, ?, ?)";
    PreparedStatement preparedStatement =
        this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setInt(3, position);
        preparedStatement.setString(4, age);
        preparedStatement.setFloat(5, salary);
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
        return -1;
        }
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
        {
            if (generatedKeys.next()) {
                return (int) generatedKeys.getLong(1);
        }
            else {
        return -1;
        }
    }
}
    public int updatePerson(int id, MyRequest request) throws SQLException {
        String sql = "UPDATE persons SET" + createUpdateQuery(request) + "
        WHERE id=?";
        PreparedStatement preparedStatement =
        this.connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
    int affectedRows = preparedStatement.executeUpdate();
    return affectedRows == 0 ? -1 : 1;
    }
    public int deletePerson(int id) throws SQLException {
        String sql = "DELETE FROM persons WHERE id = ?";
        PreparedStatement preparedStatement =
    this.connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int affectedRows = preparedStatement.executeUpdate();
        return affectedRows == 0 ? -1 : 1;
    }
    private List<Person> executeQuery(String sql) {
        List<Person> persons = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String position = rs.getString("position");
                int age = rs.getInt("age");
                int salary = rs.getInt("salary");

            Person person = new Person(id, name, surname, position, age,
            salary);
            persons.add(person);
        }
    } catch (SQLException ex) {
        Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE,
        null, ex);
    }
    return persons;
}
    private String createUpdateQuery(MyRequest request) {
        StringBuilder stringBuilderField = new StringBuilder("(");
        StringBuilder stringBuilderValues = new StringBuilder("(");
        if (request.getId() > 0) {
        stringBuilderField.append("id,");
        stringBuilderValues.append(request.getId()).append(",");
        }
        if (!request.getName().equals("?") && !request.getName().equals(""))
        {
        stringBuilderField.append("name,");
        stringBuilderValues.append("'").append(request.getName()).append("',");
        }
        if (!request.getAuthor().equals("?") &&
        !request.getAuthor().equals("")) {
        stringBuilderField.append("surname,");
        stringBuilderValues.append("'").append(request.getAuthor()).append("',");
        }
        if (request.getYear() > 0) {
        stringBuilderField.append("position,");
        stringBuilderValues.append(request.getYear()).append(",");
        }
        if (!request.getMaterial().equals("?") &&
        !request.getMaterial().equals("")) {
        stringBuilderField.append("age,");
        stringBuilderValues.append("'").append(request.getMaterial()).append("',");
        }
        if (request.getHeight() > 0) {
        stringBuilderField.append("salary,");
        stringBuilderValues.append(request.getHeight()).append(",");
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
