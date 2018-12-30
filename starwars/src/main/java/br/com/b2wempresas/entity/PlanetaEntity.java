package br.com.b2wempresas.entity;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "starwars")
public class PlanetaEntity {

	@Id
	private ObjectId _id;

	@NotNull
	@Indexed(unique = true)
	@NotBlank
	@JsonProperty("name")
	private String name;

	@NotBlank
	@NotNull
	@JsonProperty("climate")
	private String climate;

	@NotBlank
	@NotNull
	@JsonProperty("terrain")
	private String terrain;

	@JsonProperty("films")
	private List<String> films;

	@JsonProperty("quantityFilms")
	private int quantityFilms;

	public int getQuantityFilms() {
		return quantityFilms;
	}

	public void setQuantityFilms(int quantityFilms) {
		this.quantityFilms = quantityFilms;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}

	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
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

	@Override
	public String toString() {
		return "PlanetaEntity [_id=" + _id + ", name=" + name + ", climate=" + climate + ", terrain=" + terrain
				+ ", films=" + films + "]";
	}

}
