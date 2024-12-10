package org.sm.finance.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor


public class Portfolio {

    @Id
    @GeneratedValue
    private  Long id;
    String name;
    String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_email",referencedColumnName =  "email")
    private Userinfo userinfo;
    
    @OneToMany(mappedBy = "portfolio")  // mappedBy is not available for @ManyToOne, mappedBy value should be in lowercase
    private Set<PortfolioStock>portfolioStocks;

    
    


    
}
