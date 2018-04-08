
package com.mycompany.lab3.WebServiceClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {

    private static void getStatus(PersonWebService personWebService) {

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
        PersonWebService personWebService =

                personService.getPersonWebServicePort();
        
            System.out.println("Simple hard code client for service");
            getStatus(personWebService);
            
            System.out.println("Query: createPerson?name=Иван&surname=Иванов");

        int creatingPersonId = 0;

        try {
        creatingPersonId = personWebService.createPerson(
        "Иван",
        "Иваноы",
        0,
        "",
        0,
        0);
        } catch (InsertingException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " +
    e.getFaultInfo().getMessage());

        } catch (InvalidCreatingParametersException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println("FaultInfo: " +
        e.getFaultInfo().getMessage());

        }
            System.out.println("Response: " + creatingPersonId);
            System.out.println();
            
            System.out.println("Query: createPerson?name=Иван&surname=Иванов&" + "position=Менеджер&age=25 salary=1000");
            
            int id = 0;
            try {
                id = personWebService.createPerson(
                "Иван",
                "Иванов",
                "Менеджер",
                25,
                1000
                );
                } catch (InsertingException e) {
                    
                System.out.println("Message: " + e.getMessage());
                System.out.println("FaultInfo: " + e.getFaultInfo().getMessage());
                
                } catch (InvalidCreatingParametersException e) {
                    
                System.out.println("Message: " + e.getMessage());
                System.out.println("FaultInfo: " +
                e.getFaultInfo().getMessage());
                }
            
                System.out.println("Response: " + id);
                System.out.println();
                
                getStatus(personWebService);
                
                System.out.println("Query: updatePerson?id=10");

                MyRequest myRequest = new MyRequest();

                try {

                    creatingPersonId = personWebService.updatePerson(11,myRequest);
                
                } catch (IllegalIdException e) {
                    System.out.println("Message: " + e.getMessage());
                    System.out.println("FaultInfo: " +
                    e.getFaultInfo().getMessage());
                } catch (IllegalQException e) {
                    System.out.println("Message: " + e.getMessage());
                    System.out.println("FaultInfo: " +
                    e.getFaultInfo().getMessage());
                } catch (InvalidEntityException e) {
                    System.out.println("Message: " + e.getMessage());
                    System.out.println("FaultInfo: " +
                    e.getFaultInfo().getMessage());
                }
                    System.out.println("Response: " + creatingPersonId);
                    System.out.println();
                    
                    System.out.println("Query: updatePerson?id=111&name=My own
                person&surname=Иванов&position=Менеджер");myRequest.init();
                    myRequest.setName("My own person");
                    myRequest.setSurname("Иванов");
                    myRequest.setPosition("Менеджер");
                try {
                    creatingPersonId = personWebService.updatePerson(111,
                    myRequest);
                } catch (IllegalIdException e) {
                    System.out.println("Message: " + e.getMessage());
                    System.out.println("FaultInfo: " +
                    e.getFaultInfo().getMessage());
                } catch (IllegalQException e) {
                    System.out.println("Message: " + e.getMessage());
                    System.out.println("FaultInfo: " +
                    e.getFaultInfo().getMessage());
                } catch (InvalidEntityException e) {
                    System.out.println("Message: " + e.getMessage());
                    System.out.println("FaultInfo: " +
                    e.getFaultInfo().getMessage());
                    }
                    System.out.println("Response: " + creatingPersonId);
                    System.out.println();
                    System.out.println("Query: deletePerson?id=111");
                    try {
                    creatingPersonId = personWebService.deletePerson(111);
                } catch (IllegalIdException e) {
                    System.out.println("Message: " + e.getMessage());
                    System.out.println("FaultInfo: " +
                    e.getFaultInfo().getMessage());
                } catch (InvalidEntityException e) {
                    System.out.println("Message: " + e.getMessage());
                    System.out.println("FaultInfo: " +
                    e.getFaultInfo().getMessage());
                    }
                    System.out.println("Response: " + creatingPersonId);
                    System.out.println();
                    System.out.println("Query: deletePerson?id=" + id);
                    try {
                    creatingPersonId = personWebService.deletePerson(id);
                } catch (IllegalIdException e) {
                    System.out.println("Message: " + e.getMessage());
                    System.out.println("FaultInfo: " +
                    e.getFaultInfo().getMessage());
                } catch (InvalidEntityException e) {
                    System.out.println("Message: " + e.getMessage());
                    System.out.println("FaultInfo: " +
                    e.getFaultInfo().getMessage());
                    }
                    System.out.println("Response: " + creatingPersonId);
                    System.out.println();
                    getStatus(personWebService);
    }
}
