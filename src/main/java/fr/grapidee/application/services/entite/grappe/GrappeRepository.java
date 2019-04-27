package fr.grapidee.application.services.entite.grappe;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrappeRepository extends CrudRepository<GrappeEntity, Long>,
		JpaSpecificationExecutor<GrappeEntity> {

	@Query("select grappe from grappe as grappe where grappe.id not in (select distinct  grappeParent from grappe ) and grappe.grappeParent is null )")
	Iterable<GrappeEntity> findOrphelines();

	@Query("select grappe from grappe as grappe where grappe.id in (select distinct  grappeParent from grappe ) and grappe.grappeParent is null )")
	Iterable<GrappeEntity> findMaitres();

}
