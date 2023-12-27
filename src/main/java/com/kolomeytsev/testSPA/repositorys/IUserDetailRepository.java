package com.kolomeytsev.testSPA.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kolomeytsev.testSPA.domain.User;

public interface IUserDetailRepository extends JpaRepository<User,String> {
    
}
