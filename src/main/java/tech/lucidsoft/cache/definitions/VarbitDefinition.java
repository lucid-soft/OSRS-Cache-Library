package tech.lucidsoft.cache.definitions;

public class VarbitDefinition {

    private int id;
    private int index;
    private int leastSignificantBit;
    private int mostSignificantBit;

    public VarbitDefinition() {
        setDefaults();
    }

    public VarbitDefinition(int id) {
        this.setId(id);
        setDefaults();
    }

    public void setDefaults() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLeastSignificantBit() {
        return leastSignificantBit;
    }

    public void setLeastSignificantBit(int leastSignificantBit) {
        this.leastSignificantBit = leastSignificantBit;
    }

    public int getMostSignificantBit() {
        return mostSignificantBit;
    }

    public void setMostSignificantBit(int mostSignificantBit) {
        this.mostSignificantBit = mostSignificantBit;
    }
}
