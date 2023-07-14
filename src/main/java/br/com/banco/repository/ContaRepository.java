package br.com.banco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.banco.models.Conta;

public interface ContaRepository extends PagingAndSortingRepository<Conta, Integer> {
	@Query(value = "SELECT C FROM Conta C WHERE C.nomeResponsavel LIKE %:nomeResponsavel%")
	List<Conta> findByNomeResponsavel(String nomeResponsavel);
	
}
