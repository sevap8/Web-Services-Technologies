package com.maxart.service.src.mycompany.lab1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
@WebService(serviceName = "PersonService")
public class PersonWebService {
@WebMethod(operationName = "getAllPersons")
public List<Person> getAllPersons() {
PostgreSQLDAO dao = new PostgreSQLDAO();
return dao.getAllPersons();
}
@WebMethod(operationName = "findPictures")
public List<Person> findPictures(@WebParam(name = "q") MyRequest
request) {
PostgreSQLDAO dao = new PostgreSQLDAO();
return dao.findPictures(request);
}
}