package com.brian.gestionproyectosmoviles.model;

public class Persona {
    String name,code,percentage;
    public Persona(){}

    public Persona(String name, String code, String percentage){
        this.name = name;
        this.code = code;
        this.percentage = percentage;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
