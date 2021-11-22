package org.matsim.project.oop;

public class Person {

    private int age;
    private String lastName;
    private double height;

    Person(String str1, int age1, double height1) {

    }

    void printYourself() {
        System.out.println("lastName= " + lastName + " ;age= " + age + " ;height= " + height);
    }

    void setAge(int var) {
        this.age = var;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    int getAge() {
        return this.age;
    }
}
