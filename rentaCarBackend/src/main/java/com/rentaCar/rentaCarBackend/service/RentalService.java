package com.rentaCar.rentaCarBackend.service;

import  com.rentaCar.rentaCarBackend.JPARepo.CarRepository;
import  com.rentaCar.rentaCarBackend.JPARepo.ClientRepository;
import  com.rentaCar.rentaCarBackend.JPARepo.RentalRepository;
import  com.rentaCar.rentaCarBackend.JPARepo.UserRepository;
import com.rentaCar.rentaCarBackend.dto.ClientDTO;
import  com.rentaCar.rentaCarBackend.dto.RentalDTO;
import  com.rentaCar.rentaCarBackend.mapper.ClientMapper;
import  com.rentaCar.rentaCarBackend.mapper.RentalMapper;
import  com.rentaCar.rentaCarBackend.model.Rental;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RentalService {

        private final RentalRepository rentalRepository;
        private final RentalMapper rentalMapper;
        private final CarRepository carRepository;
        private final ClientRepository clientRepository;
        private final UserRepository userRepository;
        private final ClientMapper clientMapper; // Potreban za getClientsRentals

        public RentalService(RentalRepository rentalRepository, RentalMapper rentalMapper, CarRepository carRepository,
                             ClientRepository clientRepository, UserRepository userRepository, ClientMapper clientMapper) {
                this.rentalRepository = rentalRepository;
                this.rentalMapper = rentalMapper;
                this.carRepository = carRepository;
                this.clientRepository = clientRepository;
                this.userRepository = userRepository;
                this.clientMapper = clientMapper;
        }

        public List<RentalDTO> getAll() {
                List<Rental> rentals = rentalRepository.findAll();
                return rentals.stream()
                        .map(rentalMapper::toDomainDTO) // Značajno pojednostavljeno
                        .collect(Collectors.toList());
        }

        public List<RentalDTO> getClientsRentals(ClientDTO clientDTO) {
                // Pretpostavka je da u RentalRepository postoji metoda findByClient
                List<Rental> rentals = rentalRepository.findByClient(clientMapper.toDomainEntity(clientDTO));
                return rentals.stream()
                        .map(rentalMapper::toDomainDTO) // Značajno pojednostavljeno
                        .collect(Collectors.toList());
        }

        public List<RentalDTO> getRentalStatus(int status) {
                List<Rental> rentals = rentalRepository.findByStatus(status);
                return rentals.stream()
                        .map(rentalMapper::toDomainDTO) // Značajno pojednostavljeno
                        .collect(Collectors.toList());
        }

        public String addRental(RentalDTO rentalDTO) {
                try {
                        Rental rental = new Rental();
                        rental.setStartDate(rentalDTO.getStartDate());
                        rental.setEndDate(rentalDTO.getEndDate());
                        rental.setNotes(rentalDTO.getNotes());
                        rental.setStatus(rentalDTO.getStatus());

                        // Povezivanje postojećih entiteta iz baze
                        clientRepository.findById(rentalDTO.getClient().getJmbg()).ifPresent(rental::setClient);
                        carRepository.findById(rentalDTO.getCar().getId()).ifPresent(rental::setCar);
                        if (rentalDTO.getUser() != null) {
                                userRepository.findById(rentalDTO.getUser().getId()).ifPresent(rental::setUser);
                        }

                        rentalRepository.save(rental);
                        return "Created the rental successfully!";
                } catch (Exception ex) {
                        return "Cannot create this rental!";
                }
        }

        public String deleteRental(int rentalId) {
                try {
                        rentalRepository.deleteById(rentalId);
                        return "Deleted the rental successfully!";
                } catch (Exception ex) {
                        return "Cannot delete this rental!";
                }
        }

        public RentalDTO getRental(int rentalId) {
                Rental rental = rentalRepository.findById(rentalId).orElse(null);
                return rentalMapper.toDomainDTO(rental); // Značajno pojednostavljeno
        }

        public String updateRental(RentalDTO rentalDTO) {
                try {
                        // Logika je ista kao za dodavanje, jer save() radi i update ako ID postoji
                        addRental(rentalDTO);
                        return "Updated the rental successfully!";
                } catch (Exception ex) {
                        return "Cannot update this rental!";
                }
        }
}