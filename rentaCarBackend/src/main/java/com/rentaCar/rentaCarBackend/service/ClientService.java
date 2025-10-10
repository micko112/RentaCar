package com.rentaCar.rentaCarBackend.service;

import com.rentaCar.rentaCarBackend.JPARepo.CityRepository;
import com.rentaCar.rentaCarBackend.JPARepo.ClientRepository;
import com.rentaCar.rentaCarBackend.dto.ClientDTO;
import com.rentaCar.rentaCarBackend.mapper.ClientMapper;
import com.rentaCar.rentaCarBackend.model.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final CityRepository cityRepository;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, CityRepository cityRepository) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.cityRepository = cityRepository;
    }

    public List<ClientDTO> getAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::toDomainDTO) // Pojednostavljeno mapiranje
                .collect(Collectors.toList());
    }

    public List<ClientDTO> searchClients(String searchDto) {
        List<Client> clients = clientRepository.findBySurnameStartsWith(searchDto);
        return clients.stream()
                .map(clientMapper::toDomainDTO)
                .collect(Collectors.toList());
    }

    public String addClient(ClientDTO clientDTO) {
        try {
            if (clientRepository.existsById(clientDTO.getJmbg())) {
                return "Ovaj klijent već ima nalog!";
            } else {
                Client client = clientMapper.toDomainEntity(clientDTO);
                // Ručno postavljanje grada iz baze
                cityRepository.findById(clientDTO.getCity().getId()).ifPresent(client::setCity);
                clientRepository.save(client);
                return "Nalog je uspešno kreiran!";
            }
        } catch (Exception ex) {
            return "Greška tokom kreiranja naloga!";
        }
    }

    public String deleteClient(String clientId) {
        try {
            clientRepository.deleteById(clientId);
            return "Klijent je obrisan uspešno!";
        } catch (Exception ex) {
            return "Sistem ne može da obriše klijenta!";
        }
    }

    public ClientDTO getClient(String clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        return clientMapper.toDomainDTO(client); // Pojednostavljeno mapiranje
    }

    public String updateClient(ClientDTO clientDTO) {
        try {
            Client client = clientMapper.toDomainEntity(clientDTO);
            // Ručno postavljanje grada iz baze
            cityRepository.findById(clientDTO.getCity().getId()).ifPresent(client::setCity);
            clientRepository.save(client);
            return "Klijent je ažuriran uspešno!";
        } catch (Exception ex) {
            return "Sistem ne može da ažurira klijenta!";
        }
    }

    public String login(String username, String password) {
        try {
            Client client = clientRepository.findByUsername(username);
            if (client != null && client.getPassword().equals(password)) {
                return "Dobrodošli, " + client.getName() + " " + client.getSurname() + "!";
            }
        } catch (Exception ex) {
            return "Pogrešno korisnicko ime ili prezime!";
        }
        return "Pogrešno korisnicko ime ili prezime!";
    }
}