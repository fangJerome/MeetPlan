package org.dummyApp.model;

public class Visio extends Reunion {

    public Visio(Integer heureDebut, Integer heureFin, Integer nbParticipants) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.nbParticipants = nbParticipants;
        this.reunionType = ReunionType.VISIO;
        this.equipementRequis.add(Equipement.ECRAN);
        this.equipementRequis.add(Equipement.PIEUVRE);
        this.equipementRequis.add(Equipement.WEBCAM);
        this.nbEquipementRequis = 3;
    }
}
