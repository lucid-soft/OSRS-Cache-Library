package tech.lucidsoft.cache.definitions;

import java.util.Map;

public class ItemDefinition {

    private String name;
    private int id;
    private int category;
    private String[] inventoryOptions;
    private String[] groundOptions;
    private boolean grandExchange;
    private boolean isMembers;
    private int isStackable;
    private int price;
    private int notedTemplate;
    private int notedId;
    private int bindTemplateId;
    private int bindId;
    private int placeholderTemplate;
    private int placeholderId;
    private int[] stackIds;
    private int[] stackAmounts;
    private int maleOffset;
    private int primaryMaleHeadModelId;
    private int secondaryMaleHeadModelId;
    private int primaryMaleModel;
    private int secondaryMaleModel;
    private int tertiaryMaleModel;
    private int femaleOffset;
    private int primaryFemaleHeadModelId;
    private int secondaryFemaleHeadModelId;
    private int primaryFemaleModel;
    private int secondaryFemaleModel;
    private int tertiaryFemaleModel;
    private int inventoryModelId;
    private int shiftClickIndex;
    private int teamId;
    private int zoom;
    private int offsetX;
    private int offsetY;
    private int modelPitch;
    private int modelRoll;
    private int modelYaw;
    private int resizeX;
    private int resizeY;
    private int resizeZ;
    private short[] originalColours;
    private short[] replacementColours;
    private short[] originalTextures;
    private short[] replacementTextures;
    private int ambient;
    private int contrast;
    private Map<Integer, Object> parameters;

    public ItemDefinition() {
        setDefaults();
    }

    public ItemDefinition(int id) {
        setDefaults();
        this.setId(id);
    }

    public void setDefaults() {
        setName("null");
        setCategory(-1);
        setZoom(2000);
        setModelPitch(0);
        setModelRoll(0);
        setModelYaw(0);
        setOffsetX(0);
        setOffsetY(0);
        setIsStackable(0);
        setPrice(1);
        setMembers(false);
        setGroundOptions(new String[]{null, null, "Take", null, null});
        setInventoryOptions(new String[]{null, null, null, null, "Drop"});
        setShiftClickIndex(-2);
        setPrimaryMaleModel(-1);
        setSecondaryMaleModel(-1);
        setMaleOffset(0);
        setPrimaryFemaleModel(-1);
        setSecondaryFemaleModel(-1);
        setFemaleOffset(0);
        setTertiaryMaleModel(-1);
        setTertiaryFemaleModel(-1);
        setPrimaryMaleHeadModelId(-1);
        setSecondaryMaleHeadModelId(-1);
        setPrimaryFemaleHeadModelId(-1);
        setSecondaryFemaleHeadModelId(-1);
        setNotedId(-1);
        setNotedTemplate(-1);
        setResizeX(128);
        setResizeY(128);
        setResizeZ(128);
        setAmbient(0);
        setContrast(0);
        setTeamId(0);
        setGrandExchange(false);
        setBindId(-1);
        setBindTemplateId(-1);
        setPlaceholderId(-1);
        setPlaceholderTemplate(-1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getInventoryOptions() {
        return inventoryOptions;
    }

    public void setInventoryOptions(String[] inventoryOptions) {
        this.inventoryOptions = inventoryOptions;
    }

    public String[] getGroundOptions() {
        return groundOptions;
    }

    public void setGroundOptions(String[] groundOptions) {
        this.groundOptions = groundOptions;
    }

    public boolean isGrandExchange() {
        return grandExchange;
    }

    public void setGrandExchange(boolean grandExchange) {
        this.grandExchange = grandExchange;
    }

    public boolean isMembers() {
        return isMembers;
    }

    public void setMembers(boolean members) {
        isMembers = members;
    }

    public int getIsStackable() {
        return isStackable;
    }

    public void setIsStackable(int isStackable) {
        this.isStackable = isStackable;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNotedTemplate() {
        return notedTemplate;
    }

    public void setNotedTemplate(int notedTemplate) {
        this.notedTemplate = notedTemplate;
    }

    public int getNotedId() {
        return notedId;
    }

    public void setNotedId(int notedId) {
        this.notedId = notedId;
    }

    public int getBindTemplateId() {
        return bindTemplateId;
    }

    public void setBindTemplateId(int bindTemplateId) {
        this.bindTemplateId = bindTemplateId;
    }

    public int getBindId() {
        return bindId;
    }

    public void setBindId(int bindId) {
        this.bindId = bindId;
    }

    public int getPlaceholderTemplate() {
        return placeholderTemplate;
    }

    public void setPlaceholderTemplate(int placeholderTemplate) {
        this.placeholderTemplate = placeholderTemplate;
    }

    public int getPlaceholderId() {
        return placeholderId;
    }

    public void setPlaceholderId(int placeholderId) {
        this.placeholderId = placeholderId;
    }

    public int[] getStackIds() {
        return stackIds;
    }

    public void setStackIds(int[] stackIds) {
        this.stackIds = stackIds;
    }

    public int[] getStackAmounts() {
        return stackAmounts;
    }

    public void setStackAmounts(int[] stackAmounts) {
        this.stackAmounts = stackAmounts;
    }

    public int getMaleOffset() {
        return maleOffset;
    }

    public void setMaleOffset(int maleOffset) {
        this.maleOffset = maleOffset;
    }

    public int getPrimaryMaleHeadModelId() {
        return primaryMaleHeadModelId;
    }

    public void setPrimaryMaleHeadModelId(int primaryMaleHeadModelId) {
        this.primaryMaleHeadModelId = primaryMaleHeadModelId;
    }

    public int getSecondaryMaleHeadModelId() {
        return secondaryMaleHeadModelId;
    }

    public void setSecondaryMaleHeadModelId(int secondaryMaleHeadModelId) {
        this.secondaryMaleHeadModelId = secondaryMaleHeadModelId;
    }

    public int getPrimaryMaleModel() {
        return primaryMaleModel;
    }

    public void setPrimaryMaleModel(int primaryMaleModel) {
        this.primaryMaleModel = primaryMaleModel;
    }

    public int getSecondaryMaleModel() {
        return secondaryMaleModel;
    }

    public void setSecondaryMaleModel(int secondaryMaleModel) {
        this.secondaryMaleModel = secondaryMaleModel;
    }

    public int getTertiaryMaleModel() {
        return tertiaryMaleModel;
    }

    public void setTertiaryMaleModel(int tertiaryMaleModel) {
        this.tertiaryMaleModel = tertiaryMaleModel;
    }

    public int getFemaleOffset() {
        return femaleOffset;
    }

    public void setFemaleOffset(int femaleOffset) {
        this.femaleOffset = femaleOffset;
    }

    public int getPrimaryFemaleHeadModelId() {
        return primaryFemaleHeadModelId;
    }

    public void setPrimaryFemaleHeadModelId(int primaryFemaleHeadModelId) {
        this.primaryFemaleHeadModelId = primaryFemaleHeadModelId;
    }

    public int getSecondaryFemaleHeadModelId() {
        return secondaryFemaleHeadModelId;
    }

    public void setSecondaryFemaleHeadModelId(int secondaryFemaleHeadModelId) {
        this.secondaryFemaleHeadModelId = secondaryFemaleHeadModelId;
    }

    public int getPrimaryFemaleModel() {
        return primaryFemaleModel;
    }

    public void setPrimaryFemaleModel(int primaryFemaleModel) {
        this.primaryFemaleModel = primaryFemaleModel;
    }

    public int getSecondaryFemaleModel() {
        return secondaryFemaleModel;
    }

    public void setSecondaryFemaleModel(int secondaryFemaleModel) {
        this.secondaryFemaleModel = secondaryFemaleModel;
    }

    public int getTertiaryFemaleModel() {
        return tertiaryFemaleModel;
    }

    public void setTertiaryFemaleModel(int tertiaryFemaleModel) {
        this.tertiaryFemaleModel = tertiaryFemaleModel;
    }

    public int getInventoryModelId() {
        return inventoryModelId;
    }

    public void setInventoryModelId(int inventoryModelId) {
        this.inventoryModelId = inventoryModelId;
    }

    public int getShiftClickIndex() {
        return shiftClickIndex;
    }

    public void setShiftClickIndex(int shiftClickIndex) {
        this.shiftClickIndex = shiftClickIndex;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getModelPitch() {
        return modelPitch;
    }

    public void setModelPitch(int modelPitch) {
        this.modelPitch = modelPitch;
    }

    public int getModelRoll() {
        return modelRoll;
    }

    public void setModelRoll(int modelRoll) {
        this.modelRoll = modelRoll;
    }

    public int getModelYaw() {
        return modelYaw;
    }

    public void setModelYaw(int modelYaw) {
        this.modelYaw = modelYaw;
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

    public int getResizeZ() {
        return resizeZ;
    }

    public void setResizeZ(int resizeZ) {
        this.resizeZ = resizeZ;
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

    public Map<Integer, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<Integer, Object> parameters) {
        this.parameters = parameters;
    }

}
