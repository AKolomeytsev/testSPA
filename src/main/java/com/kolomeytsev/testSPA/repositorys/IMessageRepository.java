package com.kolomeytsev.testSPA.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kolomeytsev.testSPA.domain.Message;

public interface IMessageRepository extends JpaRepository<Message,Long>{
    
}
