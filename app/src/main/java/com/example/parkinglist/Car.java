package com.example.parkinglist;

public class Car {
    private int id;
    private String name;
    private String matricule;
    private String time;
    private boolean payee;

    public Car(int id, String name, String matricule, String time, boolean payee) {
        this.id = id;
        this.name = name;
        this.matricule = matricule;
        this.time = time;
        this.payee = payee;
    }

    public Car(String name, String matricule, String time, boolean payee) {
        this.name = name;
        this.matricule = matricule;
        this.time = time;
        this.payee = payee;
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

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isPayee() {
        return payee;
    }

    public void setPayee(boolean payee) {
        this.payee = payee;
    }
}
