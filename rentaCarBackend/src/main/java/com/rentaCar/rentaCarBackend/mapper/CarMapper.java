package com.rentaCar.rentaCarBackend.mapper;

import com.rentaCar.rentaCarBackend.dto.CarDTO;
import com.rentaCar.rentaCarBackend.dto.CarModelDTO;
import com.rentaCar.rentaCarBackend.model.Car;
import com.rentaCar.rentaCarBackend.model.CarModel;
import org.springframework.stereotype.Component;

@Component
// IZMENJENO: BaseMapper sada radi sa CarDTO i Car
public class CarMapper implements BaseMapper<CarDTO, Car> {

    @Override
    public Car toDomainEntity(CarDTO carDTO) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setLicensePlate(carDTO.getLicensePlate());
        car.setYear(carDTO.getYear());
        if (carDTO.getCarModel() != null) {
            car.setCarModel(new CarModel(carDTO.getCarModel().getId(), carDTO.getCarModel().getName()));
        }
        car.setNotes(carDTO.getNotes());
        car.setTransmission(carDTO.getTransmission());
        car.setStatus(carDTO.getStatus());
        car.setPicture(carDTO.getPicture());
        return car;
    }

    public Car toDomainEntity(CarDTO carDTO, CarModel carModel) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setLicensePlate(carDTO.getLicensePlate());
        car.setYear(carDTO.getYear());
        car.setCarModel(carModel); // Koristimo prosleđeni CarModel
        car.setNotes(carDTO.getNotes());
        car.setTransmission(carDTO.getTransmission());
        car.setStatus(carDTO.getStatus());
        car.setPicture(carDTO.getPicture());
        return car;
    }

    @Override
    public CarDTO toDomainDTO(Car carEntity) {
        if (carEntity == null) {
            return null;
        }
        CarDTO carDTO = new CarDTO();
        carDTO.setId(carEntity.getId());
        carDTO.setLicensePlate(carEntity.getLicensePlate());
        carDTO.setYear(carEntity.getYear());
        if (carEntity.getCarModel() != null) {
            carDTO.setCarModel(new CarModelDTO(carEntity.getCarModel().getId(), carEntity.getCarModel().getName()));
        }
        carDTO.setNotes(carEntity.getNotes());
        carDTO.setTransmission(carEntity.getTransmission());
        carDTO.setStatus(carEntity.getStatus());
        carDTO.setPicture(carEntity.getPicture());
        return carDTO;
    }
}