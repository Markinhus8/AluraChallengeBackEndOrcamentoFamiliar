package br.com.alura.orcamentoFamiliar.controller.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.orcamentoFamiliar.modelo.CategoriaDespesa;
import br.com.alura.orcamentoFamiliar.modelo.Despesa;

public class DespesaVO{

	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	private CategoriaDespesa categoria;

	public DespesaVO(Despesa despesa) {
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.data = despesa.getData();
		this.categoria = despesa.getCategoria();
	}

	public String getDescricao() {
		return descricao;
	}
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

	public static List<DespesaVO> converterListaDespesaEntidadeParaVo(List<Despesa> despesas){
		List<DespesaVO> vo = new ArrayList<>();
		for(Despesa d : despesas) {
			vo.add(new DespesaVO(d));
		}
		return vo;

	}
}
