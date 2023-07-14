package br.com.banco.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.models.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{
	List<Transferencia> findAll();
	
	List<Transferencia> findByDataTransferenciaBetween(LocalDateTime inicio, LocalDateTime fim);

    List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);

    List<Transferencia> findByDataTransferenciaBetweenAndNomeOperadorTransacao(
            LocalDateTime inicio, LocalDateTime fim, String nomeOperadorTransacao);
}
