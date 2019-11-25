package main.fr.ubordeaux.domain.service;

import main.fr.ubordeaux.domain.exception.GestionTicketException;
import main.fr.ubordeaux.domain.model.Ticket;
import main.fr.ubordeaux.domain.model.TicketInterface;
import main.fr.ubordeaux.domain.type.Enseigne;
import main.fr.ubordeaux.domain.type.EnseigneId;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketBuilder {
	private Enseigne enseigne;
	private Date date;
	
	public TicketBuilder enseigne(EnseigneId id) {
		this.enseigne = id.label();
		return this;
	}
	
	public TicketBuilder date(String date) {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			df.setLenient(false);
			this.date = df.parse(date);
		} catch (ParseException e) {
			throw new GestionTicketException("Erreur de format de date : " + e.getMessage());
		}
		return this;
	}
	
	public TicketInterface build() {
		return new Ticket(enseigne, date);
	}
	
}
