package test.fr.ubordeaux.domain.model;

import main.fr.ubordeaux.domain.exception.GestionTicketException;
import main.fr.ubordeaux.domain.model.Aliment;
import main.fr.ubordeaux.domain.model.EntreeTicket;
import main.fr.ubordeaux.domain.type.Prix;
import main.fr.ubordeaux.domain.type.Quantite;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class EntreeTicketTest {

    private Prix prix;
    private Aliment aliment;
    private Quantite quantite;


    @Before
    public void initializeSomeDataTest() {
        prix = new Prix(4);
        aliment = new Aliment("test", prix);
        quantite = new Quantite(10);
    }

    @Test
    public void shouldAddNewQuantity() {
        int quantite2 = 25;

        EntreeTicket entree = new EntreeTicket(aliment, quantite);

        assertEquals(quantite.quantite(), entree.quantite());
        entree.changerQuantite(quantite2);
        assertEquals(quantite.quantite() + quantite2, entree.quantite());
    }

    @Test(expected = GestionTicketException.class)
    public void shouldNotAddNegativeQuantityIfNotEnought() {
        int quantite2 = -1000;

        EntreeTicket entree = new EntreeTicket(aliment, quantite);
        entree.changerQuantite(quantite2);
    }

    @Test
    public void shouldAddNegativeQuantityIfEnought() {
        int quantite2 = -1000;

        try {
            EntreeTicket entree = new EntreeTicket(aliment, new Quantite(2500));
            entree.changerQuantite(quantite2);
            assertTrue(true);
        } catch (GestionTicketException e) {
            fail("Reached code that should not be reached");
        }
    }

    @Test(expected = GestionTicketException.class)
    public void shouldNotBeEqualToZero() {
        int quantiteNegative = -1000;
        int quantitePositive = -quantiteNegative;

        EntreeTicket entree = new EntreeTicket(aliment, new Quantite(quantitePositive));
        entree.changerQuantite(quantiteNegative); // 1000 + (-1000) = 0 --> Exception
    }

    @Test
    public void shouldUpdateTotalPrice() {
        int nbProduits = 10;
        EntreeTicket entree = new EntreeTicket(aliment, new Quantite(1));
        assertEquals(aliment.prixUnitaire(), entree.prixTotal());
        int nbProduitsPresents = entree.quantite();

        entree.changerQuantite(nbProduits);
        assertEquals(aliment.prixUnitaire() * (nbProduits + nbProduitsPresents), entree.prixTotal());
    }
}