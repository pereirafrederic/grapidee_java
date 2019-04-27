package fr.grapidee.application.services.commun;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CommunEntity extends CommunLightEntity {

	@Column(length = 1000)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
