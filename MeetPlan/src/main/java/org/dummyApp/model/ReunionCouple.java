package org.dummyApp.model;

public class ReunionCouple extends Reunion {
    public ReunionCouple(Integer heureDebut, Integer heureFin, Integer nbParticipants) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.nbParticipants = nbParticipants;
        this.reunionType = ReunionType.RC;
        this.equipementRequis.add(Equipement.TABLEAU);
        this.equipementRequis.add(Equipement.ECRAN);
        this.equipementRequis.add(Equipement.PIEUVRE);
        this.nbEquipementRequis = 3;
    }
}
