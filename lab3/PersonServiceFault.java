
package com.mycompany.lab3;

public class PersonServiceFault {
    private static final String DEFAULT_MESSAGE = "Parameter q cannot be null
or empty";
    private String message;

    public String getMessage() {
    return message;
    }
    public void setMessage(String message) {
    this.message = message;
    }
    public static PersonServiceFault defaultInstance() {
        PersonServiceFault fault = new PersonServiceFault();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }
}
