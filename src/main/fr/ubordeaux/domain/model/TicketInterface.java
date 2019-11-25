package main.fr.ubordeaux.domain.model;

import java.util.Date;
import java.util.List;

import main.fr.ubordeaux.domain.type.Enseigne;
import main.fr.ubordeaux.domain.type.Prix;
import main.fr.ubordeaux.domain.type.Quantite;

public interface TicketInterface {
	public int ajouterAliment(Aliment aliment, Quantite quantite);
	public void ajouterQuantite(int alimentId, int quantite);
	public void retirerAliment(Aliment aliment);
	public List<EntreeTicketDTO> aliments();
	public Enseigne enseigne();
	public Date date();
	public Prix total();
}
