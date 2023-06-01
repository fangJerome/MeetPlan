package org.dummyApp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class Salle {
    private String nom;
    private Integer capacite;
    private boolean disponible;
    private ArrayList equipementPresent;

    private ArrayList<Integer> disponibilite = new ArrayList<Integer>(Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20));


    public Salle(String nom, Integer capacite, ArrayList equipementPresent) {
        this.setNom(nom);
        this.setCapacite(capacite);
        this.setEquipementPresent(equipementPresent);

    }

    //All check ok
    public ArrayList<Integer> reserveCreneau(Integer heureDebut, Integer heureFin, ArrayList<Integer> disponibilite) {
        disponibilite.remove(new Integer(heureDebut));
        disponibilite.remove(new Integer(heureFin));
        if (!heureFin.equals(20)) {
            disponibilite.remove(new Integer(heureFin + 1));
        }
        return disponibilite;
    }

    public boolean isDisponibleCreneau(Integer heureDebut, Integer heureFin) {
        if (heureDebut != null && heureFin != null
                && heureDebut >= 8 && heureFin <= 20
                && heureDebut < heureFin && disponibilite.contains(heureDebut) && disponibilite.contains(heureFin)
                && (disponibilite.contains(heureFin + 1) || heureFin.equals(20))) { //Heure de maintenance
            return true;
        } else {
            return false;
        }
    }

    public boolean hasRequiredEquipement(Reunion reunion, ArrayList<Stock> stock) {
        boolean hasRequiredEquipement = true;
        for (Equipement equipement : reunion.getEquipementRequis()) {
            if (!this.getEquipementPresent().contains(equipement)) {

                for (Stock inventaire : stock) {
                    if (inventaire.getTypeEquipement().equals(equipement) && inventaire.getNbEnStock() > 0) {
                        hasRequiredEquipement = true;
                        inventaire.setNbEnStock(inventaire.getNbEnStock() - 1);
                    } else {
                        hasRequiredEquipement = false;
                    }
                }
            }
        }
        return hasRequiredEquipement;
    }


}
