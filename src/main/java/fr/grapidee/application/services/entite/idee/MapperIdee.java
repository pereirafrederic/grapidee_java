package fr.grapidee.application.services.entite.idee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.grapidee.application.services.association.grappe.AssociationGrappeEntity;
import fr.grapidee.application.services.association.idee.AssociationIdeeEntity;
import fr.grapidee.application.services.association.idee.IdeeEsclaveDTO;
import fr.grapidee.application.services.commun.CommunBodyDTO;
import fr.grapidee.application.services.commun.Options;
import fr.grapidee.application.services.entite.grappe.GrappeDTO;
import fr.grapidee.application.services.entite.grappe.MapperGrappe;

@Service
public class MapperIdee {

	@Autowired
	private MapperGrappe mapperGrappe;

	public IdeeDTO mappeOne(IdeeEntity entity, int niveau) {
		System.out.println("mappe one - " + entity.getId() + " - "
				+ entity.getNom());

		IdeeDTO retour = new IdeeDTO();
		mappeBase(retour, entity);

		if (niveau > 1) {
			if (niveau > 3) {

				if (null != entity.getListeAssoGrappe()
						&& entity.getListeAssoGrappe().size() > 0) {
					retour.setGrappes(mapperGrappe.mappeAssoGrappes(entity
							.getListeAssoGrappe()));
				}
			}
			if (niveau > 2) {

				if (null != entity.getListeAssoIdee()
						&& entity.getListeAssoIdee().size() > 0) {
					retour.setOrganisation(mappeEsclaves(entity.getListeAssoIdee(),
							niveau - 1));
				}
			}

		}
		return retour;
	}

	private OganisationIdeeBaseDTO mappeEsclaves(
			List<AssociationIdeeEntity> entitys, int niveau) {

		OganisationIdeeBaseDTO organisation = new OganisationIdeeBaseDTO();
		for (AssociationIdeeEntity entity : entitys) {

			IdeeEsclaveDTO idee = mappeEsclave(entity, niveau);
			System.out.println("mappe mappeEsclaves - " + idee.getNom()
					+ " niveau " + niveau);

			if (null == entity.getGrappe()
					&& (entity.getIdeeEsclave().getListeAssoGrappe() == null || entity
							.getIdeeEsclave().getListeAssoGrappe().isEmpty())) {
				organisation.getIdeeEsclaves().add(idee);
			} else {
				GrappeDTO grappeparent = null;
				GrappeDTO grappeFille = null;
				if (null == entity.getGrappe()) {
					if (null != entity.getIdeeEsclave().getListeAssoGrappe()
							&& null != entity.getIdeeEsclave()
									.getListeAssoGrappe().get(0)
							&& null != entity.getIdeeEsclave()
									.getListeAssoGrappe().get(0).getGrappe()) {
						grappeFille = mapperGrappe.mappeBase(entity
								.getIdeeEsclave().getListeAssoGrappe().get(0)
								.getGrappe());

						if (null != entity.getIdeeEsclave()
								.getListeAssoGrappe().get(0).getGrappe()
								.getGrappeParent()) {
							grappeparent = mapperGrappe.mappeBase(entity
									.getIdeeEsclave().getListeAssoGrappe()
									.get(0).getGrappe().getGrappeParent());
						}
					}

				} else {
					grappeFille = mapperGrappe.mappeBase(entity.getGrappe());
					if (null != entity.getGrappe().getGrappeParent()) {
						grappeparent = mapperGrappe.mappeBase(entity
								.getGrappe().getGrappeParent());
					}
				}
				if (grappeFille == null) {
					organisation.getIdeeEsclaves().add(idee);
				} else {
					System.out.println("recherche de " + grappeFille.getNom());

					OganisationIdeeDTO orgaRecherche = mapperOrganisationBase(grappeFille);
							if(organisation.getMapIdees().containsKey(
							grappeFille.getId())){
								orgaRecherche = organisation.getMapIdees().get(grappeFille.getId());
							}
							
					orgaRecherche.getIdeeEsclaves().add(idee);

					if (grappeparent != null){
					if (organisation.getMapIdees().containsKey(
							grappeparent.getId())) {
						OganisationIdeeDTO orgaParentTrouve = organisation
								.getMapIdees().get(grappeparent.getId());
						if (orgaParentTrouve.getMapIdees().containsKey(
								grappeFille.getId())) {
							// il faut ajouter l'idée dans la fille déjà
							// présente
							OganisationIdeeDTO orgaFilleTrouve = orgaParentTrouve
									.getMapIdees().get(grappeFille.getId());
							orgaFilleTrouve.getIdeeEsclaves().add(idee);
						} else {
							// il faut ajouter la fille avec l'idée
							orgaParentTrouve.getMapIdees().put(
									orgaRecherche.getId(), orgaRecherche);
						}

					} else {
						// faut ajouter le parent et la fille avec l'idée
						OganisationIdeeDTO orgaParent = mapperOrganisationBase(grappeparent);
						orgaParent.getMapIdees().put(orgaRecherche.getId(),
								orgaRecherche);
						organisation.getMapIdees().put(orgaParent.getId(),
								orgaParent);
					}
					}else{
						organisation.getMapIdees().put(orgaRecherche.getId(),
								orgaRecherche);
					}

				}

			}

		}

		for (Long idGrappeParent : organisation.getMapIdees().keySet()) {
			OganisationIdeeDTO grappParent = organisation.getMapIdees().get(idGrappeParent);
			for (Long idGrappeFille : grappParent.getMapIdees().keySet()) {
				OganisationIdeeDTO grappeFille = grappParent.getMapIdees().get(idGrappeFille);
				grappParent.getGrappes().add(grappeFille);
			}
			grappParent.getMapIdees().clear();
			organisation.getOrganisationFilles().add(grappParent);
		}
		organisation.getMapIdees().clear();
		return organisation ;
	}

	private void nettoyerOrganisationIdee(OganisationIdeeDTO organisationIdee) {
		// System.out.println("avant nettoyage nettoyerOrganisationIdee - "
		// + organisationIdee.getNom() + "avec "
		// + organisationIdee.getMapIdees().size());
		if (organisationIdee.getMapIdees().size() > 0) {
			int i = 1;
			for (OganisationIdeeDTO organisationIdeefille : organisationIdee
					.getMapIdees().values()) {
				// System.out.println("---FILLE" + i);
				// System.out.println("nettoyerOrganisationIdee fille - "
				// + organisationIdeefille.getNom() + "avec "
				// + organisationIdee.getMapIdees().size());
				nettoyerOrganisationIdee(organisationIdeefille);
				organisationIdee.getGrappes().add(organisationIdeefille);
				i++;
			}
		}

		organisationIdee.getMapIdees().clear();
		// System.out.println("apres nettoyage nettoyerOrganisationIdee - "
		// + organisationIdee.getNom() + "avec "
		// + organisationIdee.getMapIdees().size());
	}

	private OganisationIdeeDTO mappeOrganisation(GrappeDTO grappe,
			OganisationIdeeDTO orgaSuperieur, IdeeEsclaveDTO idee) {

		OganisationIdeeDTO orga = null;
		OganisationIdeeDTO orgaBase = mapperOrganisationBase(grappe);
		if (orgaSuperieur != null) {
			orgaSuperieur.getMapIdees().put(grappe.getId(), orgaBase);
		}

		if (null != grappe.getGrappeParent()) {

			orga = mappeOrganisation(grappe.getGrappeParent(), orgaBase, idee);
		} else {
			orgaBase.getIdeeEsclaves().add(idee);
			orga = orgaBase;

		}

		return orga;
	}

	private GrappeDTO getGrappeInverse(GrappeDTO grappe, GrappeDTO grappeFille,
			boolean stop) {

		GrappeDTO magrappe = new GrappeDTO();

		if (!stop && grappe.getGrappeParent() != null) {

			GrappeDTO grappeParent = grappe.getGrappeParent();
			grappe.setGrappeParent(grappeFille);
			magrappe = getGrappeInverse(grappeParent, grappe, true);

		} else {
			grappe.setGrappeParent(grappeFille);
			magrappe = grappe;

		}
		return magrappe;
	}

	private OganisationIdeeDTO mapperOrganisationBase(GrappeDTO grappe) {
		OganisationIdeeDTO orgafille;
		orgafille = new OganisationIdeeDTO();
		orgafille.setId(grappe.getId());
		orgafille.setNom(grappe.getNom());
		orgafille.setDescription(grappe.getDescription());
		return orgafille;
	}

	private IdeeEsclaveDTO mappeEsclave(AssociationIdeeEntity entity, int niveau) {
		IdeeEsclaveDTO retour = new IdeeEsclaveDTO();
		if (null != entity.getLiaison()) {
			retour.setLiaison(entity.getLiaison());
		}
		IdeeDTO ideeEsclave = mappeOne(entity.getIdeeEsclave(), niveau);
		mapperIdeeDtoToIdeeEsclave(retour, ideeEsclave);
		return retour;
	}

	private void mapperIdeeDtoToIdeeEsclave(IdeeEsclaveDTO retour,
			IdeeDTO ideeEsclave) {
		retour.setId(ideeEsclave.getId());
		retour.setNom(ideeEsclave.getNom());
		retour.setDescription(ideeEsclave.getDescription());
		retour.setGrappes(ideeEsclave.getGrappes());
		retour.setOrganisation(ideeEsclave.getOrganisation());
	}

	public List<IdeeDTO> mappeAll(Iterable<IdeeEntity> entitys, int nbreNiveau) {
		List<IdeeDTO> retour = new ArrayList<IdeeDTO>();
		entitys.forEach(entity -> retour.add(mappeOne(entity, nbreNiveau)));
		return retour;
	}

	private void mappeBase(IdeeDTO retour, IdeeEntity entity) {
		retour.setId(entity.getId());
		retour.setNom(entity.getNom());
		retour.setDescription(entity.getDescription());

	}

	public List<IdeeDTO> mappeAssoIdees(List<AssociationGrappeEntity> entitys,
			int nbrNiveauIdee) {
		List<IdeeDTO> retour = new ArrayList<IdeeDTO>();
		for (AssociationGrappeEntity entity : entitys) {
			retour.add(mappeAssoIdee(entity, nbrNiveauIdee));
		}

		return retour;
	}

	private IdeeDTO mappeAssoIdee(AssociationGrappeEntity entity,
			int nbrNiveauIdee) {
		return mappeOne(entity.getIdee(), nbrNiveauIdee);
	}

	public IdeeEntity mappeEntity(CommunBodyDTO ideeDto) {
		IdeeEntity idee = new IdeeEntity();
		idee.setNom(ideeDto.getNom());
		idee.setDescription(ideeDto.getDescription());
		return idee;

	}

	public IdeeEntity mappeEntity(IdeeEntity ideeExistante,
			IdeePutBodyDTO ideeDto) {
		ideeExistante.setNom(ideeDto.getNom());
		ideeExistante.setDescription(ideeDto.getDescription());
		return ideeExistante;
	}

	public List<Options> mappeAllOptions(List<IdeeEntity> all) {
		List<Options> retour = new ArrayList<Options>();
		all.stream().forEach( idee -> retour.add(mappeOption(idee)));
		
		return retour;
	}

	private Options mappeOption(IdeeEntity entity) {
		Options retour = new Options();
		retour.setValue(entity.getId());
		if(entity.getListeAssoGrappe() != null &&entity.getListeAssoGrappe().size()>0){
			retour.setLabel(entity.getNom()+" [" +entity.getListeAssoGrappe().get(0).getGrappe().getNom() +"]" );	
		}else
		{
		retour.setLabel(entity.getNom() );
		}
		return retour;
	}
}
