package tech.lucidsoft.cache.definitions;

public class OverlayDefinition {

    private int id;
    private int colour;
    private int texture;
    private boolean hideUnderlay;
    private int secondaryColour;

    public OverlayDefinition() {
        setDefaults();
    }

    public OverlayDefinition(int id) {
        setDefaults();
        this.setId(id);
    }

    private void setDefaults() {
        setColour(0);
        setTexture(-1);
        setSecondaryColour(-1);
        setHideUnderlay(true);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public int getTexture() {
        return texture;
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public boolean isHideUnderlay() {
        return hideUnderlay;
    }

    public void setHideUnderlay(boolean hideUnderlay) {
        this.hideUnderlay = hideUnderlay;
    }

    public int getSecondaryColour() {
        return secondaryColour;
    }

    public void setSecondaryColour(int secondaryColour) {
        this.secondaryColour = secondaryColour;
    }
}
