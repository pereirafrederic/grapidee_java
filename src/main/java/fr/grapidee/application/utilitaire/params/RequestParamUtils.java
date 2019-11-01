package fr.grapidee.application.utilitaire.params;

public abstract class RequestParamUtils {

	public static Integer getNiveauInferieur(Integer niveauInferieur) {
		if (null == niveauInferieur) {
			niveauInferieur = 3;
		}
		return niveauInferieur;
	}

}
