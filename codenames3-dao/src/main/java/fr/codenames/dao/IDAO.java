package fr.codenames.dao;

import java.util.List;

public interface IDAO<T> {
	/**
	 * Rechercher toutes les entités
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * Rechercher une entité par son identifiant
	 * @param id
	 * @return
	 */
	public T findById(int id);
	
	/**
	 * Sauvegarder une entité (création et modification)
	 * @param entity
	 * @return
	 */
	public T save(T entity);
	
	/**
	 * Supprimer une entité
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * Supprimer une entité par son identifiant
	 * @param id
	 */
	public void deleteById(int id);
}