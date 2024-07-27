package com.apiexample.controller;

import com.apiexample.dto.RegistrationDto;
import com.apiexample.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @PostMapping
   // responseEntity helps in returning post data by programmer and https status
    public ResponseEntity<?> addRegistration(
           @Valid @RequestBody RegistrationDto registrationDto ,
           BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }

        RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        return  new ResponseEntity<>(regDto, HttpStatus.CREATED);

    }

    //response entity send the response
    @DeleteMapping
    public ResponseEntity<String>deleteRegistrationByid( @RequestParam   long id){
        registrationService.deleteRegistrationByid(id);
       return new ResponseEntity<>("Registration Deleted",HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RegistrationDto> updateRegistrationDto(@RequestParam long id,
                                                                   @RequestBody RegistrationDto registrationDto) {
        RegistrationDto Dto = registrationService.updateRegistration(id, registrationDto);
        return new ResponseEntity<>(Dto, HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<List<RegistrationDto>> getAllRegistrations(

            //code for pagination
            @RequestParam(name = "pagNo" , defaultValue = "0", required = false) int pageNo,
            @RequestParam(name = "pagSize" , defaultValue = "5", required = false) int pageSize ,
            @RequestParam(name = "sortBy" , defaultValue = "name", required = false) String sortBy,
          @RequestParam(name = "sortDir" , defaultValue = "name", required = false) String sortDir

    ){
      List<RegistrationDto> dtos= registrationService.getAllRegistrations(pageNo,pageSize,sortBy,sortDir);
      return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/getbyid")
    public  ResponseEntity<RegistrationDto> getRegistrationById(@RequestParam long id){
       RegistrationDto reg= registrationService.getRegistrationById(id);
        return  new ResponseEntity<>(reg, HttpStatus.OK);
        }

}
