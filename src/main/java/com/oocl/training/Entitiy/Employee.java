package com.oocl.training.Entitiy;

public class Employee {
    private int id;
    private String name;
    private int age;
    private String gender;
    private double salary;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Employee(int id, String name, int age, String gender, double salary, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.active = active;
    }

    public Employee(String name, int age, String gender, double salary) {
        this.id = 1;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.active = true;
    }

    public Employee() {

    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
