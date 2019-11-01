package fr.grapidee.application.services.association.idee;

import fr.grapidee.application.services.entite.grappe.GrappeDTO;
import fr.grapidee.application.services.entite.idee.IdeeDTO;

public class IdeeEsclaveDTO extends IdeeDTO {

	private String liaison;

	private GrappeDTO grappe;

	public GrappeDTO getGrappe() {
		return grappe;
	}

	public void setGrappe(GrappeDTO grappe) {
		this.grappe = grappe;
	}

	public String getLiaison() {
		return liaison;
	}

	public void setLiaison(String liaison) {
		this.liaison = liaison;
	}

}
