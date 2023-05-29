package br.com.alura.orcamentoFamiliar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.orcamentoFamiliar.modelo.Receita;

public interface ReceitaRepository extends JpaRepository <Receita, Long> {

}
