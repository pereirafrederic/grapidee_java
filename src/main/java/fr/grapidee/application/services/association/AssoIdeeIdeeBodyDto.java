package fr.grapidee.application.services.association;

import org.springframework.web.bind.annotation.PathVariable;

public class AssoIdeeIdeeBodyDto {
	private  Long idMaitre;
	private Long idEsclave;
	private Long idGrappe;

	
	public Long getIdEsclave() {
		return idEsclave;
	}
	public void setIdEsclave(Long idEsclave) {
		this.idEsclave = idEsclave;
	}
	public Long getIdGrappe() {
		return idGrappe;
	}
	public void setIdGrappe(Long idGrappe) {
		this.idGrappe = idGrappe;
	}
	public Long getIdMaitre() {
		return idMaitre;
	}
	public void setIdMaitre(Long idMaitre) {
		this.idMaitre = idMaitre;
	}
}
