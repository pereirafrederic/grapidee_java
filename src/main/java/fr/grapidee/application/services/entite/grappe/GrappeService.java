package fr.grapidee.application.services.entite.grappe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.grapidee.application.services.association.grappe.AssociationGrappeEntity;
import fr.grapidee.application.services.association.grappe.AssociationGrappeRepository;
import fr.grapidee.application.services.association.idee.AssociationIdeeEntity;
import fr.grapidee.application.services.association.idee.AssociationIdeeRepository;
import fr.grapidee.application.services.commun.Options;
import fr.grapidee.application.services.commun.TypeChargement;
import fr.grapidee.application.services.entite.AccueilGrappeDto;

@Service
public class GrappeService {

	@Autowired
	private GrappeRepository repo;

	@Autowired
	private AssociationGrappeRepository repoAssoGrappe;

	@Autowired
	private AssociationIdeeRepository repoAssoIdee;

	@Autowired
	private MapperGrappe mapper;

	public GrappeDTO findById(Long id) {
		return mapper.mappeOne(repo.findById(id).get(), TypeChargement.COMPLET, true);
	}

	public List<Options> findAllOptions() {
		return mapper.mappeAll((List<GrappeEntity>) repo.findAll());
	}

	public AccueilGrappeDto findAll() {
		AccueilGrappeDto retour = new AccueilGrappeDto();

		retour.setOrphelines(recupererGrappesOrphelines());
		retour.setMaitres(recupererGrappesMaitres());
		//
		return retour;
	}
	public List<GrappeDTO> recupererGrappesMaitres() {
		
		return mapper.mappeAll(repo.findMaitres(),
				TypeChargement.ARBRE, true);
	}

	public List<GrappeDTO> recupererGrappesOrphelines() {
		return mapper.mappeAll(repo.findOrphelines(),
				TypeChargement.BASIQUE, false);
	}
	

	public GrappeDTO postOne(GrappeBodyDTO grappeDto) {

		GrappeEntity parent = null;
		if (null != grappeDto.getIdParent()) {
			parent = repo.findById(grappeDto.getIdParent()).get();
		}
		GrappeEntity grappe = mapper.mappeEntity(grappeDto, parent);
		grappe.setOrdre(99);
		repo.save(grappe);

		return mapper.mappeOne(grappe, TypeChargement.BASIQUE, true);
	}

	public GrappeDTO putOne(GrappeBodyDTO grappeDto) {
		GrappeEntity grappeExistante = repo.findById(grappeDto.getId()).get();
		GrappeEntity parent = null;
		if (null != grappeDto.getIdParent()) {
			parent = repo.findById(grappeDto.getIdParent()).get();
		}
		GrappeEntity grappe = mapper.mappeEntity(grappeExistante, grappeDto,
				parent);
		grappe = repo.save(grappe);
		return mapper.mappeOne(grappe, TypeChargement.BASIQUE, true);
	}

	public GrappeDTO deleteOne(GrappeBodyDTO grappeDto) {
		GrappeEntity grappeExistante = repo.findById(grappeDto.getId()).get();
		GrappeEntity parent = repo.findById(grappeDto.getIdParent()).get();

		List<AssociationGrappeEntity> listeAssoGrappe = repoAssoGrappe
				.findByGrappe(grappeExistante);
		for (AssociationGrappeEntity associationGrappeEntity : listeAssoGrappe) {
			associationGrappeEntity.setGrappe(parent);
			repoAssoGrappe.save(associationGrappeEntity);
		}
		List<AssociationIdeeEntity> listeAssoIdee = repoAssoIdee
				.findByGrappe(grappeExistante);
		for (AssociationIdeeEntity associationIdeeEntity : listeAssoIdee) {
			associationIdeeEntity.setGrappe(parent);
			repoAssoIdee.save(associationIdeeEntity);
		}
		repo.delete(grappeExistante);
		return mapper.mappeOne(repo.findById(grappeDto.getIdParent()).get(),
				TypeChargement.BASIQUE, true);
	}


	

}
