package com.investec.assessment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.investec.assessment.dto.ClientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.random.RandomGenerator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
@ExtendWith(SpringExtension.class)
public class ClientControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createClient() throws Exception {

        ClientDto clientDto = ClientDto.builder()
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("0123456789")
                .idNumber("9307035298082")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(clientDto);

        mockMvc.perform(post("/api/client/create")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void updateClient() throws Exception {
        ClientDto clientDto = ClientDto.builder()
                .id(RandomGenerator.getDefault().nextLong())
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("0123456789")
                .idNumber("9307035298082")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(clientDto);

        mockMvc.perform(put("/api/client/update/{id}", clientDto.getId())
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void searchClientByIdNumber() throws Exception {
        String testIdNumber = "9307035298082";
        mockMvc.perform(get("/api/client/searchByIdNumber").param("idNumber", testIdNumber))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idNumber").value(testIdNumber));

    }

    @Test
    public void searchClientByFirstName() throws Exception {
        String firstName = "John";
        mockMvc.perform(get("/api/client/searchByName").param("firstName", firstName))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void searchClientByPhoneNumber() throws Exception {
        String phoneNumber = "0729048069";
        mockMvc.perform(get("/api/client/searchByPhoneNumber", phoneNumber).param("phoneNumber", phoneNumber))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(phoneNumber));

    }

}
