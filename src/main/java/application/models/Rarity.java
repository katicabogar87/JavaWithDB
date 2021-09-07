package application.models;

public enum Rarity {

    COMMON,
    RARE,
    VERY_RARE,
    EPIC,
    LEGENDARY,
    HEROIC;

    public int getDBIndex(){
        return ordinal()+1;
    }

    }
