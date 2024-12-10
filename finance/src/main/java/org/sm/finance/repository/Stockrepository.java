package org.sm.finance.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sm.finance.model.Stock;

@Repository
public interface Stockrepository extends  JpaRepository<Stock,Long>{
    
}
