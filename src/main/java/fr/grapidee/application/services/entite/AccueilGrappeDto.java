package fr.grapidee.application.services.entite;

import java.util.List;

import fr.grapidee.application.services.entite.grappe.GrappeDTO;

public class AccueilGrappeDto {

	private List<GrappeDTO> orphelines;
	private List<GrappeDTO> maitres;

	public List<GrappeDTO> getOrphelines() {
		return orphelines;
	}

	public void setOrphelines(List<GrappeDTO> orphelines) {
		this.orphelines = orphelines;
	}

	public List<GrappeDTO> getMaitres() {
		return maitres;
	}

	public void setMaitres(List<GrappeDTO> maitres) {
		this.maitres = maitres;
	}

}
