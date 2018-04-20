
package com.mycompany.lab5;
    
import lab5.base..ConnectionLab;
import lab5.base.PostgreSQLDAO;
import lab5.base.PostgreSQLDAO.StatusOperation.ResultStatusOperation;
import lab5.base.Person;
import lab5.base.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonWebService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons(){
        try(PostgreSQLDAO dal = new PostgreSQLDAO(ConnectionLab.getConnection())) {
            List<Person> persons = dal.getPersons();
            return persons;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Person> getPersonByPerson(Person person){
        try(PostgreSQLDAO dal = new PostgreSQLDAO(ConnectionLab.getConnection())){
            return dal.getPersonsByPerson(person);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("id") int id){
        PostgreSQLDAO dal = new PostgreSQLDAO(ConnectionLab.getConnection());
        return dal.getPerson(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultStatusOperation createPerson(Person person){
        PostgreSQLDAO dal = new PostgreSQLDAO(ConnectionLab.getConnection());
        return dal.insertPerson(person);
    }

    @PUT
    public ResultStatusOperation updatePerson(Person person){
        PostgreSQLDAO dal = new PostgreSQLDAO(ConnectionLab.getConnection());
        return dal.updatePerson(person);
    }

    @DELETE
    @Path("/{id}")
    public ResultStatusOperation deletePerson(@PathParam("id") int id){
        PostgreSQLDAO dal = new PostgreSQLDAO(ConnectionLab.getConnection());
        return dal.deletePerson(id);
    }
}