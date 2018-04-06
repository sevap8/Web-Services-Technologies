/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        myRequest.seturname("Петер");
        persons = personWebService.findPersons(myRequest);
        for (Person person : persons) {
        System.out.println(person.toString());
        }
        System.out.println("Total persons: " + persons.size());
        System.out.println();
        System.out.println("Query: findPersons?name=Петер
        Винчи&year=1495");
        myRequest.setYear(1495);
        pictures = pictureWebService.findPictures(myRequest);
        for (Picture picture : pictures) {
        System.out.println(picture.toString());
        }
        System.out.println("Total pictures: " + pictures.size());
        System.out.println();
        System.out.println("Query: findPictures?id=7");
        myRequest.setId(7);myRequest.setYear(0);
        myRequest.setAuthor("");
        pictures = pictureWebService.findPictures(myRequest);
        for (Picture picture : pictures) {
        System.out.println(picture.toString());
        }
        System.out.println("Total pictures: " + pictures.size());
        System.out.println();
    }
}