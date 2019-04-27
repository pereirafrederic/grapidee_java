package fr.grapidee.application.services.entite.grappe;

import java.util.ArrayList;
import java.util.List;

import fr.grapidee.application.services.commun.dto.CommunDTO;
import fr.grapidee.application.services.entite.idee.IdeeDTO;

public class GrappeDTO extends CommunDTO {

	private List<IdeeDTO> Idees = null;

	private GrappeDTO grappeParent = null;

	private List<GrappeDTO> grappeEnfants = null;

	private String type;

	public List<IdeeDTO> getIdees() {
		return Idees;
	}

	public void setIdees(List<IdeeDTO> idees) {
		Idees = idees;
	}

	public GrappeDTO getGrappeParent() {
		return grappeParent;
	}

	public void setGrappeParent(GrappeDTO grappeParent) {
		this.grappeParent = grappeParent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<GrappeDTO> getGrappeEnfants() {
		return grappeEnfants;
	}

	public void setGrappeEnfants(List<GrappeDTO> grappeEnfants) {
		this.grappeEnfants = grappeEnfants;
	}

}
