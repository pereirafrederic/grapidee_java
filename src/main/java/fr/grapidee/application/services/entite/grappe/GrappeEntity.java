package fr.grapidee.application.services.entite.grappe;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.grapidee.application.services.association.grappe.AssociationGrappeEntity;
import fr.grapidee.application.services.commun.CommunEntity;

@Entity(name = "grappe")
@Table(name = "grappe", schema = "grapidee_v2")
public class GrappeEntity extends CommunEntity {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grappe", targetEntity = AssociationGrappeEntity.class)
	private List<AssociationGrappeEntity> listeAssoIdee;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grappeParent", targetEntity = GrappeEntity.class)
	private List<GrappeEntity> listeEnfants;

	@ManyToOne
	@JoinColumn(name = "grappe_id")
	private GrappeEntity grappeParent;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private TypeGrappeEntity type;

	private Integer ordre;

	public List<AssociationGrappeEntity> getListeAssoIdee() {
		return listeAssoIdee;
	}

	public void setListeAssoIdee(List<AssociationGrappeEntity> listeAssoIdee) {
		this.listeAssoIdee = listeAssoIdee;
	}

	public GrappeEntity getGrappeParent() {
		return grappeParent;
	}

	public void setGrappeParent(GrappeEntity grappeParent) {
		this.grappeParent = grappeParent;
	}

	public TypeGrappeEntity getType() {
		return type;
	}

	public void setType(TypeGrappeEntity type) {
		this.type = type;
	}

	public List<GrappeEntity> getListeEnfants() {
		return listeEnfants;
	}

	public void setListeEnfants(List<GrappeEntity> listeEnfants) {
		this.listeEnfants = listeEnfants;
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}

}
