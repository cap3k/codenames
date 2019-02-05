package fr.codenames.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.codenames.dao.IDAOChat;
import fr.codenames.model.Message;
import fr.codenames.projection.Views;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/chat")
public class ChatRestController {
	
	@Autowired
	private IDAOChat daoChat;
	
	@GetMapping
	@JsonView(Views.Message.class)
	public List<Message> findAll() {
		return this.daoChat.findAll();
	}
	
	@PostMapping
	@JsonView(Views.Message.class)
	public Message save(@RequestBody Message message) {
		this.daoChat.save(message);
		return message;
	}
	
}