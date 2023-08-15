package br.com.alura.orcamentoFamiliar.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.alura.orcamentoFamiliar.modelo.CategoriaDespesa;
import br.com.alura.orcamentoFamiliar.modelo.Despesa;
import br.com.alura.orcamentoFamiliar.repository.DespesaRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DespesaForm {
	
	@NotEmpty(message="A descrição deve ser informada")
	private String descricao;
	@NotNull(message="Um valor deve ser informado")
	private BigDecimal valor;
	@NotNull(message="A data deve ser informada")
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate data;
	
	//Se a categoria da despesa não for informada, a API deve atribuir automaticamente a categoria Outras à despesa
	private CategoriaDespesa categoria = CategoriaDespesa.OUTRAS ;


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
	
	public CategoriaDespesa getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDespesa categoria) {
		this.categoria = categoria;
	}

	public boolean validarMesmaDescricaoDentroDoMesmoMes(DespesaRepository despesaRepository) {
		LocalDate startDate = data.with(TemporalAdjusters.firstDayOfMonth());

		LocalDate endDate = data.with(TemporalAdjusters.lastDayOfMonth());

		return despesaRepository.findByDescricaoAndDataBetween(descricao, 
				startDate, 
				endDate)
				.isPresent();
	}

	public Despesa converterParaEntidadeDespesa() {
		Despesa despesa = new Despesa();

		despesa.setDescricao(descricao);
		despesa.setValor(valor);
		despesa.setData(data);
		despesa.setCategoria(categoria);

		return despesa;
	}

}

