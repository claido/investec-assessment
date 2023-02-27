package com.investec.assessment.service;

import com.investec.assessment.dto.ClientDto;
import com.investec.assessment.exception.ClientNotFoundException;
import com.investec.assessment.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

@Service
@Slf4j
public class ClientService {

    // dummy database
    private static List<ClientDto> clientDB = new ArrayList<>();

    public ClientDto saveClient(ClientDto client) {

        log.info("Saving client. Client : {}", client);

        ValidationFailedException validationFailedException = validateClient(client);
        if(validationFailedException != null) {
            throw validationFailedException;
        }

        client.setId(RandomGenerator.getDefault().nextLong());
        clientDB.add(client);

        return client;
    }

    public ValidationFailedException validateClient(ClientDto client) {

        Map<String,String> failedValidation = new HashMap<>();
        for (ClientDto clientDto : clientDB) {

            if (client.getIdNumber().equals(clientDto.getIdNumber())) {
                failedValidation.put("ID number", "ID number already exists, duplicates not allowed");
            }

            if (client.getPhoneNumber() != null && client.getPhoneNumber().equals(clientDto.getPhoneNumber())) {
                failedValidation.put("Phone number", "Phone number already exists, duplicates not allowed");
            }
        }
        if (failedValidation.isEmpty()) {
            return null;
        }
        return new ValidationFailedException(failedValidation,"Failed to validate client");
    }
    public ClientDto updateClient(ClientDto client, Long id) {
        log.info("Updating client. Client : {}", client);

        ValidationFailedException validationFailedException = validateClient(client);
        if (validationFailedException!= null) {
            throw validationFailedException;
        }
        boolean found = false;
        for (int i = 0; i < clientDB.size(); i++) {
            ClientDto clientDto = clientDB.get(i);
            if (clientDto.getId() == id) {
                found = true;
                client.setId(id);
                clientDB.set(i, client);
                break;
            }
        }
        if (!found) {
            throw new ClientNotFoundException("Client with specified id does not exist");
        }
        return client;
    }

    public List<ClientDto> searchByName(String firstName) {

        List<ClientDto> clients = new ArrayList<>();

        for (ClientDto clientDto : clientDB) {
            if (clientDto.getFirstName().equals(firstName)) {
                clients.add(clientDto);
            }
        }
        return clients;
    }

    public ClientDto searchByIdNumber(String idNumber) {

        for (ClientDto clientDto : clientDB) {
            if (clientDto.getIdNumber().equals(idNumber)) {
                return clientDto;
            }
        }

        throw new ClientNotFoundException("Client with specified id Number does not exist");
    }

    public ClientDto searchByPhoneNumber(String phoneNumber) {

        for (ClientDto clientDto : clientDB) {
            if (clientDto.getPhoneNumber().equals(phoneNumber)) {
                return clientDto;
            }
        }

        throw new ClientNotFoundException("Client with specified phone number does not exist");
    }
}
