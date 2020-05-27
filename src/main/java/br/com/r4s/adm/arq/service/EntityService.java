package br.com.r4s.adm.arq.service;

import java.util.List;

public interface EntityService<T> {
	
	public List<T> findAll();
	public T save(T entity);
	public T findById(Long id);
	public void deleteById(Long id);
	
}
