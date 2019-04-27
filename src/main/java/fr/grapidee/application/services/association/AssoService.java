package fr.grapidee.application.services.association;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.grapidee.application.services.association.grappe.AssociationGrappeEntity;
import fr.grapidee.application.services.association.grappe.AssociationGrappeRepository;
import fr.grapidee.application.services.association.idee.AssociationIdeeEntity;
import fr.grapidee.application.services.association.idee.AssociationIdeeRepository;
import fr.grapidee.application.services.entite.grappe.GrappeEntity;
import fr.grapidee.application.services.entite.grappe.GrappeRepository;
import fr.grapidee.application.services.entite.idee.IdeeDTO;
import fr.grapidee.application.services.entite.idee.IdeeEntity;
import fr.grapidee.application.services.entite.idee.IdeeRepository;

@Service
public class AssoService {

	@Autowired
	private AssociationIdeeRepository repoAssoIdee;

	@Autowired
	private AssociationGrappeRepository repoAssoGrappe;

	@Autowired
	private IdeeRepository repoIdee;

	@Autowired
	private GrappeRepository repoGrappe;

	public AssociationIdeeEntity associerIdee(IdeeEntity idee, Long idParent,
			String liaison, Long idGrappe) {

		AssociationIdeeEntity asso = new AssociationIdeeEntity();
		asso.setIdeeMaitre(repoIdee.findOne(idParent));
		asso.setIdeeEsclave(idee);
		if (null != idGrappe) {
			asso.setGrappe(repoGrappe.findOne(idGrappe));
		}
		asso.setLiaison(liaison);

		return repoAssoIdee.save(asso);

	}

	public AssociationGrappeEntity associerGrappe(IdeeEntity idee, Long idGrappe) {
		AssociationGrappeEntity asso = new AssociationGrappeEntity();
		asso.setIdee(idee);
		asso.setGrappe(repoGrappe.findOne(idGrappe));

		return repoAssoGrappe.save(asso);
	}

	public void associerIdee(Long idmaitre, Long idEsclave, Long idGrappe) {
		AssociationIdeeEntity asso = new AssociationIdeeEntity();
		asso.setIdeeMaitre(repoIdee.findOne(idmaitre));
		asso.setIdeeEsclave(repoIdee.findOne(idEsclave));
		if(idGrappe != null ){
		asso.setGrappe(repoGrappe.findOne(idGrappe));
		}
		repoAssoIdee.save(asso);
	}

	public void desassocierIdee(Long idmaitre, Long idEsclave) {
		List<AssociationIdeeEntity> asso = repoAssoIdee
				.findByIdeeMaitreAndIdeeEsclave(repoIdee.findOne(idmaitre),
						repoIdee.findOne(idEsclave));

		repoAssoIdee.delete(asso);

	}

	public void associerGrappe(Long idmaitre, Long idEsclave) throws Exception {
		GrappeEntity grappe = repoGrappe.findOne(idEsclave);
		if (grappe.getGrappeParent() == null) {
			GrappeEntity grappeParent = repoGrappe.findOne(idmaitre);
			grappe.setGrappeParent(grappeParent);
			repoGrappe.save(grappe);
		} else {
			throw new Exception("elle possède déjà un maitre");
		}

	}

	public void desassocierGrappe(Long idmaitre,Long idEsclave) throws Exception {
		GrappeEntity grappeParent = repoGrappe.findOne(idmaitre);
		if(grappeParent != null){
		
		GrappeEntity grappe = repoGrappe.findOne(idEsclave);
		if (grappe.getGrappeParent() == null) {
			throw new Exception("elle ne possède pas de maitre");
		}
		grappe.setGrappeParent(null);
		repoGrappe.save(grappe);
		}

	}

}
