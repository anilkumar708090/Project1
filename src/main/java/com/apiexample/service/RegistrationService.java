package com.apiexample.service;

import com.apiexample.dto.RegistrationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegistrationService {

     RegistrationDto createRegistration(RegistrationDto registrationDto);


    void deleteRegistrationByid(long id);

    RegistrationDto updateRegistration(long id, RegistrationDto registrationDto);


    List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir);

    public RegistrationDto getRegistrationById(long id);
}
