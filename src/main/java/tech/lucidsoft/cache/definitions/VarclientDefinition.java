package tech.lucidsoft.cache.definitions;

public class VarclientDefinition {

    private int id;
    private boolean persists;

    public VarclientDefinition() {
        setDefaults();
    }

    public VarclientDefinition(int id) {
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

    public boolean isPersists() {
        return persists;
    }

    public void setPersists(boolean persists) {
        this.persists = persists;
    }
}
