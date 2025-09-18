package com.rentaCar.rentaCarBackend.dto;

import java.io.Serializable;

public class CarDTO implements DomainDTO, Serializable {

    private int id;
    private String licensePlate; // name -> licensePlate
    private int year;            // age -> year
    private CarModelDTO carModel;  // breed -> carModel
    private String notes;
    private String transmission; // gender -> transmission
    private int status;
    private String picture;

    public CarDTO() {
    }

    public CarDTO(int id, String licensePlate, int year, CarModelDTO carModel, String notes, String transmission, int status, String picture) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.year = year;
        this.carModel = carModel;
        this.notes = notes;
        this.transmission = transmission;
        this.status = status;
        this.picture = picture;
    }

    // --- Ažurirani Getteri i Setteri ---

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

    public CarModelDTO getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModelDTO carModel) {
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
        return "CarDTO{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", year=" + year +
                ", carModel=" + (carModel != null ? carModel.getName() : null) +
                '}';
    }
}