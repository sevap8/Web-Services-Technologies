
package com.mycompany.lab3;

import javax.xml.ws.WebFault;
@WebFault(faultBean = "com.maxart.service.PersonServiceFault")
public class IllegalQException extends Exception {
    private static final long serialVersionUID = -6647544772732631047L;
    private final PersonServiceFault fault;
    
    public IllegalQException(String message, PersonServiceFault fault) {
        super(message);
        this.fault = fault;
    }
    
    public IllegalQException(String message, PersonServiceFault fault,
Throwable cause) {
    super(message, cause);
    this.fault = fault;
    }
    
    public PersonServiceFault getFaultInfo() {
    return fault;
    }
}
