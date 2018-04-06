package com.maxart.service.src.mycompany.lab1.J2EE;

import com.maxart.service.src.mycompany.lab1.*;
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
}