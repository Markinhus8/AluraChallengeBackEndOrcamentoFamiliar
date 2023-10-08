package br.com.alura.orcamentoFamiliar.controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.orcamentoFamiliar.controller.vo.ResumoVO;
import br.com.alura.orcamentoFamiliar.repository.DespesaRepository;
import br.com.alura.orcamentoFamiliar.repository.ReceitaRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/resumo")
@SecurityRequirement(name = "bearer-key")
public class ResumoController {

	@Autowired
	ReceitaRepository receitaRepository;
	
	@Autowired
	DespesaRepository despesaRepository;
	
	// Resumo do mês
	/* Endpoint para detalhar o resumo de determinado mês.
	O resumo do mês deve conter as seguintes informações:
	Valor total das receitas no mês
	Valor total das despesas no mês
	Saldo final no mês
	Valor total gasto no mês em cada uma das categorias */
	@GetMapping("{ano}/{mes}")
	public ResponseEntity<?> exibirResumo(@PathVariable Integer ano, @PathVariable Integer mes) {
		LocalDate dataInicial;

		try {
			dataInicial = LocalDate.of(ano, mes, 1);
		} catch (DateTimeException e) {
			return ResponseEntity.badRequest().build();
		}

		LocalDate dataFinal = dataInicial.with(TemporalAdjusters.lastDayOfMonth());

		ResumoVO resumoVo = new ResumoVO(dataInicial, dataFinal, receitaRepository, despesaRepository);

		return ResponseEntity.ok(resumoVo);
	}

}
