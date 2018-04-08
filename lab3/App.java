
package com.mycompany.lab3;

import javax.xml.ws.Endpoint;

public class App {
    

    public static void main(String[] args) {
    //disable stacktraces in soap-message
   System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStack Trace", "false");

   String url = "http://0.0.0.0:8080/PersonService";

    Endpoint.publish(url, new PersonWebService());
    }
}


