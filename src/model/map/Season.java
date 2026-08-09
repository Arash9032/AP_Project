package model.map;

public enum Season {
    SPRING,
    SUMMER,
    FALL,
    WINTER;

    private final static Season[] VALUES = values();

    public Season getNextSeason(){
        return VALUES[(this.ordinal() + 1) % VALUES.length];
    }
}
