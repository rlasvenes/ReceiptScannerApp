package test.fr.ubordeaux.domain.type;

import main.fr.ubordeaux.domain.exception.GestionTicketException;
import main.fr.ubordeaux.domain.type.Quantite;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuantiteTest {

    @Test(expected = GestionTicketException.class)
    public void shouldThrowExceptionIfNegativeQuantity() {
        Quantite quantite = new Quantite(-10);
    }

    @Test
    public void shouldBeEqual() {
        Quantite quantite1 = new Quantite(21);
        Quantite quantite2 = new Quantite(21);

        assertEquals(quantite1, quantite2);
        assertTrue(quantite1.equals(quantite2));
    }

    @Test
    public void shouldReturnCorrectQuantity() {
        int valeur = 12;
        Quantite quantite = new Quantite(valeur);

        assertEquals(valeur, quantite.quantite());
        assertTrue(quantite.quantite() == valeur);
    }
}