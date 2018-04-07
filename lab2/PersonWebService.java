package com.mycompany.lab2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(serviceName = "PersonService")
public class PersonWebService {
    @WebMethod(operationName = "getAllPersons")
    public List<Person> getAllPersons() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
    return dao.getAllPersons();
}
    @WebMethod(operationName = "findPersons")
    public List<Person> findPersons(@WebParam(name = "q") MyRequest
        request) {
    PostgreSQLDAO dao = new PostgreSQLDAO();
    return dao.findPersons(request);
    }
    @WebMethod(operationName = "createPerson")
    public int createPerson(@WebParam(name = "name") String name,
    @WebParam(name = "surname") String surname,
    @WebParam(name = "position") String position,
    @WebParam(name = "age") int age,
    @WebParam(name = "salary") int salary,
    {
    PostgreSQLDAO dao = new PostgreSQLDAO();
    try {
        return dao.createPercon(name, surname, position, age, salary);
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return -1;
        }
    @WebMethod(operationName = "updatePercon")
    public int updatePercon(@WebParam(name = "id") int id,
    @WebParam(name = "q") MyRequest request) {
    PostgreSQLDAO dao = new PostgreSQLDAO();
    try {
    return dao.updatePercon(id, request);
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return -1;
    }
    @WebMethod(operationName = "deletePercon")
    public int deletePercon(@WebParam(name = "id") int id) {
    PostgreSQLDAO dao = new PostgreSQLDAO();
    try {
        return dao.deletePercon(id);
        } catch (SQLException e) {e.printStackTrace();
        }
        return -1;
    }
}
