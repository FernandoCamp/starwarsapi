package br.com.b2wempresas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.b2wempresas.entity.PlanetaEntity;

public interface PlanetaRepository extends MongoRepository<PlanetaEntity, String> {
	public PlanetaEntity findByNameIgnoreCase(String nome);
}
