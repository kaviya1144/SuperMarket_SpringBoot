package review.example.review.controller;

import org.springframework.web.bind.annotation.RestController;
import review.example.review.model.RegistrationDetails;
import review.example.review.service.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class RegistrationController {

    // creating a bean for registrationservice
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //#create#
    @PostMapping("/postreview")
    public ResponseEntity<RegistrationDetails> postRegister(@RequestBody RegistrationDetails registrationDetails) {

        // Email will conflict where already exists...
        if (registrationService.isDataExists(registrationDetails.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // to check the 1000 th customer
        if (registrationDetails.getCustId() == 1000) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        // to post the values
        if (registrationService.storeValues(registrationDetails)) {
            return new ResponseEntity<>(registrationDetails, HttpStatus.NOT_ACCEPTABLE);

        }
        return new ResponseEntity<>(registrationDetails, HttpStatus.ACCEPTED);
    }

    //#read#
    @GetMapping("/getreview")
    public ResponseEntity<List<RegistrationDetails>> getDetails(RegistrationDetails registrationDetails) {
        List<RegistrationDetails> registrationDetailsList = registrationService.getValues(registrationDetails);
        return new ResponseEntity<>(registrationDetailsList, HttpStatus.OK);
    }

    //update
    @PutMapping("/putregister/{custId}")
    public ResponseEntity<RegistrationDetails> putloginId(@PathVariable("custId") int custId, @RequestBody RegistrationDetails registrationDetails){
        RegistrationDetails putreg = registrationService.putbyregister(custId,registrationDetails);
        if(putreg!=null)
            return new ResponseEntity<>(putreg,HttpStatus.CREATED);
        return new ResponseEntity<>(putreg,HttpStatus.NOT_FOUND);    
    }

    //#delete#
    @DeleteMapping("/deletereview/{custId}")
    public void deleteRegister(@PathVariable int custId) {
        registrationService.deleteRegisters(custId);
    }
}