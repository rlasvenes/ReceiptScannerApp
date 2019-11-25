package test.fr.ubordeaux.domain.type;

import main.fr.ubordeaux.domain.exception.GestionTicketException;
import main.fr.ubordeaux.domain.type.Total;
import org.junit.Test;

import static org.junit.Assert.*;

public class TotalTest {

    @Test
    public void shouldInitializeTotalAtZero() {
        Total total = new Total();

        assertEquals(0, total.montantTotal(), 0.0);
    }

    @Test
    public void shouldInitializeTotalWithCorrectValue() {
        double valeurInitiale = 53.498;
        Total total = new Total(valeurInitiale);

        assertEquals(valeurInitiale, total.montantTotal(), 0.0);
    }

    @Test
    public void shouldAddCorrectAmountToTotal() {
        double montant1 = 5.00;
        double montant2 = 18.42;

        Total total = new Total(0.00);
        total.ajouter(montant1);
        total.ajouter(montant2);

        assertEquals((montant1 + montant2), total.montantTotal(), 0.0);
    }

    @Test(expected = GestionTicketException.class)
    public void shouldNotAddAmountToTotalIfNegative() {
        double montant = -10_000;

        Total total = new Total(0.00);
        total.ajouter(montant);
    }

    @Test
    public void shouldSubAmountToTotal() {
        double montant = 10;
        int montantInitial = 42;

        Total total = new Total(montantInitial);
        total.retirer(montant);

        assertEquals((montantInitial - montant), total.montantTotal(), 0.0);
    }

    @Test(expected = GestionTicketException.class)
    public void shouldNotSubAmountToTotalIfNegative() {
        double montant = 1000;
        double montantInitial = 0.00;

        Total total = new Total(montantInitial);
        total.retirer(montant);
    }
}