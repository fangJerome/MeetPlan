package org.dummyApp.model;

public class Spec extends Reunion{

    public Spec(Integer heureDebut, Integer heureFin, Integer nbParticipants) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.nbParticipants = nbParticipants;
        this.reunionType=ReunionType.SPEC;
        this.equipementRequis.add(Equipement.TABLEAU);
        this.nbEquipementRequis = 1;
    }
}
