package com.rodrigo.cadastro.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigo.cadastro.domain.Pessoa;
import com.rodrigo.cadastro.repositories.PessoaRepository;

@Service
public class DBService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		
		Pessoa pe1 = new Pessoa(null, "Teste", sdf.parse("21/02/1995"), "04352222100");
		Pessoa pe2 = new Pessoa(null, "Teste2", sdf.parse("17/02/1995"), "04935820101");
		
		pessoaRepository.saveAll(Arrays.asList(pe1, pe2));
	}
}
