package com.rentaCar.rentaCarBackend.dto;

import java.io.Serializable;

public class CarModelDTO implements DomainDTO, Serializable {

    private int id;
    private String name;

    public CarModelDTO() {
    }

    public CarModelDTO(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "CarModelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}