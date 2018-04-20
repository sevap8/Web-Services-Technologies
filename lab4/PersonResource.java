
package com.mycompany.lab4;

 
import com.maxart.service.exceptions.*;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/persons")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {
    @GET

    public List<Person> find(@Context UriInfo info) {
        
        String id = info.getQueryParameters().getFirst("id");
        String name = info.getQueryParameters().getFirst("name");
        String surname = info.getQueryParameters().getFirst("surname");
        String position = info.getQueryParameters().getFirst("position");
        String age = info.getQueryParameters().getFirst("age");
        String salary = info.getQueryParameters().getFirst("salary");
        return new PostgreSQLDAO().findPersons(id, name, surname, position,
                age, salary);
    }
    @GET
    @Path("/{id}")
    public List<Person> getOne(@PathParam("id") int id) throws
            IllegalIdException {
        if (id <= 0) {throw IllegalIdException.DEFAULT_INSTANCE;
        }
        return new PostgreSQLDAO().findOne(id);
    }
    @POST @Consumes("application/json")
    public String create(Person person) throws
            InvalidCreatingParametersException, InsertingException {
        if (person.getName() == null || person.getName().trim().isEmpty())
        {
            throw new InvalidCreatingParametersException("Invalid creating
            parameter: name");
        }
        if (person.getAuthor() == null ||
                person.getSurname().trim().isEmpty()) {
            throw new InvalidCreatingParametersException("Invalid creating
            parameter: surname");
        }
        if (person.getPosition() <= 0) {
            throw new InvalidCreatingParametersException("Invalid creating
            parameter: position");
        }
        if (person.getAge() == null ||
                person.getMaterial().trim().isEmpty()) {
            throw new InvalidCreatingParametersException("Invalid creating
            parameter: age");
        }
        if (person.getSalary() <= 0) {
            throw new InvalidCreatingParametersException("Invalid creating
            parameter: salary");
        }

        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.createPerson(person) + "}";
    }
    @PUT @Consumes("application/json")
    @Path("/{id}")
    public String update(@PathParam("id") int id, Person person) throws
            IllegalIdException, InvalidEntityException,
            InvalidUpdatingParametersException {
        if (id <= 0) {
            throw IllegalIdException.DEFAULT_INSTANCE;
        }
        if ((person.getName() == null || person.getName().trim().isEmpty())
                &&
                (person.getSurname() == null ||
                person.getSurname().trim().isEmpty()) &&
                (person.getYear() <= 0) &&
                (person.getPosition() == null ||
                person.getPosition().trim().isEmpty()) &&
                (person.getAge() <= 0) &&
                (person.getSalary() <= 0)) {throw InvalidUpdatingParametersException.DEFAULT_INSTANCE;
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.updatePerson(id, person) + "}";
    }
    @DELETE
    @Path("/{id}")
    public String delete(@PathParam("id") int id) throws IllegalIdException,
            InvalidEntityException {
        if (id <= 0) {
            throw IllegalIdException.DEFAULT_INSTANCE;
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        return "{\"result\":" + dao.deletePerson(id) + "}";
    }
}