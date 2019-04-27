package fr.grapidee.application.services.association;

import org.springframework.web.bind.annotation.PathVariable;

public class AssoGrappeGrappeBodyDto {
	private  Long idMaitre;
	private Long idEsclave;

	
	public Long getIdEsclave() {
		return idEsclave;
	}
	public void setIdEsclave(Long idEsclave) {
		this.idEsclave = idEsclave;
	}
	public Long getIdMaitre() {
		return idMaitre;
	}
	public void setIdMaitre(Long idMaitre) {
		this.idMaitre = idMaitre;
	}
}
