package fr.grapidee.application.services.entite.grappe;

import javax.persistence.Entity;
import javax.persistence.Table;

import fr.grapidee.application.services.commun.CommunLightEntity;

@Entity(name = "typeGrappe")
@Table(name = "type_grappe", schema = "grapidee_v2")
public class TypeGrappeEntity extends CommunLightEntity {
	private static final long serialVersionUID = 1L;

}
