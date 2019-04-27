package fr.grapidee.application.services.entite.idee;

import fr.grapidee.application.services.commun.CommunBodyDTO;


public class IdeeBodyDTO extends CommunBodyDTO {

	private String liaison;
	private Long idGrappe;

	private Long idIdeeMaitre;
	private Long idGrappeAsso;

	public String getLiaison() {
		return liaison;
	}

	public void setLiaison(String liaison) {
		this.liaison = liaison;
	}

	public Long getIdGrappe() {
		return idGrappe;
	}

	public void setIdGrappe(Long idGrappe) {
		this.idGrappe = idGrappe;
	}

	public Long getIdIdeeMaitre() {
		return idIdeeMaitre;
	}

	public void setIdIdeeMaitre(Long idIdeeMaitre) {
		this.idIdeeMaitre = idIdeeMaitre;
	}

	public Long getIdGrappeAsso() {
		return idGrappeAsso;
	}

	public void setIdGrappeAsso(Long idGrappeAsso) {
		this.idGrappeAsso = idGrappeAsso;
	}

}
