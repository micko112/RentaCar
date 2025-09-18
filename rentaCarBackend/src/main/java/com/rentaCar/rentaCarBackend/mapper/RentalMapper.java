package com.rentaCar.rentaCarBackend.mapper;

import com.rentaCar.rentaCarBackend.dto.RentalDTO;
import com.rentaCar.rentaCarBackend.model.Rental;
import org.springframework.stereotype.Component;

@Component
// IZMENJENO: BaseMapper sada radi sa RentalDTO
public class RentalMapper implements BaseMapper<RentalDTO, Rental> {

    // Potrebni su nam drugi mapperi za konverziju ugnježdenih objekata
    // U pravom Spring projektu bi se injektovali sa @Autowired
    private final CarMapper carMapper = new CarMapper();
    private final ClientMapper clientMapper = new ClientMapper();
    private final UserMapper userMapper = new UserMapper(); // Pretpostavka da postoji i UserMapper

    @Override
    public Rental toDomainEntity(RentalDTO rentalDTO) {
        Rental rental = new Rental();
        rental.setRentalId(rentalDTO.getRentalId());
        rental.setStartDate(rentalDTO.getStartDate());
        rental.setEndDate(rentalDTO.getEndDate()); // DODATO
        rental.setNotes(rentalDTO.getNotes());
        rental.setStatus(rentalDTO.getStatus());

        if (rentalDTO.getCar() != null) {
            rental.setCar(carMapper.toDomainEntity(rentalDTO.getCar()));
        }
        if (rentalDTO.getClient() != null) {
            rental.setClient(clientMapper.toDomainEntity(rentalDTO.getClient()));
        }
        if (rentalDTO.getUser() != null) {
            rental.setUser(userMapper.toDomainEntity(rentalDTO.getUser()));
        }

        return rental;
    }

    @Override
    public RentalDTO toDomainDTO(Rental rentalEntity) {
        if (rentalEntity == null) {
            return null;
        }
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setRentalId(rentalEntity.getRentalId());
        rentalDTO.setStartDate(rentalEntity.getStartDate());
        rentalDTO.setEndDate(rentalEntity.getEndDate()); // DODATO
        rentalDTO.setNotes(rentalEntity.getNotes());
        rentalDTO.setStatus(rentalEntity.getStatus());

        if (rentalEntity.getCar() != null) {
            rentalDTO.setCar(carMapper.toDomainDTO(rentalEntity.getCar()));
        }
        if (rentalEntity.getClient() != null) {
            rentalDTO.setClient(clientMapper.toDomainDTO(rentalEntity.getClient()));
        }
        if (rentalEntity.getUser() != null) {
            rentalDTO.setUser(userMapper.toDomainDTO(rentalEntity.getUser()));
        }

        return rentalDTO;
    }
}