package br.com.b2wempresas;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.com.b2wempresas.entity.PlanetaEntity;
import br.com.b2wempresas.req.PlanetaReq;
import br.com.b2wempresas.resp.Result;
import br.com.b2wempresas.service.PlanetaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StarwarsApplicationTests {

	@Autowired
	private PlanetaService planetaService;
	RestTemplate restTemplate = new RestTemplate();

	@Test
	public void testaRequisicao() {
		assertEquals("200 OK",
				restTemplate.getForEntity("https://swapi.co/api/planets/?format=json", PlanetaEntity.class)
						.getStatusCode().toString());
	}

	@Test
	public void testaRetorno() {
		Result planetaResp = restTemplate.getForObject("https://swapi.co/api/planets/?format=json&search=Tatooine",
				Result.class);
		assertEquals(5, planetaResp.getResults().get(0).getFilms().size());
	}

	@Test
	public void testaBuscaPorNome() {
		assertEquals("Yavin IV", planetaService.buscaPorNome("Yavin IV").getName());
	}
	
	@Test
	public void testaAdicaoDePlaneta() {
		PlanetaReq planetaReq = new PlanetaReq();
		String string = new Random().toString();
		planetaReq.setName(string);
		planetaService.criaPlaneta(planetaReq);
		PlanetaEntity planetaSalvo = planetaService.buscaPorNome(string);
		assertEquals(string, planetaSalvo.getName());
	}

	@Test
	public void testaBuscaPorId() {
		assertEquals("Yavin IV",
				planetaService.buscaPlanetaPorId(planetaService.buscaPorNome("Yavin IV").get_id()).getName());
	}

}
