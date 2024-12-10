package org.sm.finance.repository;



import java.util.Optional;

import org.sm.finance.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository

public interface Portfolirepository  extends JpaRepository<Portfolio,Long>{
    Optional<Portfolio> findByUserinfo_Email(String email);
    
}
