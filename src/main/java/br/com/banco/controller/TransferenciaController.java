package br.com.banco.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Transferencia>> listarTransferencias(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fim,
            @RequestParam(required = false) String nomeOperadorTransacao) {

        LocalDateTime inicioDateTime = inicio != null ? inicio.atStartOfDay() : null;
        LocalDateTime fimDateTime = fim != null ? fim.atTime(LocalTime.MAX) : null;

        List<Transferencia> transferencias = null;

        if (inicioDateTime != null && fimDateTime != null && nomeOperadorTransacao != null) {
            transferencias = transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(
                    inicioDateTime, fimDateTime, nomeOperadorTransacao);
        } else if (inicioDateTime != null && fimDateTime != null) {
            transferencias = transferenciaRepository.findByDataTransferenciaBetween(inicioDateTime, fimDateTime);
        } else if (nomeOperadorTransacao != null) {
            transferencias = transferenciaRepository.findByNomeOperadorTransacao(nomeOperadorTransacao);
        } else {
            transferencias = transferenciaRepository.findAll();
        }

        return ResponseEntity.ok(transferencias);
    }
}
