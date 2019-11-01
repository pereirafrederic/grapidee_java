package fr.grapidee.application.services.entite.idee;

import java.util.ArrayList;
import java.util.List;

import fr.grapidee.application.services.commun.dto.CommunDTO;
import fr.grapidee.application.services.entite.grappe.GrappeDTO;

public class IdeeDTO extends CommunDTO {

	private OganisationIdeeBaseDTO organisation;

	private List<GrappeDTO> grappes = new ArrayList<GrappeDTO>();

	public List<GrappeDTO> getGrappes() {
		return grappes;
	}

	public void setGrappes(List<GrappeDTO> grappes) {
		this.grappes = grappes;
	}

	public OganisationIdeeBaseDTO getOrganisation() {
		return organisation;
	}

	public void setOrganisation(OganisationIdeeBaseDTO organisation) {
		this.organisation = organisation;
	}



}
