package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.models.Conta;
import br.com.banco.repository.ContaRepository;

@RestController
@RequestMapping("cliente")
public class ContaController {
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping("/")
	public Iterable<Conta> getContas() {
		return contaRepository.findAll();
	}
	
	@GetMapping("/buscarPorNome")
	public List<Conta> buscarPorNome(@RequestBody Conta conta) {
		String nome = conta.getNomeResponsavel();
	    return contaRepository.findByNomeResponsavel(nome);
	}
}
