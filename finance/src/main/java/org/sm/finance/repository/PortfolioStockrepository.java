package org.sm.finance.repository;

import org.sm.finance.model.PortfolioStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioStockrepository extends JpaRepository <PortfolioStock,Long>{
    
}
