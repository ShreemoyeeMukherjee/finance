package org.sm.finance.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PortfolioStock {
    @ManyToOne
    @JoinColumn(name = "portfolio",referencedColumnName = "id")
    private Portfolio portfolio;
    
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "stock", referencedColumnName = "id")
   private Stock stock;
    private long quantiy;
    private double purchasePrice;
    private  Date dateofPurchase;

}
