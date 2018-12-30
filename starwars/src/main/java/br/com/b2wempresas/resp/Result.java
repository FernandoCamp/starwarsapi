package br.com.b2wempresas.resp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.b2wempresas.entity.PlanetaEntity;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "count", "next", "previous", "results" })
public class Result {

	@JsonProperty("count")
	private Integer count;
	@JsonProperty("next")
	private String next;
	@JsonProperty("previous")
	private Object previous;
	@JsonProperty("results")
	private List<PlanetaEntity> planetaEntity = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Result() {
	}

	/**
	 * 
	 * @param results
	 * @param previous
	 * @param count
	 * @param next
	 */
	public Result(Integer count, String next, Object previous, List<PlanetaEntity> planetaEntity) {
		super();
		this.count = count;
		this.next = next;
		this.previous = previous;
		this.planetaEntity = planetaEntity;
	}

	@JsonProperty("count")
	public Integer getCount() {
		return count;
	}

	@JsonProperty("count")
	public void setCount(Integer count) {
		this.count = count;
	}

	@JsonProperty("next")
	public String getNext() {
		return next;
	}

	@JsonProperty("next")
	public void setNext(String next) {
		this.next = next;
	}

	@JsonProperty("previous")
	public Object getPrevious() {
		return previous;
	}

	@JsonProperty("previous")
	public void setPrevious(Object previous) {
		this.previous = previous;
	}

	@JsonProperty("results")
	public List<PlanetaEntity> getResults() {
		return planetaEntity;
	}

	@JsonProperty("results")
	public void setResults(List<PlanetaEntity> planetaEntity) {
		this.planetaEntity = planetaEntity;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}
