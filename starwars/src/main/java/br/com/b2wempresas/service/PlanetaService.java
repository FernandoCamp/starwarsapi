package br.com.b2wempresas.service;

import java.util.List;

import br.com.b2wempresas.entity.PlanetaEntity;
import br.com.b2wempresas.req.PlanetaReq;

public interface PlanetaService {

	public List<PlanetaEntity> buscaPlanetas();

	public PlanetaEntity buscaPlanetaPorId(String id);

	public void criaPlaneta(PlanetaReq planetaReq);

	public PlanetaEntity buscaPorNome(String nome);

	public void deletaPlaneta(PlanetaEntity planeta);

	public List<PlanetaEntity> planetasResp();

	public int quantidadeFilmes(PlanetaEntity planeta);
	
}
