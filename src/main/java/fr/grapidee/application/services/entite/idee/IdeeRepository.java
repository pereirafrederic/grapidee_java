package fr.grapidee.application.services.entite.idee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeeRepository extends CrudRepository<IdeeEntity, Long>,
		JpaSpecificationExecutor<IdeeEntity> {

	@Query("select idee from idee as idee where idee.id not in (select distinct ideeEsclave from assoidee ) and idee.id not in ( select distinct  ideeMaitre from assoidee )")
	List<IdeeEntity> findOrphelines();

	@Query("select idee from idee as idee where idee.id not in (select distinct  ideeEsclave from assoidee ) and idee.id in ( select distinct  ideeMaitre from assoidee )")
	List<IdeeEntity> findMaitres();

}
