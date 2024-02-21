package review.example.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import review.example.review.model.RegistrationDetails;

public interface RegistrationRepository extends JpaRepository<RegistrationDetails,Integer>{
    
    boolean existsByEmail(String email);
}