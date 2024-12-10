package org.sm.finance.controller;

import org.sm.finance.model.Portfolio;
import org.sm.finance.service.Portfolioservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/portfolio")

@RestController
public class portfoliocontroller {
   
    @Autowired
    private Portfolioservice portfolioservice;
    @PostMapping("/create-portfolio")
    
    
    public ResponseEntity<Portfolio> createUserPortfolio(@RequestBody Portfolio portfolio_info)

    {

        Portfolio saved_Portfolio = portfolioservice.createPortfolio(portfolio_info);
        return(ResponseEntity.ok().body(saved_Portfolio));
          
    }
    @GetMapping("/get-portfolio")
    public ResponseEntity<Portfolio> getUserPortfolio()

    {

        Portfolio retrieved_Portfolio = portfolioservice.getPortfolio();
        return(ResponseEntity.ok().body(retrieved_Portfolio));
          
    }
    @DeleteMapping("/delete-portfolio")
    public ResponseEntity<String> deleteUserPortfolio()

    {

        boolean portfolio_deletion_response = portfolioservice.deletePortfolio();
        if(portfolio_deletion_response  == true){
        return(ResponseEntity.ok().body("Portfolio deletion successfukk"));
        }
        else
        {
            return(ResponseEntity.ok().body("Portfolio deletion successful"));
        }
          
    }

    

    
}
