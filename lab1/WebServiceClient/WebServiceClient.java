package com.maxart.service.src.mycompany.lab1.J2EE.WebServiceClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
public class WebServiceClient {
    
public static void main(String[] args) throws MalformedURLException {
    
        URL url = new URL("http://localhost:8080/PersonService?wsdl");
        PersonService PersonService = new PersonService(url);
        PersonWebService PersonWebService =
        personService.getPersonWebServicePort();
        System.out.println("Simple hard code client for service");
        System.out.println("Query: getAllPerson");
        List<Person> persons = personWebService.getAllPersons();
        for (Person person : persons) {
        System.out.println(person.toString());
        }
        System.out.println("Total persons: " + Persons.size());
        System.out.println();
        System.out.println("Query: Persons?name=Петер");
        MyRequest myRequest = new MyRequest();
        myRequest.setSurname("Петер");
        persons = personWebService.findPersons(myRequest);
        for (Person person : persons) {
        System.out.println(person.toString());
        }
        System.out.println("Total persons: " + persons.size());
        System.out.println();
        System.out.println("Query: findPersons?name=Петер&position='Менеджер'");
        myRequest.setYear(1495);
        persons = personWebService.findpersons(myRequest);
        for (Person person : persons) {
        System.out.println(person.toString());
        }
        System.out.println("Total persons: " + persons.size());
        System.out.println();
        System.out.println("Query: findPersons?id=3");
        myRequest.setId(7);myRequest.setAge(0);
        myRequest.setSurname("");
        persons = personWebService.findPersons(myRequest);
        for (Persons person : persons) {
        System.out.println(person.toString());
        }
        System.out.println("Total persons: " + persons.size());
        System.out.println();
    }
}
