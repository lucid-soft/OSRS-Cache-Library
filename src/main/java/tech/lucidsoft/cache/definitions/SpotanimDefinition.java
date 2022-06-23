package tech.lucidsoft.cache.definitions;

public class SpotanimDefinition {

    private int id;
    private int modelId;
    private int animationId;
    private int resizeX;
    private int resizeY;
    private int rotation;
    private int ambient;
    private int contrast;
    private short[] originalColours;
    private short[] replacementColours;
    private short[] originalTextures;
    private short[] replacementTextures;

    public SpotanimDefinition() {
        setDefaults();
    }

    public SpotanimDefinition(int id) {
        setDefaults();
        setId(id);
    }

    public void setDefaults() {
        setResizeY(128);
        setResizeX(128);
        setAnimationId(-1);
        setAmbient(0);
        setContrast(0);
        setRotation(0);
        originalColours = new short[0];
        replacementColours = new short[0];
        originalTextures = new short[0];
        replacementTextures = new short[0];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getAnimationId() {
        return animationId;
    }

    public void setAnimationId(int animationId) {
        this.animationId = animationId;
    }

    public int getResizeX() {
        return resizeX;
    }

    public void setResizeX(int resizeX) {
        this.resizeX = resizeX;
    }

    public int getResizeY() {
        return resizeY;
    }

    public void setResizeY(int resizeY) {
        this.resizeY = resizeY;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getAmbient() {
        return ambient;
    }

    public void setAmbient(int ambient) {
        this.ambient = ambient;
    }

    public int getContrast() {
        return contrast;
    }

    public void setContrast(int contrast) {
        this.contrast = contrast;
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
}
