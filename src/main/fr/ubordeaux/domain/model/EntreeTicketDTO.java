package main.fr.ubordeaux.domain.model;

import main.fr.ubordeaux.domain.type.Prix;
import main.fr.ubordeaux.domain.type.Quantite;

/**
 * Project :
 * Class : EntreeTicketDTO
 * Created at 24/11/19 at 21:32
 * Author : rlasvenes
 */

public class EntreeTicketDTO {

    private Aliment aliment;
    private Prix prixTotal;
    private Quantite quantite;

    private static int cpt = 0;
    private int id;

    public EntreeTicketDTO(Aliment aliment, Quantite quantite) {
        this.id = cpt++;
        this.aliment = aliment;
        this.quantite = quantite;
        this.prixTotal = new Prix(this.quantite.quantite() * this.aliment.prixUnitaire());
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

    public int id() {
        return this.id;
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
