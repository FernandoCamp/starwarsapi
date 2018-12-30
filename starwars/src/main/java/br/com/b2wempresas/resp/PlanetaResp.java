package br.com.b2wempresas.resp;

public class PlanetaResp {
	
	private String name;
	
	private String climate;
	
	private String terrain;
	
	private int quantityFilms;

	public int getQuantityFilms() {
		return quantityFilms;
	}

	public void setQuantityFilms(int quantityFilms) {
		this.quantityFilms = quantityFilms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	
}
