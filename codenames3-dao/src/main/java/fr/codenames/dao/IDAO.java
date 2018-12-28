package fr.codenames.dao;

import java.util.List;

public interface IDAO<T> {
	/**
	 * Rechercher toutes les entites
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * Rechercher une entite par son identifiant
	 * @param id
	 * @return
	 */
	public T findById(int id);
	
	/**
	 * Sauvegarder une entite (creation et modification)
	 * @param entity
	 * @return
	 */
	public T save(T entity);
	
	/**
	 * Supprimer une entite
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * Supprimer une entite par son identifiant
	 * @param id
	 */
	public void deleteById(int id);
	
	public void close();
}