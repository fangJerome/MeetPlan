package org.dummyApp.model;

public class ReunionSimple extends Reunion{
    public ReunionSimple(Integer heureDebut, Integer heureFin, Integer nbParticipants) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.nbParticipants = nbParticipants;
        this.reunionType = ReunionType.RS;
        this.equipementRequis.add(Equipement.NEANT);
        this.nbEquipementRequis = 0;
    }
}
