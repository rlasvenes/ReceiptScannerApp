package main.fr.ubordeaux.infrastructure.inmemory;

import main.fr.ubordeaux.domain.model.Aliment;
import main.fr.ubordeaux.domain.model.TicketInterface;
import main.fr.ubordeaux.domain.service.TicketBuilder;
import main.fr.ubordeaux.domain.type.EnseigneId;
import main.fr.ubordeaux.domain.type.Prix;
import main.fr.ubordeaux.domain.type.Quantite;

public class ApplicationScanner {

	public static void main(String[] args) {
		TicketInterface ticket1 = new TicketBuilder()
										.enseigne(EnseigneId.INTERMARCHE)
										.date("19/10/2019 19:11")
										.build();
		
		Aliment tomate = new Aliment("Tomate", new Prix(2.50));
		Aliment tomateGrappe = new Aliment("Tomate en grappe", new Prix(9.00));
		Aliment merguez = new Aliment("Merguez piment", new Prix(6.00));
		
		ticket1.ajouterAliment(tomate, new Quantite(4));
		ticket1.ajouterAliment(tomateGrappe, new Quantite(3));
		ticket1.ajouterAliment(merguez, new Quantite(2));

		System.out.println(ticket1);
	}

}
