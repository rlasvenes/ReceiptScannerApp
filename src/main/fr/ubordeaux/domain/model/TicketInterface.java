package main.fr.ubordeaux.domain.model;

import main.fr.ubordeaux.domain.type.Enseigne;
import main.fr.ubordeaux.domain.type.Prix;
import main.fr.ubordeaux.domain.type.Quantite;

import java.util.Date;
import java.util.List;

public interface TicketInterface {
	int ajouterAliment(Aliment aliment, Quantite quantite);
	void ajouterQuantite(int alimentId, int quantite);
	void retirerAliment(Aliment aliment);
	List<EntreeTicketDTO> aliments();
	Enseigne enseigne();
	Date date();
	Prix total();
	int id();
}
