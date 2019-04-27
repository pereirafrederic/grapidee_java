package fr.grapidee.application.services.entite;

import java.util.List;

import fr.grapidee.application.services.entite.idee.IdeeDTO;

public class AccueilIdeeDto {

	private List<IdeeDTO> orphelines;
	private List<IdeeDTO> maitres;

	public List<IdeeDTO> getOrphelines() {
		return orphelines;
	}

	public void setOrphelines(List<IdeeDTO> orphelines) {
		this.orphelines = orphelines;
	}

	public List<IdeeDTO> getMaitres() {
		return maitres;
	}

	public void setMaitres(List<IdeeDTO> maitres) {
		this.maitres = maitres;
	}

}
