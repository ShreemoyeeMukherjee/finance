package org.sm.finance.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
// import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Userinfo {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
    @CreatedDate
    private LocalDateTime createdAt;
    @OneToOne(mappedBy = "userinfo")  // Each user will have one portfolio hence one to one mapping
    private Portfolio portfolio;
    
} 


