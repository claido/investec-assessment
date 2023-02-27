package com.investec.assessment.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class ClientDto {

    private Long id;


    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    private String phoneNumber;

    @NotBlank(message = "ID number is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9_-](?!.*::)[^%+\\\\\\\\/#'\\\"]+$", message = "ID number is invalid")
    private String idNumber;

    private String physicalAddress;
}
