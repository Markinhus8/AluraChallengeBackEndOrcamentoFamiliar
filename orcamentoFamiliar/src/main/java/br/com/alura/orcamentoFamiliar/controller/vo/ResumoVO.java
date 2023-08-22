package br.com.alura.orcamentoFamiliar.controller.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

import br.com.alura.orcamentoFamiliar.repository.DespesaRepository;
import br.com.alura.orcamentoFamiliar.repository.ReceitaRepository;

public class ResumoVO {

	private BigDecimal totalReceita;
	private BigDecimal totalDespesa;
	private BigDecimal saldo;

	private Collection<?> totalPorCategoria;

	public ResumoVO(LocalDate dataInicial, LocalDate dataFinal, ReceitaRepository receitaRepository,
			DespesaRepository despesaRepository) {

		this.totalReceita = receitaRepository.sumBetweenData(dataInicial, dataFinal).orElse(BigDecimal.ZERO);
		this.totalDespesa = despesaRepository.sumBetweenData(dataInicial, dataFinal).orElse(BigDecimal.ZERO);

		this.saldo = this.totalReceita.subtract(this.totalDespesa);

		this.totalPorCategoria = despesaRepository.exibirTotalPorCategoria(dataInicial, dataFinal);
	}

	public BigDecimal getTotalReceita() {
		return totalReceita;
	}

	public void setTotalReceita(BigDecimal totalReceita) {
		this.totalReceita = totalReceita;
	}

	public BigDecimal getTotalDespesa() {
		return totalDespesa;
	}

	public void setTotalDespesa(BigDecimal totalDespesa) {
		this.totalDespesa = totalDespesa;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Collection<?> getTotalPorCategoria() {
		return totalPorCategoria;
	}

	public void setTotalPorCategoria(Collection<?> totalPorCategoria) {
		this.totalPorCategoria = totalPorCategoria;
	}

}
