package main.fr.ubordeaux.domain.type;

import main.fr.ubordeaux.domain.exception.GestionTicketException;

import java.math.BigDecimal;

public class Prix {
	private final double valeur;
	
	public Prix(double valeur) {
		if (valeur < 0) {
			throw new GestionTicketException("Erreur, prix invalide (< 0) : " + valeur);
		}
		BigDecimal val = BigDecimal.valueOf(valeur).setScale(2, BigDecimal.ROUND_HALF_UP);
		this.valeur = val.doubleValue();
	}
	
	public double valeur() {
		return this.valeur;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Prix))
			return false;
		
		Prix other = (Prix) obj;
		return other.valeur() == Double.doubleToLongBits(other.valeur);
	}

	@Override
	public String toString() {
		return String.valueOf(valeur);
	}
}
