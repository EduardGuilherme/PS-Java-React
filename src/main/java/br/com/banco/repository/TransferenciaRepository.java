package br.com.banco.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.banco.models.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long>{
	 List<Transferencia> findAll();

	 @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia >= :inicio")
	    List<Transferencia> findByDataTransferenciaInicio(@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDateTime inicio);

	    @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia >= :fim")
	    List<Transferencia> findByDataTransferenciaFim(@DateTimeFormat(pattern = "dd/MM/yyyy") LocalDateTime fim);
	    
	    List<Transferencia> findByDataTransferenciaBetween(LocalDateTime inicio, LocalDateTime fim);

	    List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);

	    @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia BETWEEN :inicio AND :fim AND t.nomeOperadorTransacao = :nomeOperadorTransacao")
	    List<Transferencia> findByDataTransferenciaBetweenAndNomeOperadorTransacao(
	            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDateTime inicio,
	            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDateTime fim,
	            String nomeOperadorTransacao);
}
