package br.com.b2wempresas.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parametrizacao")
public class ParametrizacaoEntity {
	
	@Id
	private ObjectId _id;
	
	@NotNull
	@NotBlank
	private String key;
	
	@NotNull
	@NotBlank
	private String value;
	
	@NotNull
	@NotBlank
	private String description;
	
	@NotNull
	@NotBlank
	private boolean enabled;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
