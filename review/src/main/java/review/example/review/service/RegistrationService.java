package review.example.review.service;

import java.util.List;
import org.springframework.stereotype.Service;

import review.example.review.model.RegistrationDetails;
import review.example.review.repository.RegistrationRepository;

@Service
public class RegistrationService {

    // creating bean for registerrepo
    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    // to check the email already exist or not
    public boolean isDataExists(String someUniqueFieldValue) {
        return registrationRepository.existsByEmail(someUniqueFieldValue);
    }

    // posting values
    public boolean storeValues(RegistrationDetails registrationDetails) {

        try {
            registrationRepository.save(registrationDetails);
        }

        catch (Exception e) {
            return false;
        }
        return true;
    }

    // get the values from db
    public List<RegistrationDetails> getValues(RegistrationDetails registrationDetails) {
        return registrationRepository.findAll();
    }

    // delete from db
    public void deleteRegisters(int productId)
    {
        registrationRepository.deleteById(productId);
    }

    // public RegistrationDetails saveRegister(RegistrationDetails registrationDetails)
    // {
    //     return registrationRepository.save(registrationDetails);
    // }
    
    //updating db
    public RegistrationDetails putbyregister(int custId,RegistrationDetails registrationDetails){
        return registrationRepository.save(registrationDetails);
    }

}
