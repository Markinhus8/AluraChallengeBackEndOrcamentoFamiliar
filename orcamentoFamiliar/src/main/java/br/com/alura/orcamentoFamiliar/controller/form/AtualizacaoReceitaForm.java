package br.com.alura.orcamentoFamiliar.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alura.orcamentoFamiliar.modelo.Receita;
import br.com.alura.orcamentoFamiliar.repository.ReceitaRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AtualizacaoReceitaForm {

	private String descricao;
	private BigDecimal valor;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate data;


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public boolean validarMesmaDescricaoDentroDoMesmoMes(ReceitaRepository receitaRepository) {
		LocalDate startDate = data.with(TemporalAdjusters.firstDayOfMonth());

		LocalDate endDate = data.with(TemporalAdjusters.lastDayOfMonth());

		return receitaRepository.findByDescricaoAndDataBetween(descricao, 
				startDate, 
				endDate)
				.isPresent();
	}
	
	public Receita atualizar(Long id, ReceitaRepository receitaRepository) {
		Receita receita = receitaRepository.getReferenceById(id);
		receita.setDescricao(descricao);
		receita.setValor(valor);
		receita.setData(data);
		
		return receita;
	}

}

