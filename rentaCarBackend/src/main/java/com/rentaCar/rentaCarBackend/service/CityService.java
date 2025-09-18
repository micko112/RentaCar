package com.rentaCar.rentaCarBackend.service;

import com.example.demo.JPARepo.CityRepository;
import com.example.demo.dto.CityDTO;
import com.example.demo.mapper.CityMapper;
import com.example.demo.model.City;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityService {

    private final CityRepository cityRepo;
    private final CityMapper cityMapper;

    public CityService(CityRepository cityRepo, CityMapper cityMapper) {
        this.cityRepo = cityRepo;
        this.cityMapper = cityMapper;
    }

    public List<CityDTO> getAll() {
        List<City> cities = cityRepo.findAll();
        return cities.stream()
                .map(cityMapper::toDomainDTO)
                .collect(Collectors.toList());
    }
}