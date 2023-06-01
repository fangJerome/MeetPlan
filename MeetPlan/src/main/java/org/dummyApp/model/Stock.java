package org.dummyApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stock {
    private Equipement typeEquipement;
    private Integer nbEnStock;

    public Stock(Equipement typeEquipement, Integer nbEnStock) {
        this.typeEquipement = typeEquipement;
        this.nbEnStock = nbEnStock;
    }
}
