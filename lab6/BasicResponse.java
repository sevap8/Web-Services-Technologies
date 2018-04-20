
package com.mycompany.lab6;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement

public class BasicResponse {

    public String internalStatus;
    public String message;
    public BasicResponse() {}
    public BasicResponse(String message){
        this.internalStatus = "Error";
        this.message = message;
    }
}