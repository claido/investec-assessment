package com.investec.assessment.controller;

import com.investec.assessment.dto.ClientDto;
import com.investec.assessment.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @RequestMapping("/client/create")
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(clientService.saveClient(clientDto), HttpStatus.CREATED);
    }

    @PutMapping("/client/update/{id}")
    public ResponseEntity<ClientDto> updateClient(@Valid @RequestBody ClientDto clientDto, Long id) {
        return new ResponseEntity<>(clientService.updateClient(clientDto, id), HttpStatus.OK);
    }

    @GetMapping("/client/searchByIdNumber")
    public ResponseEntity<ClientDto> searchClientByIdNumber(@RequestParam String idNumber) {
        return new ResponseEntity<>(clientService.searchByIdNumber(idNumber), HttpStatus.OK);
    }

    @GetMapping("/client/searchByName")
    public ResponseEntity<List<ClientDto>> searchClientByName(@RequestParam String firstName) {
        return new ResponseEntity<>(clientService.searchByName(firstName), HttpStatus.OK);
    }

    @GetMapping("/client/searchByPhoneNumber")
    public ResponseEntity<ClientDto> searchClientByPhoneNumber(@RequestParam String phoneNumber) {
        return new ResponseEntity<>(clientService.searchByPhoneNumber(phoneNumber), HttpStatus.OK);
    }

}
