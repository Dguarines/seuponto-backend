package br.com.r4s.adm.arq.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.r4s.adm.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public abstract class AbstractService<T extends JpaRepository<U, Long>, U> implements EntityService<U> {

	protected final T repository;
	
	public List<U> findAll() {
		return repository.findAll();
	}
	
	public U save(U u) {
		return repository.save(u);
	}
	
	public U findById(Long id) {
		return repository.findById(id)
						 .orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
