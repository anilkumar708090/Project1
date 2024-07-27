package com.apiexample.service;

import com.apiexample.dto.RegistrationDto;
import com.apiexample.entity.Registration;
import com.apiexample.exception.ResourceNotFound;
import com.apiexample.repository.RegistrationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RegistrationServiceIMPL implements RegistrationService{

    private RegistrationRepository registrationRepository;

    public RegistrationServiceIMPL(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        Registration registration= mapToEntity(registrationDto);
        Registration  savedEntity=registrationRepository.save(registration);
        RegistrationDto dto=mapToDto(savedEntity);
        return dto;
    }

  Registration  mapToEntity(RegistrationDto dto){
        Registration entity=new Registration();
      entity.setName(dto.getName());
      entity.setMobile(dto.getMobile());
      entity.setEmail(dto.getEmail());
      return entity;

    }

   RegistrationDto mapToDto(Registration registration){
                RegistrationDto dto=new RegistrationDto();

       dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());

        return dto;
    }

    @Override
    public void deleteRegistrationByid(long id) {
        registrationRepository.deleteById(id);
    }

    @Override
    public RegistrationDto updateRegistration(long id, RegistrationDto registrationDto) {

      Optional<Registration> opreg= registrationRepository.findById(id);
      Registration registration=opreg.get();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());

        Registration savedEntity=registrationRepository.save(registration);

       RegistrationDto dto= mapToDto(registration);

        return dto;
    }

    @Override
    public List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir) {
    // List<Registration> registrations =  registrationRepository.findAll();

      Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);
      Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
       Page<Registration> all=registrationRepository.findAll(pageable);
       List<Registration> registrations=all.getContent();
       List<RegistrationDto> registrationDtos=  registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        return registrationDtos;
    }

    @Override
    public RegistrationDto getRegistrationById(long id){
      Registration registration=  registrationRepository.findById(id).orElseThrow(()->new ResourceNotFound("id not found with Id:"+id));

      RegistrationDto dto= mapToDto(registration);
      return dto;
    }

}
