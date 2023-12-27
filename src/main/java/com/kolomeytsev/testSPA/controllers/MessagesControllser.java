package com.kolomeytsev.testSPA.controllers;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kolomeytsev.testSPA.domain.Message;
import com.kolomeytsev.testSPA.services.MessageService;


@RestController
@RequestMapping("messages")
public class MessagesControllser {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public List<Message> messagesList(){
        return messageService.getAllMessage();
    }

    @GetMapping("{id}")
    public Message getMessage(@PathVariable("id") Message message){
        return messageService.getFirstMessageById(message.getId());
    }

    @PostMapping
    public Message addMessage(@RequestBody Message message){
        return messageService.add(message);
    }

    @PutMapping("{id}")
    public Message updateMessage(@RequestBody Message messageFromForm, @PathVariable("id") Message messageFromDb){
        BeanUtils.copyProperties(messageFromForm, messageFromDb, "id");
        return messageService.update(messageFromDb);
    }

    @DeleteMapping("{id}")
    public Message deleteMessage(@PathVariable("id") Message message){
        return messageService.delete(message.getId());
    }

    
}

//fetch('/messages', {method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify({text:'wwwwww'})}).then(console.log)
