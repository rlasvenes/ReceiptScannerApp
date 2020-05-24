package main.fr.ubordeaux.domain.type;

import main.fr.ubordeaux.domain.exception.GestionTicketException;

import java.math.BigDecimal;

public class Total {
	private double total;
	
	public Total() {
		this(0);
	}
	
	public Total(double total) {
		BigDecimal val = BigDecimal.valueOf(total).setScale(2, BigDecimal.ROUND_HALF_UP);
		this.total = val.doubleValue();
	}
	
	public void ajouter(double montant) {
		this.total += montant;
		if (this.total < 0) {
			throw new GestionTicketException("Impossible d'ajouter un montant qui aboutit à un total négatif. total = " + this.total);
		}
	}
	
	public void retirer(double montant) {
		this.total -= montant;
		if (this.total < 0) {
			throw new GestionTicketException("Impossible de retirer un montant qui aboutit à un total négatif. total = " + this.total);
		}
	}
	
	public double montantTotal() {
		return this.total;
	}

	@Override
	public String toString() {
		return String.valueOf(total + "€");
	}
}
