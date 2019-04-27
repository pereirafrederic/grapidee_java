package fr.grapidee.application.services.association.grappe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.grapidee.application.services.entite.grappe.GrappeEntity;

@Repository
public interface AssociationGrappeRepository extends
		CrudRepository<AssociationGrappeEntity, Long>,
		JpaSpecificationExecutor<AssociationGrappeEntity> {

	List<AssociationGrappeEntity> findByGrappe(GrappeEntity grappeExistante);

}
