package main.fr.ubordeaux.domain.type;

import main.fr.ubordeaux.domain.exception.GestionTicketException;

public class Quantite {
	private final int quantite;
	
	public Quantite(int quantite) {
		if (quantite <= 0) {
			throw new GestionTicketException("Quantité invalide (<= 0) : " + quantite);
		}
		this.quantite = quantite;
	}
	
	public int quantite() {
		return this.quantite;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Quantite))
			return false;
		
		Quantite other = (Quantite) obj;
		return (quantite == other.quantite);
	}

	@Override
	public String toString() {
		return String.valueOf(quantite);
	}
}
