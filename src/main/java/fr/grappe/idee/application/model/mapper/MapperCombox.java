package fr.grappe.idee.application.model.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.grappe.idee.application.model.dto.CommunDTO;
import fr.grappe.idee.application.model.entity.DomaineEntity;
import fr.grappe.idee.application.model.entity.GrappeEntity;

@Service
public class MapperCombox {

	public List<CommunDTO> mapperDomaine(final List<DomaineEntity> entites) {
		List<CommunDTO> listeRetour = new ArrayList<>();
		for (DomaineEntity domaineEntity : entites) {
			listeRetour.add(this.mapperDomaine(domaineEntity));
		}
		return listeRetour;

	}

	public List<CommunDTO> mapperGrappe(final List<GrappeEntity> entites) {
		List<CommunDTO> listeRetour = new ArrayList<>();
		for (GrappeEntity grappeEntite : entites) {
			listeRetour.add(this.mapperGrappe(grappeEntite));
		}
		return listeRetour;

	}

	public CommunDTO mapperDomaine(final DomaineEntity entite) {
		return this.mapper(entite.getId(), entite.getNom());
	}

	public CommunDTO mapperGrappe(final GrappeEntity entite) {
		return this.mapper(entite.getId(), entite.getNom());
	}

	private CommunDTO mapper(Long id, String nom) {
		CommunDTO dto = new CommunDTO();
		dto.setId(id);
		dto.setNom(nom);

		return dto;
	}
}
