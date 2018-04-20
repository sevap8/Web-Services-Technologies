
package com.mycompany.lab4.J2EE;


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
  request) throws IllegalQException {
        if (request == null || request.isNull()) {
    
            PersonServiceFault fault =

        PersonServiceFault.defaultInstance();
            throw new IllegalQException("Parameter q cannot be null or
            empty", fault);
            }
        
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.findPersons(request);
        }
    
        @WebMethod(operationName = "createPerson")
        public int createPerson(@WebParam(name = "name") String name,
                                @WebParam(name = "surname") String surname,
                                @WebParam(name = "position") String position,
                                @WebParam(name = "age") int age,
                                @WebParam(name = "salary") int 
        InsertingException,
                InvalidCreatingParametersException {
            
            PersonServiceFault fault = ServiceFault.defaultInstance();
            fault.setMessage("Invalid creating parameter");
            
            if (name == null || name.trim().isEmpty()) {
            fault.setMessage("Parameter name cannot be null or empty");
            throw new InvalidCreatingParametersException("Invalid creating
            parameter", fault);
            }
            if (surname == null || surname.trim().isEmpty()) {
            fault.setMessage("Parameter surname cannot be null or empty");
            throw new InvalidCreatingParametersException("Invalid creating
            parameter", fault);
            }
            if (position <= 0) {
            fault.setMessage("Parameter position cannot be null or empty and less
            or equal to 0");
            throw new InvalidCreatingParametersException("Invalid creating
            parameter", fault);
            }
            if (position == null || position.trim().isEmpty()) {
            fault.setMessage("Parameter position cannot be null or empty");
            throw new InvalidCreatingParametersException("Invalid creating
            parameter", fault);
            }
            if (age <= 0) {
            fault.setMessage("Parameter age cannot be null or empty and
            less or equal to 0");
            throw new InvalidCreatingParametersException("Invalid creating
            parameter", fault);
            }
            if (salary <= 0) {
            fault.setMessage("Parameter salary cannot be null or empty and
            less or equal to 0");
            throw new InvalidCreatingParametersException("Invalid creating
            parameter", fault);
            }
            PostgreSQLDAO dao = new PostgreSQLDAO();return dao.createPerson(name, surname, position, age, salary);
            }
        @WebMethod(operationName = "updatePerson")
        public int updatePerson(@WebParam(name = "id") int id,
        
                @WebParam(name = "q") MyRequest request) throws
        IllegalQException, IllegalIdException, InvalidEntityException {
        if (id == 0) {
        PersonServiceFault fault =
        PersoneServiceFault.defaultInstance();
        fault.setMessage("Parameter id cannot be null or empty");
        throw new IllegalIdException("Parameter id cannot be null or
        empty", fault);
        }
        if (request == null || request.isNull()) {
        PersonServiceFault fault =
        PersonServiceFault.defaultInstance();
        throw new IllegalQException("Parameter q cannot be null or
        empty", fault);
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.updatePerson(id, request);
        }
        
    @WebMethod(operationName = "deletePerson")
    public int deletePerson(@WebParam(name = "id") int id) throws
    InvalidEntityException, IllegalIdException {
        if (id == 0) {
            PersonServiceFault fault =
    PersonServiceFault.defaultInstance();
        fault.setMessage("Parameter id cannot be null or empty");
        throw new IllegalIdException("Parameter id cannot be null or
        empty", fault);
    }
        
    PostgreSQLDAO dao = new PostgreSQLDAO();
    return dao.deletePerson(id);
    }
}
