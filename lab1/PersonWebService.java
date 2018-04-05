package com.maxart.service.src.mycompany.lab1;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

@WebService(serviceName = "PersonService")
public class PersonWebService {

    @WebMethod(operationName = "getAllPersons")
    public List<Person> getAllPersons() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return dao.getAllPersons();
    }
      @WebMethod(operationName = "findPersons")
public List<Persons> findPersons(@WebParam(name = "q") MyRequest
request) {
PostgreSQLDAO dao = new PostgreSQLDAO();
return dao.findPersons(request);
    }
}
