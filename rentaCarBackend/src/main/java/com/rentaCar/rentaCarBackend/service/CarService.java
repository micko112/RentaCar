package com.rentaCar.rentaCarBackend.service;

import com.rentaCar.rentaCarBackend.dto.CarDTO;
import com.rentaCar.rentaCarBackend.JPARepo.CarModelRepository;
import com.rentaCar.rentaCarBackend.JPARepo.CarRepository;
import com.rentaCar.rentaCarBackend.mapper.CarMapper;
import com.rentaCar.rentaCarBackend.model.Car;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final CarModelRepository carModelRepository;

    public CarService(CarRepository carRepository, CarMapper carMapper, CarModelRepository carModelRepository) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.carModelRepository = carModelRepository;
    }

    public List<CarDTO> getAll() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(carMapper::toDomainDTO) // Pojednostavljeno mapiranje
                .collect(Collectors.toList());
    }

    public List<CarDTO> getWithStatus(int status) {
        List<Car> cars = carRepository.findByStatus(status);
        return cars.stream()
                .map(carMapper::toDomainDTO) // Pojednostavljeno mapiranje
                .collect(Collectors.toList());
    }

    // Pretpostavka je da u CarRepository postoji metoda findByLicensePlateStartsWith
    public List<CarDTO> searchCars(String searchParam) {
        List<Car> cars = carRepository.findByLicensePlateStartsWith(searchParam);
        return cars.stream()
                .map(carMapper::toDomainDTO) // Pojednostavljeno mapiranje
                .collect(Collectors.toList());
    }

    public String addCar(CarDTO carDTO) {
        try {
            Car car = carMapper.toDomainEntity(carDTO);
            // Ručno postavljanje modela automobila iz baze
            carModelRepository.findById(carDTO.getCarModel().getId()).ifPresent(car::setCarModel);
            carRepository.save(car);
            return "Successfully created a car!";
        } catch (Exception ex) {
            return "Cannot create this car!";
        }
    }

    public String deleteCar(int carId) {
        try {
            carRepository.deleteById(carId);
            return "Successfully deleted the car!";
        } catch (Exception ex) {
            return "Cannot delete this car!";
        }
    }

    public CarDTO getCar(int carId) {
        Car carEntity = carRepository.findById(carId).orElse(null);
        return carMapper.toDomainDTO(carEntity); // Pojednostavljeno mapiranje
    }

    public String updateCar(CarDTO carDTO) {
        try {
            Car car = carMapper.toDomainEntity(carDTO);
            // Ručno postavljanje modela automobila iz baze
            carModelRepository.findById(carDTO.getCarModel().getId()).ifPresent(car::setCarModel);
            carRepository.save(car);
            return "Successfully updated the car!";
        } catch (Exception ex) {
            return "Cannot update this car!";
        }
    }
}