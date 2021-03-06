package fr.grapidee.application.services.associationIdee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.grapidee.application.services.grappe.GrappeEntity;
import fr.grapidee.application.services.grappe.GrappeRepository;
import fr.grapidee.application.services.idee.IdeeDTO;
import fr.grapidee.application.services.idee.IdeeEntity;
import fr.grapidee.application.services.idee.IdeeRepository;
import fr.grapidee.application.services.idee.IdeeService;

@Service
public class AssoService {

	@Autowired
	private AssociationRepository repo;

	@Autowired
	private GrappeRepository grappeRepo;

	@Autowired
	private IdeeRepository ideeRepo;

	@Autowired
	private IdeeService ideeService;

	
	@Transactional(propagation = Propagation.MANDATORY)
	public AssoBodyDTO putOne(AssoBodyDTO asso) {
		AssociationEntity entity = repo.findOne(asso.getId());

		return mapperAndSaveAndMapper(entity, asso);
	}

	
	@Transactional(propagation = Propagation.MANDATORY)
	public AssoBodyDTO postOne(AssoBodyDTO asso) throws Exception {

		IdeeEntity ideeMaitre = ideeRepo.findOne(asso.getIdMaitre());
		IdeeEntity ideeEsclave = ideeRepo.findOne(asso.getIdEsclave());
		List<AssociationEntity> listeDejaPresent = repo.findByIdeeMaitreAndIdeeEsclave(ideeMaitre, ideeEsclave);
		if (listeDejaPresent.isEmpty()) {
			AssociationEntity entity = new AssociationEntity();
			entity.setGrappe(grappeRepo.findOne(asso.getIdGrappe()));
			entity.setIdeeMaitre(ideeMaitre);
			entity.setIdeeEsclave(ideeEsclave);
			repo.save(entity);
			return mappperDto(entity);

		} else {
			throw new Exception("L'association existe déjà.");
		}

	}

	private AssoBodyDTO mapperAndSaveAndMapper(AssociationEntity entity, AssoBodyDTO asso) {
		entity.setGrappe(grappeRepo.findOne(asso.getIdGrappe()));
		entity.setIdeeMaitre(ideeRepo.findOne(asso.getIdMaitre()));
		entity.setIdeeEsclave(ideeRepo.findOne(asso.getIdEsclave()));
		AssociationEntity retourEntity = repo.save(entity);
		return mappperDto(retourEntity);
	}

	private AssoBodyDTO mappperDto(AssociationEntity retourEntity) {
		AssoBodyDTO retour = new AssoBodyDTO();
		retour.setId(retourEntity.getId());
		retour.setIdMaitre(retourEntity.getIdeeMaitre().getId());
		retour.setIdEsclave(retourEntity.getIdeeEsclave().getId());
		retour.setIdGrappe(retourEntity.getGrappe().getId());
		return retour;
	}

	
	@Transactional(propagation = Propagation.MANDATORY)
	public AssoBodyDTO creerAssoIdee(AssoIdeeBodyDTO assoIdee) throws Exception {

		// traitement de l'idée
		if (null != assoIdee.getIdee()) {
			IdeeDTO retourIdee = ideeService.putOne(assoIdee.getIdee());
			assoIdee.setIdEsclave(retourIdee.getId());
			return this.postOne(assoIdee);

		} else if (null != assoIdee.getIdEsclave()) {
			return this.postOne(assoIdee);
		} else {
			throw new Exception("L'association est impossible sans idee.");
		}
	}

	
	public AssoBodyDTO injecterAsso(String nomMaitre, String nomEsclave, String nomGrappe) throws Exception {

		AssoBodyDTO dto = new AssoBodyDTO();
		List<IdeeEntity> listeMaitre = ideeRepo.findByNom(nomMaitre);
		if (listeMaitre.isEmpty()) {
			throw new Exception("L'idée maitre est inconnue");
		}
		Long idMaitre = listeMaitre.get(0).getId();
		dto.setIdMaitre(idMaitre);

		List<IdeeEntity> listeEsclave = ideeRepo.findByNom(nomEsclave);
		if (listeEsclave.isEmpty()) {
			throw new Exception("L'idée esclave est inconnue");
		}
		Long idEsclave = listeEsclave.get(0).getId();
		dto.setIdEsclave(idEsclave);
		List<GrappeEntity> listeGrappe = grappeRepo.findByNom(nomGrappe);
		if (listeGrappe.isEmpty()) {
			throw new Exception("La grappe est inconnue");
		}
		Long idGrappe = listeGrappe.get(0).getId();
		dto.setIdGrappe(idGrappe);

		return postOne(dto);
	}

}
