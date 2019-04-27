package fr.grapidee.application.services.association.grappe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.grapidee.application.services.commun.AbstractId;
import fr.grapidee.application.services.entite.grappe.GrappeEntity;
import fr.grapidee.application.services.entite.idee.IdeeEntity;

@Entity(name = "assograppe")
@Table(name = "asso_grappe", schema = "grapidee_v2")
public class AssociationGrappeEntity extends AbstractId {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "grappe_id")
	private GrappeEntity grappe;

	@ManyToOne
	@JoinColumn(name = "idee_id")
	private IdeeEntity idee;

	public GrappeEntity getGrappe() {
		return grappe;
	}

	public void setGrappe(GrappeEntity grappe) {
		this.grappe = grappe;
	}

	public IdeeEntity getIdee() {
		return idee;
	}

	public void setIdee(IdeeEntity idee) {
		this.idee = idee;
	}

}
