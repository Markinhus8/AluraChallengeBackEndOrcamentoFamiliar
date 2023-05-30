package br.com.alura.orcamentoFamiliar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.orcamentoFamiliar.modelo.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

}
