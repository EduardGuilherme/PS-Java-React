package br.com.banco.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.models.Transferencia;
import br.com.banco.repository.TransferenciaRepository;

@RestController
@RequestMapping("transferencia")
public class TransferenciaController {
	@Autowired
	private TransferenciaRepository transferenciaRepository;
	
	@GetMapping("/buscardados")
	@CrossOrigin(origins = "*")
    public ResponseEntity<List<Transferencia>> listarTransferencias(
    		@RequestParam(required = false) String inicio,
            @RequestParam(required = false) String fim,
            @RequestParam(required = false) String nomeOperadorTransacao) {

        LocalDateTime inicioDateTime = null;
        LocalDateTime fimDateTime = null;

        if (inicio != null && !inicio.isEmpty()) {
            inicioDateTime = LocalDate.parse(inicio, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();
        }

        if (fim != null && !fim.isEmpty()) {
            fimDateTime = LocalDate.parse(fim, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atTime(LocalTime.MAX);
        }

        List<Transferencia> transferencias;

        if (inicioDateTime != null && fimDateTime != null && nomeOperadorTransacao != null) {
            transferencias = transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(
                    inicioDateTime, fimDateTime, nomeOperadorTransacao);
        } else if (inicioDateTime != null && fimDateTime == null && nomeOperadorTransacao == null) {
            
            transferencias = transferenciaRepository.findByDataTransferenciaInicio(inicioDateTime);
        } else if (fimDateTime != null && inicioDateTime == null && nomeOperadorTransacao == null) {
            
            transferencias = transferenciaRepository.findByDataTransferenciaFim(fimDateTime);
        } else if (inicioDateTime != null && fimDateTime != null && nomeOperadorTransacao == null) {
            
            transferencias = transferenciaRepository.findByDataTransferenciaBetween(inicioDateTime, fimDateTime);
        } else if (nomeOperadorTransacao != null && inicioDateTime == null && fimDateTime == null) {
            
            transferencias = transferenciaRepository.findByNomeOperadorTransacao(nomeOperadorTransacao);
        } else {
            
            transferencias = transferenciaRepository.findAll();
        }

        return ResponseEntity.ok(transferencias);
    }
}
