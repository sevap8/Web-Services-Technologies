package com.maxart.service.src.mycompany.lab1;

public class Person {
    private String name;
    private String surname;
    private String position;
    private int age;
    private int salary;
public Person() {
}
public Person(String name, String surname, String position, int age, int salary) {
    this.name = name;
    this.surname = surname;
    this.position = position; 
    this.age = age;
    this.salary = salary;
    }
public String getName() {
    return name;
    }
public String getSurname() {
    return surname;
    }
public String getPosition(){
    return position;
    }
public int getAge() {
    return age;
    }
public int getSalary(){
    return salary;
    }

public void setName(String name) {
    this.name = name;
    }
public void setSurname(String surname) {
    this.surname = surname;
    }
public void setPosition(String surname){
    this.position = position;
    }
public void setAge(int age) {
    this.age = age;
    }
public void setSalary(int age) {
    this.salary = salary;
    }
@Override
public String toString() {
return "Person{" + "name=" + name + ", surname=" + surname + ", position=" + position +", age=" + age +", salary=" + salary + '}';
    }
}