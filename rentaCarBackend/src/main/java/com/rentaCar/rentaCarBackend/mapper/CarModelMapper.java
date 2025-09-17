package com.rentaCar.rentaCarBackend.mapper;

import com.example.demo.dto.CarModelDTO;
import com.example.demo.model.CarModel;
import org.springframework.stereotype.Component;

@Component
// IZMENJENO: BaseMapper sada radi sa CarModelDTO
public class CarModelMapper implements BaseMapper<CarModelDTO, CarModel> {

    @Override
    public CarModelDTO toDomainDTO(CarModel domainEntity) {
        CarModelDTO carModelDTO = new CarModelDTO();
        carModelDTO.setId(domainEntity.getId());
        carModelDTO.setName(domainEntity.getName());
        return carModelDTO;
    }

    @Override
    public CarModel toDomainEntity(CarModelDTO domainDTO) {
        CarModel carModel = new CarModel();
        carModel.setId(domainDTO.getId());
        carModel.setName(domainDTO.getName());
        return carModel;
    }
}