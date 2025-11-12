package com.rentaCar.rentaCarBackend.controller;

import com.rentaCar.rentaCarBackend.connection.HttpResponse;
import com.rentaCar.rentaCarBackend.connection.Response;
import com.rentaCar.rentaCarBackend.dto.ClientDTO;
import com.rentaCar.rentaCarBackend.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/clients") // IZMENA
public class ClientController {

        private final ClientService clientService; // IZMENA

        public ClientController(ClientService clientService) { // IZMENA
                this.clientService = clientService;
        }

        @PostMapping("/add")
        public ResponseEntity<Response> addClient(@RequestBody ClientDTO client) { // IZMENA
                String result = clientService.addClient(client); // IZMENA

                if ("This client already has an account!".equals(result)) { // IZMENA
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.CONFLICT));
                }

                if ("Account created successfully!".equals(result)) {
                        return ResponseEntity.status(HttpStatus.OK)
                                .body(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.OK));
                }

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(HttpResponse.getResponseWithData(result, Map.of("value", result), HttpStatus.BAD_REQUEST));
        }

        @GetMapping("/all")
        public ResponseEntity<Response> getAll() {
                return ResponseEntity.ok(
                        HttpResponse.getResponseWithData(
                                "Clients have been found!", // IZMENA
                                Map.of("values", clientService.getAll()),
                                HttpStatus.OK));
        }

        @GetMapping("/getClient/{clientId}") // IZMENA
        public ResponseEntity<Response> findClientById(@PathVariable("clientId") String clientId) { // IZMENA
                return ResponseEntity.ok(
                        HttpResponse.getResponseWithData(
                                "The client has been found!", // IZMENA
                                Map.of("value", clientService.getClient(clientId)), // IZMENA
                                HttpStatus.OK));
        }

        @PostMapping("/update")
        public ResponseEntity<Response> updateClient(@RequestBody ClientDTO client) { // IZMENA
                String result = clientService.updateClient(client); // IZMENA
                HttpStatus status = "Klijent je ažuriran uspešno!".equals(result)
                        ? HttpStatus.OK
                        : HttpStatus.BAD_REQUEST;

                return ResponseEntity.status(status)
                        .body(HttpResponse.getResponseWithData(result, Map.of("value", result), status));
        }

        @GetMapping("/login/{username}/{password}")
        public ResponseEntity<Response> login(@PathVariable("username") String username, @PathVariable("password") String password) {
                String message = clientService.login(username, password);
                return ResponseEntity.ok(HttpResponse.getResponse(message, HttpStatus.OK));
        }

        @GetMapping("/search/{searchRequest}")
        public ResponseEntity<Response> searchClient(@PathVariable("searchRequest") String searchRequest) { // IZMENA
                return ResponseEntity.ok(
                        HttpResponse.getResponseWithData(
                                "Searching for clients...", // IZMENA
                                Map.of("values", clientService.searchClients(searchRequest)), // IZMENA
                                HttpStatus.OK));
        }

        @DeleteMapping("/delete/{clientId}")
        public ResponseEntity<Response> deleteClient(@PathVariable String clientId) {
                String result = clientService.deleteClient(clientId);
                HttpStatus status = "Klijent je obrisan uspešno!".equals(result)
                        ? HttpStatus.OK
                        : HttpStatus.BAD_REQUEST;

                return ResponseEntity.status(status)
                        .body(HttpResponse.getResponseWithData(result, Map.of("value", result), status));
        }
}