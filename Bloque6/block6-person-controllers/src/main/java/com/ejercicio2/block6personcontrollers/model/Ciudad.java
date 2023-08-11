package com.ejercicio2.block6personcontrollers.model;

public class Ciudad {

    private String name;
    private int numPeople;

    public Ciudad(String name, int numPeople) {
        this.name = name;
        this.numPeople = numPeople;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "name='" + name + '\'' +
                ", numPeople=" + numPeople +
                '}';
    }
}
