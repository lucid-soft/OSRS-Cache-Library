package tech.lucidsoft.cache.definitions;


import java.util.HashMap;

/**
 * Contains data related to Object configs(a.k.a definitions/defs)
 */
public class ObjectDefinition {

    private int id;
    private int category;
    private String name;
    private int varbit;
    private int optionsInvisible;
    private int[] models;
    private int[] types;
    private int[] transformedIds;
    private int ambientSoundId;
    private int varp;
    private int supportItems;
    private int[] anIntArray100;
    private int mapIconId;
    private int sizeX;
    private int clipType;
    private boolean isRotated;
    private int sizeY;
    private boolean projectileClip;
    private int anInt455;
    private boolean nonFlatShading;
    private int contouredGround;
    private int anInt456;
    private boolean modelClipped;
    private int ambient;
    private String[] options;
    private int contrast;
    private int anInt457;
    private boolean hollow;
    private int animationId;
    private int modelSizeX;
    private int decorDisplacement;
    private int modelSizeHeight;
    private int modelSizeY;
    private int[] modelColours;
    private boolean clipped;
    private short[] modelTexture;
    private int mapSceneId;
    private int[] replacementColours;
    private int offsetX;
    private short[] replacementTextures;
    private int offsetHeight;
    private int offsetY;
    private boolean obstructsGround;
    private int accessBlockFlag;
    private int finalTransformation;
    private boolean randomizedAnimStart;
    private HashMap<Integer, Object> parameters;

    public ObjectDefinition() {
        setDefaults();
    }

    public ObjectDefinition(int id) {
        setDefaults();
        this.id = id;
    }

    private void setDefaults() {
        name = "null";
        sizeX = 1;
        sizeY = 1;
        clipType = 2;
        projectileClip = true;
        optionsInvisible = -1;
        contouredGround = -1;
        nonFlatShading = false;
        modelClipped = false;
        animationId = -1;
        decorDisplacement = 16;
        ambient = 0;
        contrast = 0;
        options = new String[5];
        mapIconId = -1;
        mapSceneId = -1;
        isRotated = false;
        clipped = true;
        modelSizeX = 128;
        modelSizeHeight = 128;
        modelSizeY = 128;
        offsetX = 0;
        offsetHeight = 0;
        offsetY = 0;
        obstructsGround = false;
        hollow = false;
        supportItems = -1;
        varbit = -1;
        varp = -1;
        ambientSoundId = -1;
        anInt455 = 0;
        anInt456 = 0;
        anInt457 = 0;
        parameters = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVarbit() {
        return varbit;
    }

    public void setVarbit(int varbit) {
        this.varbit = varbit;
    }

    public int getOptionsInvisible() {
        return optionsInvisible;
    }

    public void setOptionsInvisible(int optionsInvisible) {
        this.optionsInvisible = optionsInvisible;
    }

    public int[] getModels() {
        return models;
    }

    public void setModels(int[] models) {
        this.models = models;
    }

    public int[] getTypes() {
        return types;
    }

    public void setTypes(int[] types) {
        this.types = types;
    }

    public int[] getTransformedIds() {
        return transformedIds;
    }

    public void setTransformedIds(int[] transformedIds) {
        this.transformedIds = transformedIds;
    }

    public int getAmbientSoundId() {
        return ambientSoundId;
    }

    public void setAmbientSoundId(int ambientSoundId) {
        this.ambientSoundId = ambientSoundId;
    }

    public int getVarp() {
        return varp;
    }

    public void setVarp(int varp) {
        this.varp = varp;
    }

    public int getSupportItems() {
        return supportItems;
    }

    public void setSupportItems(int supportItems) {
        this.supportItems = supportItems;
    }

    public int[] getAnIntArray100() {
        return anIntArray100;
    }

    public void setAnIntArray100(int[] anIntArray100) {
        this.anIntArray100 = anIntArray100;
    }

    public int getMapIconId() {
        return mapIconId;
    }

    public void setMapIconId(int mapIconId) {
        this.mapIconId = mapIconId;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getClipType() {
        return clipType;
    }

    public void setClipType(int clipType) {
        this.clipType = clipType;
    }

    public boolean isRotated() {
        return isRotated;
    }

    public void setRotated(boolean rotated) {
        isRotated = rotated;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public boolean isProjectileClip() {
        return projectileClip;
    }

    public void setProjectileClip(boolean projectileClip) {
        this.projectileClip = projectileClip;
    }

    public int getAnInt455() {
        return anInt455;
    }

    public void setAnInt455(int anInt455) {
        this.anInt455 = anInt455;
    }

    public boolean isNonFlatShading() {
        return nonFlatShading;
    }

    public void setNonFlatShading(boolean nonFlatShading) {
        this.nonFlatShading = nonFlatShading;
    }

    public int getContouredGround() {
        return contouredGround;
    }

    public void setContouredGround(int contouredGround) {
        this.contouredGround = contouredGround;
    }

    public int getAnInt456() {
        return anInt456;
    }

    public void setAnInt456(int anInt456) {
        this.anInt456 = anInt456;
    }

    public boolean isModelClipped() {
        return modelClipped;
    }

    public void setModelClipped(boolean modelClipped) {
        this.modelClipped = modelClipped;
    }

    public int getAmbient() {
        return ambient;
    }

    public void setAmbient(int ambient) {
        this.ambient = ambient;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getContrast() {
        return contrast;
    }

    public void setContrast(int contrast) {
        this.contrast = contrast;
    }

    public int getAnInt457() {
        return anInt457;
    }

    public void setAnInt457(int anInt457) {
        this.anInt457 = anInt457;
    }

    public boolean isHollow() {
        return hollow;
    }

    public void setHollow(boolean hollow) {
        this.hollow = hollow;
    }

    public int getAnimationId() {
        return animationId;
    }

    public void setAnimationId(int animationId) {
        this.animationId = animationId;
    }

    public int getModelSizeX() {
        return modelSizeX;
    }

    public void setModelSizeX(int modelSizeX) {
        this.modelSizeX = modelSizeX;
    }

    public int getDecorDisplacement() {
        return decorDisplacement;
    }

    public void setDecorDisplacement(int decorDisplacement) {
        this.decorDisplacement = decorDisplacement;
    }

    public int getModelSizeHeight() {
        return modelSizeHeight;
    }

    public void setModelSizeHeight(int modelSizeHeight) {
        this.modelSizeHeight = modelSizeHeight;
    }

    public int getModelSizeY() {
        return modelSizeY;
    }

    public void setModelSizeY(int modelSizeY) {
        this.modelSizeY = modelSizeY;
    }

    public int[] getModelColours() {
        return modelColours;
    }

    public void setModelColours(int[] modelColours) {
        this.modelColours = modelColours;
    }

    public boolean isClipped() {
        return clipped;
    }

    public void setClipped(boolean clipped) {
        this.clipped = clipped;
    }

    public short[] getModelTexture() {
        return modelTexture;
    }

    public void setModelTexture(short[] modelTexture) {
        this.modelTexture = modelTexture;
    }

    public int getMapSceneId() {
        return mapSceneId;
    }

    public void setMapSceneId(int mapSceneId) {
        this.mapSceneId = mapSceneId;
    }

    public int[] getReplacementColours() {
        return replacementColours;
    }

    public void setReplacementColours(int[] replacementColours) {
        this.replacementColours = replacementColours;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public short[] getReplacementTextures() {
        return replacementTextures;
    }

    public void setReplacementTextures(short[] replacementTextures) {
        this.replacementTextures = replacementTextures;
    }

    public int getOffsetHeight() {
        return offsetHeight;
    }

    public void setOffsetHeight(int offsetHeight) {
        this.offsetHeight = offsetHeight;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public boolean isObstructsGround() {
        return obstructsGround;
    }

    public void setObstructsGround(boolean obstructsGround) {
        this.obstructsGround = obstructsGround;
    }

    public int getAccessBlockFlag() {
        return accessBlockFlag;
    }

    public void setAccessBlockFlag(int accessBlockFlag) {
        this.accessBlockFlag = accessBlockFlag;
    }

    public int getFinalTransformation() {
        return finalTransformation;
    }

    public void setFinalTransformation(int finalTransformation) {
        this.finalTransformation = finalTransformation;
    }

    public boolean isRandomizedAnimStart() {
        return randomizedAnimStart;
    }

    public void setRandomizedAnimStart(boolean randomizedAnimStart) {
        this.randomizedAnimStart = randomizedAnimStart;
    }

    public HashMap<Integer, Object> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<Integer, Object> parameters) {
        this.parameters = parameters;
    }
}
