package br.com.b2wempresas.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.b2wempresas.controller.PlanetaController;
import br.com.b2wempresas.entity.PlanetaEntity;
import br.com.b2wempresas.repository.ParametrizacaoRepository;
import br.com.b2wempresas.repository.PlanetaRepository;
import br.com.b2wempresas.req.PlanetaReq;
import br.com.b2wempresas.resp.Result;
import br.com.b2wempresas.service.PlanetaService;

@Service
public class PlanetaServiceImpl implements PlanetaService {

	public static final Logger logger = LoggerFactory.getLogger(PlanetaController.class);

	public static final String SWAPI_SERVICE_URL = "co.swapi.service.url";

	@Autowired
	private PlanetaRepository planetaRepository;

	@Autowired
	private ParametrizacaoRepository parametrizacaoRepository;

	@Override
	@Cacheable("films")
	public List<PlanetaEntity> buscaPlanetas() {
		List<PlanetaEntity> planetas = planetaRepository.findAll();
		planetas.forEach(planeta -> {
			planeta.setQuantityFilms(quantidadeFilmes(planeta));
		});
		return planetas;
	}

	@Override
	public void criaPlaneta(PlanetaReq planetaReq) {
		PlanetaEntity planetaEntity = new PlanetaEntity();
		BeanUtils.copyProperties(planetaReq, planetaEntity);
		planetaRepository.save(planetaEntity);
	}

	@Override
	public PlanetaEntity buscaPlanetaPorId(String id) {
		PlanetaEntity planetaEntity = planetaRepository.findById(id).get();
		planetasResp().forEach(planetaResp -> {
			planetaEntity.setQuantityFilms(quantidadeFilmes(planetaEntity));
		});
		return planetaEntity;
	}

	@Override
	@Cacheable("films")
	public PlanetaEntity buscaPorNome(String nome) {
		PlanetaEntity planeta = planetaRepository.findByNameIgnoreCase(nome);
		planetasResp().forEach(planetaResp -> {
			planeta.setQuantityFilms(quantidadeFilmes(planeta));
		});
		return planeta;
	}

	@Override
	public void deletaPlaneta(PlanetaEntity planeta) {
		planetaRepository.delete(planeta);
	}

	@Override
	@Cacheable("films")
	public List<PlanetaEntity> planetasResp() {
		String urlPlanetas = parametrizacaoRepository.findByKeyIgnoreCase(SWAPI_SERVICE_URL).getValue();
		logger.info("Fazendo requisição para {}", urlPlanetas);
		RestTemplate restTemplate = new RestTemplate();
		try {
			return restTemplate.getForObject(urlPlanetas, Result.class).getResults();
		} catch (Exception e) {
			logger.error("Host desconhecido {}.", urlPlanetas);
			return null;
		}
	}

	@Override
	public int quantidadeFilmes(PlanetaEntity planeta) {
		planetasResp().forEach(planetaResp -> {
			if (planetaResp.getName().equals(planeta.getName())) {
				planeta.setQuantityFilms(planetaResp.getFilms().size());
				planeta.setFilms(planetaResp.getFilms());
			}
		});
		return planeta.getQuantityFilms();
	}

}
