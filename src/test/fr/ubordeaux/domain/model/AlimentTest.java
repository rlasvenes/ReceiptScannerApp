package test.fr.ubordeaux.domain.model;

import main.fr.ubordeaux.domain.exception.GestionTicketException;
import main.fr.ubordeaux.domain.model.Aliment;
import main.fr.ubordeaux.domain.type.Prix;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlimentTest {
    @Test(expected = GestionTicketException.class)
    public void shouldThrowAnExceptionIfPriceNegative() {
        Aliment aliment = new Aliment("nom", new Prix(-100));
    }

    @Test
    public void shouldCopyEachFieldsOfAlimentIntoNewOne() {
        Aliment aliment1 = new Aliment("aliment1", new Prix(4.2));
        Aliment aliment2 = new Aliment(aliment1);

        assertEquals(aliment1, aliment2);
    }

    @Test
    public void shouldReturnCorrectUnitPrice() {
        Prix prixUnitaire1 = new Prix(42.21);
        Aliment aliment1 = new Aliment("aliment1", prixUnitaire1);

        assertEquals(prixUnitaire1.valeur(), aliment1.prixUnitaire(), 0.0001);
    }

    @Test
    public void shouldReturnCorrectName() {
        String nom = "Aliment 1 - test";
        Aliment aliment1 = new Aliment(nom, new Prix(10));

        assertEquals(nom, aliment1.nom());
    }

    @Test
    public void shouldReturnCorrectId() {
        Aliment premier = new Aliment("premier aliment", new Prix(10));
        Aliment deuxieme = new Aliment("deuxieme aliment", new Prix(10));
        Aliment troisieme = new Aliment("troisieme aliment", new Prix(10));

        // Index starts at 1
        assertEquals(1, premier.id());
        assertEquals(2, deuxieme.id());
        assertEquals(3, troisieme.id());
    }

    @Test
    public void shouldBeEqual() {
        Aliment aliment1 = new Aliment("Aliment test toto", new Prix(10));
        Aliment aliment2 = new Aliment("Aliment test toto", new Prix(10));

        // Should pass
        assertEquals(aliment1, aliment2);
        assertTrue(aliment1.equals(aliment2));
    }
}