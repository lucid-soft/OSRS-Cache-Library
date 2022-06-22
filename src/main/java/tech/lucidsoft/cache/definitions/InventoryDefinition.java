package tech.lucidsoft.cache.definitions;

public class InventoryDefinition {

    private int id;
    private int size;

    public InventoryDefinition() {
        setDefaults();
    }

    public InventoryDefinition(int id) {
        setDefaults();
        setId(id);
    }

    public void setDefaults() {
        setSize(0);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
