package tech.lucidsoft.cache.definitions;

public class IdentikitDefinition {

    private int id;
    private short[] originalColours;
    private short[] replacementColours;
    private short[] originalTextures;
    private short[] replacementTextures;
    private int bodyPartId;
    private int[] models;
    private int[] chatheadModels;
    private boolean nonSelectable;

    public IdentikitDefinition() {
        setDefaults();
    }

    public IdentikitDefinition(int id) {
        this.setId(id);
        setDefaults();
    }

    public void setDefaults() {
        setBodyPartId(-1);
        setChatheadModels(new int[] { -1, -1, -1, -1, -1 });
        setNonSelectable(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short[] getOriginalColours() {
        return originalColours;
    }

    public void setOriginalColours(short[] originalColours) {
        this.originalColours = originalColours;
    }

    public short[] getReplacementColours() {
        return replacementColours;
    }

    public void setReplacementColours(short[] replacementColours) {
        this.replacementColours = replacementColours;
    }

    public short[] getOriginalTextures() {
        return originalTextures;
    }

    public void setOriginalTextures(short[] originalTextures) {
        this.originalTextures = originalTextures;
    }

    public short[] getReplacementTextures() {
        return replacementTextures;
    }

    public void setReplacementTextures(short[] replacementTextures) {
        this.replacementTextures = replacementTextures;
    }

    public int getBodyPartId() {
        return bodyPartId;
    }

    public void setBodyPartId(int bodyPartId) {
        this.bodyPartId = bodyPartId;
    }

    public int[] getModels() {
        return models;
    }

    public void setModels(int[] models) {
        this.models = models;
    }

    public int[] getChatheadModels() {
        return chatheadModels;
    }

    public void setChatheadModels(int[] chatheadModels) {
        this.chatheadModels = chatheadModels;
    }

    public boolean isNonSelectable() {
        return nonSelectable;
    }

    public void setNonSelectable(boolean nonSelectable) {
        this.nonSelectable = nonSelectable;
    }
}
