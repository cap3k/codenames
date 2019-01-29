package fr.codenames.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.codenames.model.Participation;

public interface IDAOParticipation extends JpaRepository<Participation, Integer> {

}
