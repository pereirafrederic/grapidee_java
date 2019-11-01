package fr.grapidee.application.services.entite.grappe;

import fr.grapidee.application.services.commun.CommunBodyDTO;

public class GrappeBodyDTO extends CommunBodyDTO {
	private Long idParent;

	public Long getIdParent() {
		return idParent;
	}

	public void setIdParent(Long idParent) {
		this.idParent = idParent;
	}

}
