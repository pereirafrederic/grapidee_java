package fr.grapidee.application.services.association.idee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.grapidee.application.services.entite.idee.IdeeEntity;

@Repository
public interface AssociationRepository
		extends CrudRepository<AssociationIdeeEntity, Long>, JpaSpecificationExecutor<AssociationIdeeEntity> {

	List<AssociationIdeeEntity> findByIdeeMaitreAndIdeeEsclave(IdeeEntity ideeMaitre, IdeeEntity ideeEsclave);

}
