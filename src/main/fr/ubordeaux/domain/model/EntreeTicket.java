package main.fr.ubordeaux.domain.model;

import main.fr.ubordeaux.domain.type.Prix;
import main.fr.ubordeaux.domain.type.Quantite;

public class EntreeTicket {
	private Aliment aliment;
	private Prix prixTotal;
	private Quantite quantite;
	
	private static int cpt = 0;
	private int id;
	
	public EntreeTicket(Aliment aliment, Quantite quantite) {
		this.id = cpt++;
		this.aliment = aliment;
		this.quantite = quantite;
		
		majPrixTotal();
	}

	public Aliment aliment() {
		return aliment;
	}

	public double prixTotal() {
		return prixTotal.valeur();
	}

	public int quantite() {
		return quantite.quantite();
	}

	public void changerQuantite(int valeur) {
		this.quantite = new Quantite(this.quantite.quantite() + valeur);
		majPrixTotal();
	}
	
	public int id() {
		return this.id;
	}

	private void majPrixTotal() {
		this.prixTotal = new Prix(this.quantite.quantite() * this.aliment.prixUnitaire());
	}

	public EntreeTicketDTO dto() {
		return new EntreeTicketDTO(aliment(), new Quantite(quantite()));
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(aliment).append(aliment.prixUnitaire()).append("€");
		out.append(" * ").append(quantite).append(" : ");
		out.append(prixTotal).append("€");
		
		return out.toString();
	}
}
