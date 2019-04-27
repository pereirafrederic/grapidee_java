package fr.grapidee.application.services.entite.idee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.grapidee.application.services.association.idee.IdeeEsclaveDTO;
import fr.grapidee.application.services.commun.dto.CommunDTO;

public class OganisationIdeeBaseDTO {

	private Map<Long, OganisationIdeeDTO> mapIdees = new HashMap<Long, OganisationIdeeDTO>();
	private List<IdeeEsclaveDTO> ideeEsclaves = new ArrayList<IdeeEsclaveDTO>();
	private List<OganisationIdeeDTO> organisationFilles = new ArrayList<OganisationIdeeDTO>();

	public Map<Long, OganisationIdeeDTO> getMapIdees() {
		return mapIdees;
	}

	public void setMapIdees(Map<Long, OganisationIdeeDTO> mapIdees) {
		this.mapIdees = mapIdees;
	}



	public List<IdeeEsclaveDTO> getIdeeEsclaves() {
		return ideeEsclaves;
	}

	public void setIdeeEsclaves(List<IdeeEsclaveDTO> ideeEsclaves) {
		this.ideeEsclaves = ideeEsclaves;
	}

	public List<OganisationIdeeDTO> getOrganisationFilles() {
		return organisationFilles;
	}

	public void setOrganisationFilles(List<OganisationIdeeDTO> organisationFilles) {
		this.organisationFilles = organisationFilles;
	}

}
