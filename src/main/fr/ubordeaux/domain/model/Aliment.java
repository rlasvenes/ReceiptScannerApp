package main.fr.ubordeaux.domain.model;

import main.fr.ubordeaux.domain.type.Prix;

import java.util.Objects;

public class Aliment {
	
	private final String nom;
	private final Prix prixUnitaire;
	
	private static int cpt = 0;
	private int id;
	
	public Aliment(String nom, Prix prixUnitaire) {
		this.id = cpt++;
		this.nom = nom;
		this.prixUnitaire = prixUnitaire;
	}

	public Aliment(Aliment toCopy) {
		this.id = toCopy.id();
		this.nom = toCopy.nom();
		this.prixUnitaire = new Prix(toCopy.prixUnitaire());
	}
	
	public double prixUnitaire() {
		return this.prixUnitaire.valeur();
	}
	
	public String nom() {
		return this.nom;
	}

	public int id() {
		return this.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nom, prixUnitaire, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Aliment))
			return false;
		
		Aliment other = (Aliment) obj;
		return 	(this.nom().equals(other.nom())) && 
				(this.prixUnitaire() == other.prixUnitaire());
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(nom).append(" ");
		return out.toString();
	}
}
