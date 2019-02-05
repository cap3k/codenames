package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Message;

public interface IDAOChat extends JpaRepository<Message, Integer>{

}
