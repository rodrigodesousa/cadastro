package com.rodrigo.cadastro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rodrigo.cadastro.domain.Pessoa;
import com.rodrigo.cadastro.repositories.PessoaRepository;
import com.rodrigo.cadastro.services.exceptions.DataIntegrityException;
import com.rodrigo.cadastro.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repo;
	
	public Pessoa find(Integer id) {
		Optional<Pessoa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id
				+ ", Tipo: " + Pessoa.class.getName()));
	}
	public Pessoa insert(Pessoa obj) {
		obj.setId(null);
		try {
			return repo.save(obj);	
		}
		catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possivel inserir novos registros! Limite de registros alcançado.");
		}
	}
	public Pessoa update(Pessoa obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	public List<Pessoa> findAll(){
		return repo.findAll();
	}
	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
}
