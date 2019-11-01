package fr.grapidee.application.utilitaire.comparator;

import java.util.Comparator;
import java.util.List;

import fr.grapidee.application.services.association.idee.AssociationIdeeEntity;
import fr.grapidee.application.services.entite.grappe.GrappeEntity;

public abstract class ComparatorSort {

	public static List<GrappeEntity> getGrappeSortByNom(List<GrappeEntity> liste) {

		Comparator<GrappeEntity> byName = new Comparator<GrappeEntity>() {
			@Override
			public int compare(GrappeEntity o1, GrappeEntity o2) {
				return o1.getNom().compareTo(o2.getNom());
			}
		};
		liste.sort(byName);

		return liste;
	}

	public static List<AssociationIdeeEntity> getIdeeSortByGrappe(
			List<AssociationIdeeEntity> liste) {

		Comparator<AssociationIdeeEntity> byName = new Comparator<AssociationIdeeEntity>() {
			@Override
			public int compare(AssociationIdeeEntity o1,
					AssociationIdeeEntity o2) {
				return o1.getGrappe().getNom()
						.compareTo(o2.getGrappe().getNom());
			}
		};
		liste.sort(byName);

		return liste;
	}

}
