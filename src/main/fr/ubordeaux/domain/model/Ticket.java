package main.fr.ubordeaux.domain.model;

import java.text.SimpleDateFormat;
import java.util.*;

import main.fr.ubordeaux.domain.exception.GestionTicketException;
import main.fr.ubordeaux.domain.type.Enseigne;
import main.fr.ubordeaux.domain.type.Prix;
import main.fr.ubordeaux.domain.type.Quantite;
import main.fr.ubordeaux.domain.type.Total;

/**
 * 
 * @author rlasvenes
 * <h2> Aggregate </h2>
 *
 */
public class Ticket implements TicketInterface {
	private Map<Integer, EntreeTicket> listeAlimentTicket;
	private Enseigne enseigne;
	private Date date;
	private Total prixTotal;
	
	public Ticket(Enseigne enseigne, Date date) {
		this.enseigne = enseigne;
		this.date = date;
		this.listeAlimentTicket = new HashMap<Integer, EntreeTicket>();
		
		this.prixTotal = new Total(0.00);
	}
	
	@Override
	public int ajouterAliment(Aliment aliment, Quantite quantite) {
		if (aliment == null) {
			throw new GestionTicketException("Impossible d'ajouter un aliment inexistant (null)");
		}
		EntreeTicket exists = listeAlimentTicket.get(aliment.id());
		if (exists != null) {
			throw new GestionTicketException("Référence déjà inscrite dans le ticket : " + exists.id());
		}
		
		EntreeTicket ligne = new EntreeTicket(aliment, quantite);
		listeAlimentTicket.put(aliment.id(), ligne);
		this.prixTotal.ajouter(aliment.prixUnitaire() * quantite.quantite());

		return ligne.id();
	}

	@Override
	public void ajouterQuantite(int alimentId, int quantite) {
		EntreeTicket exists = listeAlimentTicket.get(alimentId);
		if (exists == null) {
			throw new GestionTicketException("Impossible de changer la quantite d'un aliment inexistant. id = " + alimentId);
		}

		exists.changerQuantite(quantite);
		this.prixTotal.ajouter(exists.prixTotal());
	}

	@Override
	public void retirerAliment(Aliment aliment) {
		if (aliment == null) {
			throw new GestionTicketException("Impossible de retire un aliment inexistant (null)");
		}
		
		EntreeTicket exists = listeAlimentTicket.get(aliment.id());
		
		if (exists != null) {
			listeAlimentTicket.remove(aliment.id());
			this.prixTotal.retirer(exists.prixTotal());
		}
	}

	@Override
	public List<EntreeTicketDTO> aliments() {
		List<EntreeTicketDTO> tmp = new ArrayList<>();
		for (EntreeTicket entree : listeAlimentTicket.values()) {
			tmp.add(entree.dto());
		}
		return tmp;
	}

	@Override
	public Enseigne enseigne() {
		return enseigne;
	}

	@Override
	public Date date() {
		return (Date) date.clone();
	}

	@Override
	public Prix total() {
		return new Prix(prixTotal.montantTotal());
	}

	@Override
	public int id() {
		return this.hashCode();
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		StringBuilder out = new StringBuilder();
		out.append("[");
		out.append(enseigne);
		out.append("]");
		out.append(" Ticket du : ");
		out.append(sdf.format(date));
		out.append("\n");

		for (Map.Entry<Integer, EntreeTicket> ligne : listeAlimentTicket.entrySet()) {
			out.append("\t");
			out.append(ligne.getKey());
			out.append(" : ");
			out.append(ligne.getValue());
			out.append("\n");
		}
		out.append(prixTotal);

		return out.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(enseigne, date);
	}


}
