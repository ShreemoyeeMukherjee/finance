package org.sm.finance.repository;

import org.sm.finance.model.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
// The parameter after Userinfo refers to the datatype of primary key of model
public interface Userinforepository extends JpaRepository<Userinfo,Long> {
List<Userinfo> findByEmail(String email);
}
