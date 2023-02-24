package com.investec.assessment.controller;

import com.investec.assessment.dto.ClientDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.random.RandomGenerator;

@RestController
@RequestMapping("/api")
public class ClientController {

    @RequestMapping("/client/create")
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto) {
        clientDto.setId(RandomGenerator.getDefault().nextLong());
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }

    @PutMapping("/client/update/{id}")
    public ResponseEntity<ClientDto> updateClient(@Valid @RequestBody ClientDto clientDto) {
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @GetMapping("/client/searchByIdNumber")
    public ResponseEntity<ClientDto> searchClientByIdNumber(@RequestParam String idNumber) {
        return new ResponseEntity<>(ClientDto.builder()
                .id(RandomGenerator.getDefault().nextLong())
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("0123456789")
                .idNumber(idNumber)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/client/searchByName")
    public ResponseEntity<List<ClientDto>> searchClientByName(@RequestParam String firstName) {
        return new ResponseEntity<>(Arrays.asList(ClientDto.builder()
                .id(RandomGenerator.getDefault().nextLong())
                .firstName(firstName)
                .lastName("Doe")
                .phoneNumber("0123456789")
                .build()), HttpStatus.OK);
    }

    @GetMapping("/client/searchByPhoneNumber")
    public ResponseEntity<ClientDto> searchClientByPhoneNumber(@RequestParam String phoneNumber) {
        return new ResponseEntity<>(ClientDto.builder()
                .id(RandomGenerator.getDefault().nextLong())
                .firstName("John")
                .lastName("Doe")
                .phoneNumber(phoneNumber)
                .idNumber("9307035298082")
                .build(), HttpStatus.OK);
    }
}
