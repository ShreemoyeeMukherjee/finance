package org.sm.finance.service;

import java.util.List;
import java.util.Optional;

import org.sm.finance.model.Portfolio;
import org.sm.finance.model.Userinfo;
import org.sm.finance.repository.Portfolirepository;
import org.sm.finance.repository.Userinforepository;
import org.sm.finance.utils.authentication.CustomUserDetails;
import org.sm.finance.utils.authentication.RetrieveDetailsFromAuth;
import org.sm.finance.utils.exceptions.portfolioexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Portfolioservice {
    @Autowired
    private RetrieveDetailsFromAuth auth_retrieved_userdetails;
    @Autowired
    private Userinforepository userinforepository;
    @Autowired
    private Portfolirepository portfolirepository;
    public Portfolio createPortfolio(Portfolio portfolio_obj)
    {
        CustomUserDetails userDetails = auth_retrieved_userdetails.retrieveUserDetailsFromAuth();
        String user_email = userDetails.getUsername();
        List<Userinfo> userinfo_objects = userinforepository.findByEmail(user_email);
        Userinfo userinfo_obj = userinfo_objects.get(0);
        portfolio_obj.setUserinfo(userinfo_obj);
   
        return portfolirepository.save(portfolio_obj);
} 
public Portfolio getPortfolio()
{
    CustomUserDetails userDetails = auth_retrieved_userdetails.retrieveUserDetailsFromAuth();
    // each user will have a single portfolio
        String user_email = userDetails.getUsername();
        
       Optional <Portfolio> OptionalPortfolio = portfolirepository.findByUserinfo_Email(user_email);
       if(OptionalPortfolio.isPresent())
       {
          Portfolio retrievedPortfolio = OptionalPortfolio.get();
          return (retrievedPortfolio);
       }
       else
       {
         throw new portfolioexception("Portfolio with this email not found");
       }

}
    

public boolean deletePortfolio()
{

    CustomUserDetails userDetails = auth_retrieved_userdetails.retrieveUserDetailsFromAuth();
    
        String user_email = userDetails.getUsername();
        Optional <Portfolio> OptionalPortfolio = portfolirepository.findByUserinfo_Email(user_email);
       if(OptionalPortfolio.isPresent())
       {
          Portfolio retrievedPortfolio = OptionalPortfolio.get();
          portfolirepository.delete(retrievedPortfolio);
          return true;
       }
       else{

        throw new portfolioexception("Portfolio with this email not found");
       }
}

public boolean ChangePortfolioDescription( String new_description){
    CustomUserDetails userDetails = auth_retrieved_userdetails.retrieveUserDetailsFromAuth();
    
        String user_email = userDetails.getUsername();
        Optional <Portfolio> OptionalPortfolio = portfolirepository.findByUserinfo_Email(user_email);
       if(OptionalPortfolio.isPresent())
       {
          Portfolio retrievedPortfolio = OptionalPortfolio.get();
          retrievedPortfolio.setDescription(new_description);
          return true;

}
else{

    throw new portfolioexception("Portfolio with this email not found");
   }
}
public boolean ChangePortfolioName( String new_name){
    CustomUserDetails userDetails = auth_retrieved_userdetails.retrieveUserDetailsFromAuth();
    
        String user_email = userDetails.getUsername();
        Optional <Portfolio> OptionalPortfolio = portfolirepository.findByUserinfo_Email(user_email);
       if(OptionalPortfolio.isPresent())
       {
          Portfolio retrievedPortfolio = OptionalPortfolio.get();
          retrievedPortfolio.setDescription(new_name);
          return true;

}
else{

    throw new portfolioexception("Portfolio with this email not found");
   }
}
}

