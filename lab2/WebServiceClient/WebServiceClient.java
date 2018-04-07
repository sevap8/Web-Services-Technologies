
package com.mycompany.lab2;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {
    
    public static void getStatus(PersonWebService personWebService) {
        System.out.println("Persons Status");
        List<Person> persons = personWebService.getAllPersons();
        for (Person person : persons) {
        System.out.println(person.toString());
        }
        System.out.println("Total persons: " + persons.size());
        System.out.println();
    }
    
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/PersonService?wsdl");
        PersonService personService = new PersonService(url);
        PersonWebService PersonWebService =
    personService.getPersonWebServicePort();
        
        System.out.println("Simple hard code client for service");
        getStatus(personWebService);

        System.out.println("Query: createPerson?name=Иванов&surname=Иван&" +
        "position=Менеджер&age=25&salary=1000");
            int creatingPersonId = personWebService.createPerson("Иванов", "Иван", 1881, "Менеджер", 25, 1000);
            
        System.out.println("Inserting id: " + creatingPersonId);
        System.out.println();
        
        getStatus(personWebService);
        
        System.out.println("Query: createPerson?name=person&surname=surname&"+
                "position=Менеджер&age=25&salary=1000");
        creatingPersonId = personWebService.createPerson("person", "surname", "Менеджер", 25, 1000);
        
        System.out.println("Inserting id: " + creatingPersonId);
        System.out.println();
        
        getStatus(personWebService);
        
        System.out.println("Query: updatePerson?id=11&name=My own
    person&surname= Иванов&position=Менеджер);
        MyRequest myRequest = new MyRequest();
        myRequest.setName("Иван");
        myRequest.setSurname("Иванов");
        myRequest.setPosition("Менеджер");
        creatingPersonId = personWebService.updatePerson(11, myRequest);
        
        System.out.println("Updating status: " + creatingPersonId);
        System.out.println();
        
        getStatus(personWebService);
        
        System.out.println("Query: updatePerson?id=22&name=My own
    person&surname= Иванов&position=Менеджер);
        creatingPersonId = personWebService.updatePerson(22, myRequest);
        
        System.out.println("Updating status: " + creatingPersonId);
        System.out.println();
        
        getStatus(personWebService);
        
        System.out.println("Query: deletePerson?id=11");
        creatingPersonId = personWebService.deletePerson(11);
        
        System.out.println("Delete status: " + creatingPersonId);
        System.out.println();
        
        getStatus(personWebService);
    }
}
