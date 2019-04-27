package fr.grapidee.application.services.association.idee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.grapidee.application.services.commun.AbstractId;
import fr.grapidee.application.services.entite.grappe.GrappeEntity;
import fr.grapidee.application.services.entite.idee.IdeeEntity;

@Entity(name = "assoidee")
@Table(name = "asso_idee", schema = "grapidee_v2")
public class AssociationIdeeEntity extends AbstractId {

	private static final long serialVersionUID = 1L;

	@Column(unique = true, nullable = false, length = 100)
	private String liaison;

	@ManyToOne
	@JoinColumn(name = "maitre_id")
	private IdeeEntity ideeMaitre;

	@ManyToOne
	@JoinColumn(name = "esclave_id")
	private IdeeEntity ideeEsclave;

	@ManyToOne
	@JoinColumn(name = "grappe_id")
	private GrappeEntity grappe;

	public String getLiaison() {
		return liaison;
	}

	public void setLiaison(String liaison) {
		this.liaison = liaison;
	}

	public IdeeEntity getIdeeMaitre() {
		return ideeMaitre;
	}

	public void setIdeeMaitre(IdeeEntity ideeMaitre) {
		this.ideeMaitre = ideeMaitre;
	}

	public IdeeEntity getIdeeEsclave() {
		return ideeEsclave;
	}

	public void setIdeeEsclave(IdeeEntity ideeEsclave) {
		this.ideeEsclave = ideeEsclave;
	}

	public GrappeEntity getGrappe() {
		return grappe;
	}

	public void setGrappe(GrappeEntity grappe) {
		this.grappe = grappe;
	}

}
