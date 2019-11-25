package test.fr.ubordeaux.domain.model;

import main.fr.ubordeaux.domain.exception.GestionTicketException;
import main.fr.ubordeaux.domain.model.Aliment;
import main.fr.ubordeaux.domain.model.TicketInterface;
import main.fr.ubordeaux.domain.service.TicketBuilder;
import main.fr.ubordeaux.domain.type.EnseigneId;
import main.fr.ubordeaux.domain.type.Prix;
import main.fr.ubordeaux.domain.type.Quantite;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TicketTest {

    @Test
    public void shouldConstructTicketWithEmptyFields() {
        String dateFormat = "24/11/2019 18:00";
        TicketInterface ticket = new TicketBuilder()
                                .enseigne(EnseigneId.AUCHAN)
                                .date(dateFormat)
                                .build();

        Date date;
        try {
            date = new SimpleDateFormat().parse(dateFormat);

            assertEquals(date, ticket.date());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals(0, ticket.aliments().size());
        assertEquals(new Prix(0), ticket.total());
    }

    @Test(expected = GestionTicketException.class)
    public void shouldThrowExceptionIfInvalidDateFormat() {
        String dateFormat = "32/13/2019 25:00";
        new TicketBuilder()
                .enseigne(EnseigneId.AUCHAN)
                .date(dateFormat)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfDateIsNull() {
        new TicketBuilder()
                .enseigne(EnseigneId.AUCHAN)
                .date(null)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfInvalEnseigneId() {
        String dateFormat = "32/13/2019 25:00";
        new TicketBuilder()
                .enseigne(EnseigneId.valueOf("LECLERC 2"))
                .date(dateFormat)
                .build();
    }

    @Test
    public void shouldAddValidAlimentToList() {
        String dateFormat = "24/11/2019 18:00";
        TicketInterface ticket = new TicketBuilder()
                .enseigne(EnseigneId.AUCHAN)
                .date(dateFormat)
                .build();

        assertEquals(0, ticket.aliments().size());

        Aliment aliment = new Aliment("aliment1", new Prix(10));
        ticket.ajouterAliment(aliment, new Quantite(1));

        assertEquals(1, ticket.aliments().size());
    }

    @Test(expected = GestionTicketException.class)
    public void shouldNotAddValidAlimentToListIfAlreadyPresent() {
        String dateFormat = "24/11/2019 18:00";
        TicketInterface ticket = new TicketBuilder()
                .enseigne(EnseigneId.AUCHAN)
                .date(dateFormat)
                .build();

        Aliment aliment = new Aliment("aliment1", new Prix(10));
        ticket.ajouterAliment(aliment, new Quantite(1));
        ticket.ajouterAliment(aliment, new Quantite(1));
    }

    @Test
    public void shouldAddAlimentAfterNew() {
        String dateFormat = "24/11/2019 18:00";
        TicketInterface ticket = new TicketBuilder()
                .enseigne(EnseigneId.AUCHAN)
                .date(dateFormat)
                .build();

        Aliment aliment = new Aliment("aliment1", new Prix(10));
        ticket.ajouterAliment(aliment, new Quantite(1));

        aliment = new Aliment("aliment1 bis", new Prix(20));
        ticket.ajouterAliment(aliment, new Quantite(1));

        assertEquals(2, ticket.aliments().size());
    }

    @Test
    public void shouldNotRemoveAlimentIfNotPresent() {
        String dateFormat = "24/11/2019 18:00";
        TicketInterface ticket = new TicketBuilder()
                .enseigne(EnseigneId.AUCHAN)
                .date(dateFormat)
                .build();

        assertEquals(0, ticket.aliments().size());

        Aliment aliment = new Aliment("aliment1", new Prix(10));
        ticket.retirerAliment(aliment);

        assertEquals(0, ticket.aliments().size());
    }

}