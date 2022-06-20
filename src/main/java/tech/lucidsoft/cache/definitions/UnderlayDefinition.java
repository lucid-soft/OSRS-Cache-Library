package tech.lucidsoft.cache.definitions;

public class UnderlayDefinition {

    private int id;
    private int rgb;

    public UnderlayDefinition() {
        setDefaults();
    }

    public UnderlayDefinition(int id) {
        this.setId(id);
        setDefaults();
    }

    public void setDefaults() {
        setRgb(0);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }
}
