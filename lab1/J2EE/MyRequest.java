
package com.maxart.service.src.mycompany.lab1.J2EE;


import com.maxart.service.src.mycompany.lab1.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "MyRequest", namespace="http://standalone.maxart.com")
    public class MyRequest implements Serializable
    {
    @XmlElement(name = "id", required = false)
    private int id;
    @XmlElement(name = "name", required = false)
    protected String name;
    @XmlElement(name = "surname", required = false)
    private String surname;
    @XmlElement(name = "position", required = false)
    private String position;
    @XmlElement(name = "age", required = false)
    private int age;
    @XmlElement(name = "salary", required = false)
    private int salary;
    public int getId() {
    return id;
    }
    public void setId(int id) {
    this.id = id;
    }
    public String getName() {
    return name;
    }
    public void setName(String name) {
    this.name = name;
    }
    public String getSurname() {
    return surname;
    }
    public void setSurname(String surname) {
    this.surname = surname;
    }
    public String getPosition() {
    return position;
    }
    public void setPosition(String position) {
    this.position = position;
    }
    public int getAge() {
    return age;
    }
    public void setAge(int age) {
    this.age = age;
    }
    public float getSalary() {
    return salary;
    }
    public void setSalary(int salary) {
    this.salary = salary;
    }
}