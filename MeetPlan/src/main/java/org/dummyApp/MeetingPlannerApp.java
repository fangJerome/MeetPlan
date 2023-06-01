package org.dummyApp;


import org.dummyApp.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class MeetingPlannerApp {
    public static void main(String[] args) {
        //Initier les données
        ArrayList<Salle> listSalle = new ArrayList<Salle>();
        initListSalle(listSalle);
        ArrayList<Reunion> listReunion = new ArrayList<Reunion>();
        initListReunion(listReunion);
        ArrayList<Stock> listStock = new ArrayList<Stock>();
        initListStock(listStock);

        //Filtrer les réunions sans salles
        listReunion = trimValidReunion(listReunion);

        reserveByCapacity(listSalle, listReunion, listStock);
        /* Priorisation par critère
        reserveByType(listSalle,listReunion,listStock);
        reserveByEquipement(listSalle,listReunion,listStock);
        reserveByPlanning(listSalle,listReunion,listStock);*/

    }


    public static int reserveByCapacity(ArrayList<Salle> listSalle, ArrayList<Reunion> listReunion, ArrayList<Stock> listStock) {
        System.out.println("Priorisation par capacité des salles");
        int nbReservation = 0;
        listReunion.sort(Comparator.comparing(Reunion::getNbParticipants));
        Collections.reverse(listReunion);
        for (Reunion reunion : listReunion) {

            ArrayList<Salle> salleDisponible = listSalle.stream().
                    //is creneau non occupé et libre 1h apres
                            filter(e -> e.isDisponibleCreneau(reunion.getHeureDebut(), reunion.getHeureFin())).
                    //is capacite ok
                            filter(e -> e.getCapacite() >= reunion.getNbParticipants()).
                    //is equipement ok
                            filter(e -> e.hasRequiredEquipement(reunion, listStock))

                    .collect(Collectors.toCollection(ArrayList::new));

            /*Trier les salles*/
            salleDisponible.sort(Comparator.comparingInt(Salle::getCapacite));
            //reserver le creneau

            if (salleDisponible.size() > 0) {
                salleDisponible.get(0).reserveCreneau(reunion.getHeureDebut(), reunion.getHeureFin(), salleDisponible.get(0).getDisponibilite());
                System.out.println("Créneau Réservé avec succès :" + salleDisponible.get(0).getNom() + " pour réunion " + (reunion.getReunionType()) + " de :" + reunion.getHeureDebut() + " heures, nb participants:" + reunion.getNbParticipants() + ", piece d'équipements requis : " + reunion.getNbEquipementRequis());
                nbReservation = nbReservation + 1;
            } else {
                System.out.println("Aucune reservation possible pour réunion " + reunion.getReunionType() + " de :" + reunion.getHeureDebut() + " heures, nb participants:" + reunion.getNbParticipants());
            }
        }

        return nbReservation;
    }

    public static int reserveByType(ArrayList<Salle> listSalle, ArrayList<Reunion> listReunion, ArrayList<Stock> listStock) {
        System.out.println("Priorisation par type de reunion");
        int nbReservation = 0;
        listReunion.sort(Comparator.comparing(Reunion::getReunionType));
        for (Reunion reunion : listReunion) {

            ArrayList<Salle> salleDisponible = listSalle.stream().
                    //is creneau non occupé et libre 1h apres
                            filter(e -> e.isDisponibleCreneau(reunion.getHeureDebut(), reunion.getHeureFin())).
                    //is capacite ok
                            filter(e -> e.getCapacite() >= reunion.getNbParticipants()).
                    //is equipement ok
                            filter(e -> e.hasRequiredEquipement(reunion, listStock))

                    .collect(Collectors.toCollection(ArrayList::new));

            /*Trier les salles*/
            salleDisponible.sort(Comparator.comparingInt(Salle::getCapacite));
            //reserver le creneau

            if (salleDisponible.size() > 0) {
                salleDisponible.get(0).reserveCreneau(reunion.getHeureDebut(), reunion.getHeureFin(), salleDisponible.get(0).getDisponibilite());
                System.out.println("Créneau Réservé avec succès :" + salleDisponible.get(0).getNom() + " pour réunion " + (reunion.getReunionType()) + " de :" + reunion.getHeureDebut() + " heures, nb participants:" + reunion.getNbParticipants() + ", piece d'équipements requis : " + reunion.getNbEquipementRequis());
                nbReservation = nbReservation + 1;
            } else {
                System.out.println("Aucune reservation possible pour réunion " + reunion.getReunionType() + " de :" + reunion.getHeureDebut() + " heures, nb participants:" + reunion.getNbParticipants());
            }
        }

        return nbReservation;
    }

    public static int reserveByEquipement(ArrayList<Salle> listSalle, ArrayList<Reunion> listReunion, ArrayList<Stock> listStock) {
        System.out.println("Priorisation par equipement");
        int nbReservation = 0;
        listReunion.sort(Comparator.comparingInt(Reunion::getNbEquipementRequis));
        Collections.reverse(listReunion);
        for (Reunion reunion : listReunion) {

            ArrayList<Salle> salleDisponible = listSalle.stream().
                    //is creneau non occupé et libre 1h apres
                            filter(e -> e.isDisponibleCreneau(reunion.getHeureDebut(), reunion.getHeureFin())).
                    //is capacite ok
                            filter(e -> e.getCapacite() >= reunion.getNbParticipants()).
                    //is equipement ok
                            filter(e -> e.hasRequiredEquipement(reunion, listStock))

                    .collect(Collectors.toCollection(ArrayList::new));

            /*Trier les salles*/
            salleDisponible.sort(Comparator.comparingInt(Salle::getCapacite));
            //reserver le creneau

            if (salleDisponible.size() > 0) {
                salleDisponible.get(0).reserveCreneau(reunion.getHeureDebut(), reunion.getHeureFin(), salleDisponible.get(0).getDisponibilite());
                System.out.println("Créneau Réservé avec succès :" + salleDisponible.get(0).getNom() + " pour réunion " + (reunion.getReunionType()) + " de :" + reunion.getHeureDebut() + " heures, nb participants:" + reunion.getNbParticipants() + ", piece d'équipements requis : " + reunion.getNbEquipementRequis());
                nbReservation = nbReservation + 1;
            } else {
                System.out.println("Aucune reservation possible pour réunion " + reunion.getReunionType() + " de :" + reunion.getHeureDebut() + " heures, nb participants:" + reunion.getNbParticipants());
            }
        }
        return nbReservation;

    }

    public static int reserveByPlanning(ArrayList<Salle> listSalle, ArrayList<Reunion> listReunion, ArrayList<Stock> listStock) {
        System.out.println("Priorisation par planning");
        int nbReservation = 0;
        listReunion.sort(Comparator.comparingInt(Reunion::getHeureDebut));
        for (Reunion reunion : listReunion) {

            ArrayList<Salle> salleDisponible = listSalle.stream().
                    //is creneau non occupé et libre 1h apres
                            filter(e -> e.isDisponibleCreneau(reunion.getHeureDebut(), reunion.getHeureFin())).
                    //is capacite ok
                            filter(e -> e.getCapacite() >= reunion.getNbParticipants()).
                    //is equipement ok
                            filter(e -> e.hasRequiredEquipement(reunion, listStock))

                    .collect(Collectors.toCollection(ArrayList::new));

            /*Trier les salles*/
            salleDisponible.sort(Comparator.comparingInt(Salle::getCapacite));
            //reserver le creneau

            if (salleDisponible.size() > 0) {
                salleDisponible.get(0).reserveCreneau(reunion.getHeureDebut(), reunion.getHeureFin(), salleDisponible.get(0).getDisponibilite());
                System.out.println("Créneau Réservé avec succès :" + salleDisponible.get(0).getNom() + " pour réunion " + (reunion.getReunionType()) + " de :" + reunion.getHeureDebut() + " heures, nb participants:" + reunion.getNbParticipants() + ", piece d'équipements requis : " + reunion.getNbEquipementRequis());
                nbReservation = nbReservation + 1;
            } else {
                System.out.println("Aucune reservation possible pour réunion " + reunion.getReunionType() + " de :" + reunion.getHeureDebut() + " heures, nb participants:" + reunion.getNbParticipants());
            }
        }

        return nbReservation;
    }

    public static void initListSalle(ArrayList<Salle> listSalle) {
        listSalle.add(new Salle("E1001", 23, new ArrayList<Equipement>(Arrays.asList(Equipement.NEANT))));
        listSalle.add(new Salle("E1002", 10, new ArrayList<Equipement>(Arrays.asList(Equipement.ECRAN))));
        listSalle.add(new Salle("E1003", 8, new ArrayList<Equipement>(Arrays.asList(Equipement.PIEUVRE))));
        listSalle.add(new Salle("E1004", 4, new ArrayList<Equipement>(Arrays.asList(Equipement.TABLEAU))));
        listSalle.add(new Salle("E2001", 4, new ArrayList<Equipement>(Arrays.asList(Equipement.NEANT))));
        listSalle.add(new Salle("E2002", 15, new ArrayList<Equipement>(Arrays.asList(Equipement.ECRAN, Equipement.WEBCAM))));
        listSalle.add(new Salle("E2003", 7, new ArrayList<Equipement>(Arrays.asList(Equipement.NEANT))));
        listSalle.add(new Salle("E2004", 9, new ArrayList<Equipement>(Arrays.asList(Equipement.TABLEAU))));
        listSalle.add(new Salle("E3001", 13, new ArrayList<Equipement>(Arrays.asList(Equipement.ECRAN, Equipement.WEBCAM, Equipement.PIEUVRE))));
        listSalle.add(new Salle("E3002", 8, new ArrayList<Equipement>(Arrays.asList(Equipement.NEANT))));
        listSalle.add(new Salle("E3003", 9, new ArrayList<Equipement>(Arrays.asList(Equipement.ECRAN, Equipement.PIEUVRE))));
        listSalle.add(new Salle("E3004", 4, new ArrayList<Equipement>(Arrays.asList(Equipement.NEANT))));
    }


    public static void initListReunion(ArrayList<Reunion> listReunion) {
        listReunion.add(new Visio(9, 10, 8));
        listReunion.add(new Visio(9, 10, 6));
        listReunion.add(new ReunionCouple(11, 12, 4));
        listReunion.add(new ReunionSimple(11, 12, 2));
        listReunion.add(new Spec(11, 12, 9));
        listReunion.add(new ReunionCouple(9, 10, 7));
        listReunion.add(new Visio(8, 9, 9));
        listReunion.add(new Spec(8, 9, 10));
        listReunion.add(new Spec(9, 10, 5));
        listReunion.add(new ReunionSimple(9, 10, 4));
        listReunion.add(new ReunionCouple(9, 10, 8));
        listReunion.add(new Visio(11, 12, 12));
        listReunion.add(new Spec(11, 12, 5));
        listReunion.add(new Visio(8, 9, 3));
        listReunion.add(new Spec(8, 9, 2));
        listReunion.add(new Visio(8, 9, 12));
        listReunion.add(new Visio(10, 11, 6));
        listReunion.add(new ReunionSimple(11, 12, 2));
        listReunion.add(new ReunionSimple(9, 10, 4));
        listReunion.add(new ReunionCouple(9, 10, 7));
    }

    public static void initListStock(ArrayList<Stock> listStock) {
        listStock.add(new Stock(Equipement.PIEUVRE, 4));
        listStock.add(new Stock(Equipement.ECRAN, 5));
        listStock.add(new Stock(Equipement.WEBCAM, 4));
        listStock.add(new Stock(Equipement.TABLEAU, 2));
    }

    public static ArrayList<Reunion> trimValidReunion(ArrayList<Reunion> listReunion) {
        for (int i = listReunion.size() - 1; i >= 0; i--) {
            if (listReunion.get(i).getNbParticipants() <= 2 && (ReunionSimple.class.isInstance(listReunion.get(i)))) {
                listReunion.remove(i);
            }
        }
        return listReunion;
    }

}
