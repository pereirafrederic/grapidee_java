package fr.grapidee.application.services.commun;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CommunLightEntity extends AbstractId {

	@Column(nullable = false, length = 100)
	private String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
