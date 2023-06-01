package org.dummyApp;


import org.dummyApp.model.Reunion;
import org.dummyApp.model.ReunionSimple;
import org.dummyApp.model.Salle;
import org.dummyApp.model.Stock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Unit tests for MeetingPlanner App.
 */

public class MeetingPlannerAppTest {
    @Test
        //Les ReunionsSimple avec moins de 3 participants ne sont pas pris en compte dans la gestion des salles
    void reunionSimpleMinCapacityTest() {
        ArrayList<Reunion> listReunion = new ArrayList<>();
        listReunion.add(new ReunionSimple(11, 12, 2));
        listReunion.add(new ReunionSimple(10, 11, 4));
        assertEquals(MeetingPlannerApp.trimValidReunion(listReunion).size(), 1);
    }


    @Test
        //Reservation par priorisation du type de reunion
    void reserveByTypeTest() {
        ArrayList<Salle> listSalle = new ArrayList<Salle>();
        MeetingPlannerApp.initListSalle(listSalle);
        ArrayList<Reunion> listReunion = new ArrayList<Reunion>();
        MeetingPlannerApp.initListReunion(listReunion);
        ArrayList<Stock> listStock = new ArrayList<Stock>();
        MeetingPlannerApp.initListStock(listStock);

        //Filtrer les réunions sans salles
        listReunion = MeetingPlannerApp.trimValidReunion(listReunion);

        assertEquals(5, MeetingPlannerApp.reserveByType(listSalle, listReunion, listStock));
    }

    @Test
        //Reservation par priorisation du type de reunion
    void reserveByTypeEquipement() {
        ArrayList<Salle> listSalle = new ArrayList<Salle>();
        MeetingPlannerApp.initListSalle(listSalle);
        ArrayList<Reunion> listReunion = new ArrayList<Reunion>();
        MeetingPlannerApp.initListReunion(listReunion);
        ArrayList<Stock> listStock = new ArrayList<Stock>();
        MeetingPlannerApp.initListStock(listStock);

        //Filtrer les réunions sans salles
        listReunion = MeetingPlannerApp.trimValidReunion(listReunion);

        assertEquals(5, MeetingPlannerApp.reserveByEquipement(listSalle, listReunion, listStock));
    }


    @Test
        //Reservation par priorisation du type de reunion
    void reserveByPlanningTest() {
        ArrayList<Salle> listSalle = new ArrayList<Salle>();
        MeetingPlannerApp.initListSalle(listSalle);
        ArrayList<Reunion> listReunion = new ArrayList<Reunion>();
        MeetingPlannerApp.initListReunion(listReunion);
        ArrayList<Stock> listStock = new ArrayList<Stock>();
        MeetingPlannerApp.initListStock(listStock);

        //Filtrer les réunions sans salles
        listReunion = MeetingPlannerApp.trimValidReunion(listReunion);

        assertEquals(7, MeetingPlannerApp.reserveByPlanning(listSalle, listReunion, listStock));
    }


}
