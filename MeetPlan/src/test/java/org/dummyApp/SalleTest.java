package org.dummyApp;

import org.dummyApp.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for Salle function.
 */
public class SalleTest {

    @Test
    void isDisponibleCreneauTest() {
        //Nouvelle salle libre de 8h a 20h
        Integer heureDebut = 14;
        Integer heurefin = 15;
        Salle salle = new Salle("salleTest", 23, new ArrayList<Equipement>(Arrays.asList(Equipement.NEANT)));
        salle.reserveCreneau(14, 15, salle.getDisponibilite());
        assertTrue(heureDebut < heurefin); //Coherence du creneau
        assertTrue(salle.isDisponibleCreneau(8, 9));
        assertFalse(salle.isDisponibleCreneau(17, 21)); //Heure depassant 20h
        assertFalse(salle.isDisponibleCreneau(13, 14)); //Creneau déja réservé
    }

    @Test
    void hasRequiredEquipementTest() {
        //Test d'approvisionnement de l'équipement
        Salle salle = new Salle("salleTest", 5, new ArrayList<Equipement>(Arrays.asList(Equipement.NEANT)));
        Visio visio = new Visio(9, 10, 6);
        ArrayList<Stock> listStock = new ArrayList<Stock>();
        listStock.add(new Stock(Equipement.PIEUVRE, 4));
        assertFalse(salle.hasRequiredEquipement(visio, listStock));
        listStock.add(new Stock(Equipement.ECRAN, 4));
        listStock.add(new Stock(Equipement.WEBCAM, 4));
        assertTrue(salle.hasRequiredEquipement(visio, listStock));
    }
}
