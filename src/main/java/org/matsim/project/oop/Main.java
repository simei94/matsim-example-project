package org.matsim.project.oop;

public class Main {

    public static void main(String[] args) {
        double dd;
        dd = 20.;

        //Deklaration
        Person kainagel;
        // Objekttypen müssen immer instanziiert werden: mit new xx()
        //Instanziierung
        kainagel = new Person("Nagel", 23, 185);

        //oder in einem:
//        Person person2 = new Person();

//        person2.setAge(23);


        kainagel.setAge(20);
        kainagel.setLastName("Nagel");
        kainagel.setHeight(190);

        kainagel.printYourself();
    }
}
