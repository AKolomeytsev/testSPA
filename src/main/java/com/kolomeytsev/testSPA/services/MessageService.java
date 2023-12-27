package com.kolomeytsev.testSPA.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.kolomeytsev.testSPA.domain.Message;
import com.kolomeytsev.testSPA.repositorys.IMessageRepository;

@Service
public class MessageService {

    private final IMessageRepository iMessageRepository;
    public MessageService(IMessageRepository iMessageRepository){
        this.iMessageRepository = iMessageRepository;
    }
   
    public Message getFirstMessageById(Long id){
        return iMessageRepository.findById(id).get();
    }

    public List<Message> getAllMessage(){
        return iMessageRepository.findAll();
    }

    public Message add(Message message){
        iMessageRepository.save(message);
        return message;
    }

    public Message update(Message message){
        iMessageRepository.save(message);
        return message;
    }
    
    public Message delete(Long id){
        Message item = getFirstMessageById(id);
        iMessageRepository.delete(item);;
        return item;
    }
    
}
