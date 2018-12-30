package br.com.b2wempresas.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2wempresas.entity.PlanetaEntity;
import br.com.b2wempresas.req.PlanetaReq;
import br.com.b2wempresas.resp.PlanetaResp;
import br.com.b2wempresas.service.PlanetaService;

@RestController
@RequestMapping("api")
public class PlanetaController {

	public static final Logger logger = LoggerFactory.getLogger(PlanetaController.class);

	@Autowired
	private PlanetaService planetaService;

	/**
	 * Retorna todos os planetas na base de dados e faz uma requisição para a API
	 * que retorna a quantidade de filmes.
	 * 
	 * @return
	 */
	@GetMapping("planetas")
	public ResponseEntity<List<PlanetaResp>> buscaPlanetas() {
		logger.info("Buscando todos os planetas da base de dados");
		List<PlanetaResp> planetasResp = new ArrayList<>();
		List<PlanetaEntity> listaPlanetas = planetaService.buscaPlanetas();
		if (listaPlanetas == null) {
			logger.error("Nenhum planeta foi encontrado.");
			return new ResponseEntity<List<PlanetaResp>>(HttpStatus.NOT_FOUND);
		}
		listaPlanetas.forEach(planeta -> {
			PlanetaResp planetaResp = new PlanetaResp();
			BeanUtils.copyProperties(planeta, planetaResp);
			planetasResp.add(planetaResp);
		});
		return new ResponseEntity<List<PlanetaResp>>(planetasResp, HttpStatus.OK);
	}

	/**
	 * Adiciona o 1 planeta à base de dados
	 * 
	 * @param planetaEntity
	 * @return
	 */
	@PostMapping("planeta")
	public ResponseEntity<PlanetaResp> criaPlaneta(@RequestBody PlanetaReq planetaReq) {
		logger.info("Salvar planeta : {}", planetaReq);
		PlanetaResp planetaResp = new PlanetaResp();
		if (planetaReq.getName() != null) {
			logger.error("O nome do {} planeta já está sendo usado", planetaReq.getName());
			return new ResponseEntity<PlanetaResp>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		planetaService.criaPlaneta(planetaReq);
		BeanUtils.copyProperties(planetaService.buscaPorNome(planetaReq.getName()), planetaResp);
		return new ResponseEntity<PlanetaResp>(planetaResp, HttpStatus.CREATED);
	}

	/**
	 * Busca um planeta da base de dados, através de seu ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("planeta/{id}")
	public ResponseEntity<PlanetaResp> buscaPlaneta(@PathVariable("id") String id) {
		logger.info("Buscando planeta pelo id {}", id);
		PlanetaEntity planetaEntity = planetaService.buscaPlanetaPorId(id);
		if (planetaEntity == null) {
			logger.error("Planeta de id {} não encontrado", id);
			return new ResponseEntity<PlanetaResp>(HttpStatus.NOT_FOUND);
		}
		PlanetaResp planetaResp = new PlanetaResp();
		BeanUtils.copyProperties(planetaEntity, planetaResp);
		return new ResponseEntity<PlanetaResp>(planetaResp, HttpStatus.OK);
	}

	/**
	 * Busca dentro da base de dados, um planeta com o nome no parâmetro
	 * 
	 * @param nome
	 * @return
	 */
	@GetMapping("planeta/busca={nome}")
	public ResponseEntity<PlanetaResp> buscaNome(@PathVariable("nome") String nome) {
		logger.info("Buscando planeta de nome: {}", nome);
		PlanetaResp planetaResp = new PlanetaResp();
		try {
			PlanetaEntity planetaBuscado = planetaService.buscaPorNome(nome);
			BeanUtils.copyProperties(planetaBuscado, planetaResp);
			return new ResponseEntity<PlanetaResp>(planetaResp, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Nome {} não encontrado dentre os planetas na base de dados", nome);
			return new ResponseEntity<PlanetaResp>(HttpStatus.NOT_FOUND);		}
	}

	/**
	 * Remove um planeta da base de dados, conforme seu ID
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("planeta/{id}")
	public ResponseEntity<PlanetaEntity> deletaPlaneta(@PathVariable("id") String id) {
		logger.info("Deletar planeta de id {}", id);
		PlanetaEntity planetaDeletado = planetaService.buscaPlanetaPorId(id);
		if (planetaDeletado == null) {
			logger.error("Planeta não encontrado. Consulta do id {}", id);
			return new ResponseEntity<PlanetaEntity>(HttpStatus.NOT_FOUND);
		}
		planetaService.deletaPlaneta(planetaDeletado);
		PlanetaResp planetaResp = new PlanetaResp();
		BeanUtils.copyProperties(planetaDeletado, planetaResp);
		return new ResponseEntity<PlanetaEntity>(planetaDeletado, HttpStatus.OK);
	}

}
