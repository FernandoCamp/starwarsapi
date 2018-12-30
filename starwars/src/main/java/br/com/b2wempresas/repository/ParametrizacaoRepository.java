package br.com.b2wempresas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.b2wempresas.entity.ParametrizacaoEntity;

public interface ParametrizacaoRepository extends MongoRepository<ParametrizacaoEntity, String> {
	public ParametrizacaoEntity findByKeyIgnoreCase(String nome);
}
