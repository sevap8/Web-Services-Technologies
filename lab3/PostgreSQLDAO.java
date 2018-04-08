
package com.mycompany.lab3;

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
age, int salary) throws InsertingException {
    String sql = "INSERT INTO persons (name, surname, position, age, salary) VALUES(?, ?, ?, ?, ?, ?)";
    PreparedStatement preparedStatement = null;
    int id = 0;
    try {
        preparedStatement = this.connection.prepareStatement(sql,
Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setInt(3, position);
        preparedStatement.setString(4, age);
        preparedStatement.setFloat(5, salary);

        int affectedRows = preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            id = (int) generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        if (id == 0) {
            PersonServiceFault fault =
PersonServiceFault.defaultInstance();
    fault.setMessage("Error During creation entity");
    throw new InsertingException("Error During creation entity",
fault);
    }
        
    return id;

        }
    
    public int updatePerson(int id, MyRequest request) throws
            InvalidEntityException {
            String sql = "UPDATE persons SET" + createUpdateQuery(request) + "
        WHERE id=?";"
                + "
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
                PersonServiceFault fault =
    PersonServiceFault.defaultInstance();
    
        fault.setMessage("Invalid entity");
        throw new InvalidEntityException("Invalid entity", fault);
        
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
        PersonServiceFault fault =
    PersonServiceFault.defaultInstance();
        fault.setMessage("Invalid entity");
        throw new InvalidEntityException("Invalid entity", fault);
    }
        return affectedRows;
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
                String position = rs.getInt("position");
                int age = rs.getString("age");
                int salary = rs.getFloat("salary");
                
                Person person = new Person(id, name, surname, position,
age, salary);
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
        if (!request.getSurname().equals("?") &&
        !request.getSurname().equals("")) {
        stringBuilderField.append("surname,");
        stringBuilderValues.append("'").append(request.getSurname()).append("',");
        }
        if (request.getPosition() > 0) {
        stringBuilderField.append("position,");
        stringBuilderValues.append(request.getPosition()).append(",");
        }
        if (!request.getAge().equals("?") &&
        !request.getAge().equals("")) {
        stringBuilderField.append("age,");
        stringBuilderValues.append("'").append(request.getAge()).append("',");
        }
        if (request.getSalary() > 0) {
        stringBuilderField.append("salary,");
        stringBuilderValues.append(request.getSalary()).append(",");
        }
        if (stringBuilderField.toString().endsWith(",")) {
        stringBuilderField.setLength(stringBuilderField.length() - 1);
        stringBuilderValues.setLength(stringBuilderValues.length() - 1);
        }
        stringBuilderField.append(")");
        StringBuilderValues.append(")");
    return stringBuilderField.toString() + " = " +
    stringBuilderValues.toString();
    }
}
