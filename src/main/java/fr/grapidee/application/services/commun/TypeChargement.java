package fr.grapidee.application.services.commun;

public enum TypeChargement {

	BASIQUE, ARBRE, COMPLET;

	public static boolean estAuMoins(TypeChargement type,
			TypeChargement typeCompare) {
		boolean estAuMoins = false;
		if (type.equals(typeCompare) || type.equals(COMPLET)) {
			estAuMoins = true;
		} else if (type.equals(ARBRE)) {
			if (typeCompare.equals(BASIQUE)) {
				estAuMoins = true;
			}
		}

		return estAuMoins;
	}

}
