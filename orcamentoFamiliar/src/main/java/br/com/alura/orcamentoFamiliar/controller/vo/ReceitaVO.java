package br.com.alura.orcamentoFamiliar.controller.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.orcamentoFamiliar.modelo.Receita;

public class ReceitaVO{
	
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	
	public ReceitaVO(Receita receita) {
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
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

	public static List<ReceitaVO> converter(List<Receita> receitas){
		List<ReceitaVO> vo = new ArrayList<>();
		for(Receita r : receitas) {
			vo.add(new ReceitaVO(r));
		}
		return vo;
		
	}
}
