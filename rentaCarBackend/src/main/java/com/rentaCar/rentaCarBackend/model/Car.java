package com.rentaCar.rentaCarBackend.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Car implements Serializable, DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private int id;

    @Column(name = "license_plate", nullable = false, updatable = true)
    private String licensePlate;


    @Column(name = "year", nullable = false, updatable = true)
    private int year;

    @ManyToOne
    @JoinColumn(name = "car_model_id", nullable = false, updatable = true)
    private CarModel carModel;

    @Column(name = "notes", nullable = true, updatable = true)
    private String notes;


    @Column(name = "transmission", nullable = false, updatable = true)
    private String transmission;

    @Column(name = "status", nullable = false, updatable = true)
    private int status;

    @Column(name = "picture", nullable = true, updatable = true)
    private String picture;

    public Car() {
    }

    public Car(int id, String licensePlate, int year, CarModel carModel, String notes, String transmission, int status, String picture) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.year = year;
        this.carModel = carModel;
        this.notes = notes;
        this.transmission = transmission;
        this.status = status;
        this.picture = picture;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", year=" + year +
                ", carModel=" + (carModel != null ? carModel.getName() : null) +
                ", transmission='" + transmission + '\'' +
                ", status=" + status +
                '}';
    }
}