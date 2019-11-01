package fr.grapidee.application.services.entite.idee;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.grapidee.application.services.association.grappe.AssociationGrappeEntity;
import fr.grapidee.application.services.association.idee.AssociationIdeeEntity;
import fr.grapidee.application.services.commun.CommunEntity;

@Entity(name = "idee")
@Table(name = "idee", schema = "grapidee_v2")
public class IdeeEntity extends CommunEntity {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ideeMaitre", targetEntity = AssociationIdeeEntity.class)
	private List<AssociationIdeeEntity> listeAssoIdee;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "idee", targetEntity = AssociationGrappeEntity.class)
	private List<AssociationGrappeEntity> listeAssoGrappe;

	public List<AssociationIdeeEntity> getListeAssoIdee() {
		return listeAssoIdee;
	}

	public void setListeAssoIdee(List<AssociationIdeeEntity> listeAssoIdee) {
		this.listeAssoIdee = listeAssoIdee;
	}

	public List<AssociationGrappeEntity> getListeAssoGrappe() {
		return listeAssoGrappe;
	}

	public void setListeAssoGrappe(List<AssociationGrappeEntity> listeAssoGrappe) {
		this.listeAssoGrappe = listeAssoGrappe;
	}

}
