package org.sm.finance.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue
    private long id;
    private String symbol;
    
    private long purchasePrice;
    @OneToMany(mappedBy = "stock")
    private Set<PortfolioStock>portfolioStocks;


    

    
}
