package org.dummyApp.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reunion {
    protected ArrayList<Equipement> equipementRequis = new ArrayList<Equipement>();
    protected Integer nbParticipants;
    protected Integer nbEquipementRequis;
    protected Integer heureDebut;
    protected Integer heureFin;

    protected ReunionType reunionType;

}
