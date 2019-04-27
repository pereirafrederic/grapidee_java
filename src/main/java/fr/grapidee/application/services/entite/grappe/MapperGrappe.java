package fr.grapidee.application.services.entite.grappe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.grapidee.application.services.association.grappe.AssociationGrappeEntity;
import fr.grapidee.application.services.commun.CommunBodyDTO;
import fr.grapidee.application.services.commun.Options;
import fr.grapidee.application.services.commun.TypeChargement;
import fr.grapidee.application.services.commun.dto.CommunDTO;
import fr.grapidee.application.services.entite.idee.IdeeDTO;
import fr.grapidee.application.services.entite.idee.MapperIdee;
import fr.grapidee.application.utilitaire.ConstanteIdee;
import fr.grapidee.application.utilitaire.comparator.ComparatorSort;

@Service
public class MapperGrappe {

	@Autowired
	private MapperIdee mapperIdee;

	class OrdreComparator implements Comparator<GrappeEntity> {
		@Override
		public int compare(GrappeEntity a, GrappeEntity b) {
			return a.getOrdre() < b.getOrdre() ? -1 : a.getOrdre() == b
					.getOrdre() ? 0 : 1;
		}
	}

	public GrappeDTO mappeOne(GrappeEntity entity,
			TypeChargement typeChargement, boolean needParent) {
//		System.out.println("mappe MapperGrappe - grappe " + ""
//				+ entity.getNom());
		GrappeDTO retour = mappeBase(entity);

		if (TypeChargement.estAuMoins(typeChargement, TypeChargement.BASIQUE)) {
			if (null != entity.getListeEnfants()
					&& entity.getListeEnfants().size() > 0) {
				retour.setGrappeEnfants(mappeAll(
						entity.getListeEnfants().stream()
								.sorted(new OrdreComparator())
								.collect(Collectors.toList()), typeChargement,
						false));
			}
		}
		if (needParent) {
			if (null != entity.getGrappeParent()) {
				retour.setGrappeParent(mappeParent(entity.getGrappeParent()));
			}
		}
		
		if(TypeChargement.estAuMoins(typeChargement,
						TypeChargement.ARBRE)){
			retour.setIdees(mapperIdee.mappeAssoIdees(
					entity.getListeAssoIdee(),
					ConstanteIdee.NBR_NIVEAU_IDEE_GRAPPE));
		}
		if (TypeChargement.estAuMoins(typeChargement, TypeChargement.COMPLET)) {
			if (null != entity.getListeAssoIdee()
					&& entity.getListeAssoIdee().size() > 0) {
				retour.setIdees(mapperIdee.mappeAssoIdees(
						entity.getListeAssoIdee(),
						ConstanteIdee.NBRE_NIVEAU_IDEE));
			}
		}

		return retour;
	}

	public GrappeDTO mappeBase(GrappeEntity entity) {
		  GrappeDTO retour = new GrappeDTO();
		retour.setId(entity.getId());
		retour.setNom(entity.getNom());
		retour.setDescription(entity.getDescription());
		if (null != entity.getType()) {
			retour.setType(entity.getType().getNom());
		}
		return retour;
	}

	private GrappeDTO mappeParent(GrappeEntity grappeParent) {
//		System.out.println("mappe mappeParent - grappe "
//				+ grappeParent.getNom());
		GrappeDTO retour  = mappeBase(grappeParent);
		if (null != grappeParent.getGrappeParent()) {
//			System.out.println("mappe mappeParent - grappe "
//					+ grappeParent.getGrappeParent().getNom());
			retour.setGrappeParent(mappeParent(grappeParent.getGrappeParent()));
		}
		return retour;
	}

	public List<GrappeDTO> mappeAll(Iterable<GrappeEntity> entitys,
			TypeChargement typeChargement, boolean needParent) {
		List<GrappeDTO> retour = new ArrayList<GrappeDTO>();
		for (GrappeEntity entity : entitys) {
			retour.add(mappeOne(entity, typeChargement, needParent));
		}
		return retour;
	}

	public List<Options> mappeAll(List<GrappeEntity> entitys) {
		List<Options> retour = new ArrayList<Options>();
		for (GrappeEntity entity : ComparatorSort.getGrappeSortByNom(entitys)) {
			retour.add(mappeOption(entity));
		}
		return retour;
	}

	private Options mappeOption(GrappeEntity entity) {
		Options retour = new Options();
		retour.setValue(entity.getId());
		retour.setLabel(entity.getNom());

		return retour;
	}

	public List<GrappeDTO> mappeAssoGrappes(
			List<AssociationGrappeEntity> entitys) {
		List<GrappeDTO> retour = new ArrayList<GrappeDTO>();
		entitys.forEach(entity -> retour.add(mappeAssoGrappe(entity)));
		return retour;
	}

	private GrappeDTO mappeAssoGrappe(AssociationGrappeEntity entity) {
		GrappeEntity grappe = entity.getGrappe();
//		System.out.println("mappeAssoGrappe" + grappe.getNom());
		GrappeDTO retour =mappeBase(grappe);
		if (null != grappe.getGrappeParent()) {
			retour.setGrappeParent(mappeParent(grappe.getGrappeParent()));
		}

		return retour;
	}

	public GrappeEntity mappeEntity(CommunBodyDTO grappeDto, GrappeEntity parent) {
		GrappeEntity entity = new GrappeEntity();
		entity.setNom(grappeDto.getNom());
		entity.setDescription(grappeDto.getDescription());
		entity.setGrappeParent(parent);
		return entity;
	}

	public GrappeEntity mappeEntity(GrappeEntity grappeExistante,
			CommunBodyDTO grappeDto, GrappeEntity parent) {
		grappeExistante.setNom(grappeDto.getNom());
		grappeExistante.setDescription(grappeDto.getDescription());
		grappeExistante.setGrappeParent(parent);
		return grappeExistante;
	}

}

