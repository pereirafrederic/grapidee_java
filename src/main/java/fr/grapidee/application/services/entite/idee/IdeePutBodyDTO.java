package fr.grapidee.application.services.entite.idee;

import fr.grapidee.application.services.commun.CommunBodyDTO;

public class IdeePutBodyDTO extends CommunBodyDTO {

	private String liaison;

	private Long idGrappe;
	private Long idGrappeAncienne;

	private Long idIdeeMaitre;
	private Long idIdeeMaitreAncienne;

	private Long idGrappeAsso;
	private Long idGrappeAssoAncienne;

	public Long getIdGrappeAncienne() {
		return idGrappeAncienne;
	}

	public void setIdGrappeAncienne(Long idGrappeAncienne) {
		this.idGrappeAncienne = idGrappeAncienne;
	}

	public Long getIdIdeeMaitreAncienne() {
		return idIdeeMaitreAncienne;
	}

	public void setIdIdeeMaitreAncienne(Long idIdeeMaitreAncienne) {
		this.idIdeeMaitreAncienne = idIdeeMaitreAncienne;
	}

	public Long getIdGrappeAssoAncienne() {
		return idGrappeAssoAncienne;
	}

	public void setIdGrappeAssoAncienne(Long idGrappeAssoAncienne) {
		this.idGrappeAssoAncienne = idGrappeAssoAncienne;
	}

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
