package tech.lucidsoft.cache.definitions;

public class HitmarkDefinition {

    private int id;
    private String stringFormat = "";
    private int varbitID = -1;
    private int leftSprite = -1;
    private int leftSprite2 = -1;
    private int rightSpriteId = -1;
    private int fontType = -1;
    private int backgroundSprite = -1;
    private int varpID = -1;
    private int useDamage = -1;
    private int textColor = 0xFFFFFF;
    private int displayCycles = 70;
    private int[] multihitsplats;
    private int scrollToOffsetX = 0;
    private int fadeStartCycle = -1;
    private int scrollToOffsetY = 0;
    private int textOffsetY = 0;

    public HitmarkDefinition() {
        setDefaults();
    }

    public HitmarkDefinition(int id) {
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

    public String getStringFormat() {
        return stringFormat;
    }

    public void setStringFormat(String stringFormat) {
        this.stringFormat = stringFormat;
    }

    public int getVarbitID() {
        return varbitID;
    }

    public void setVarbitID(int varbitID) {
        this.varbitID = varbitID;
    }

    public int getLeftSprite() {
        return leftSprite;
    }

    public void setLeftSprite(int leftSprite) {
        this.leftSprite = leftSprite;
    }

    public int getLeftSprite2() {
        return leftSprite2;
    }

    public void setLeftSprite2(int leftSprite2) {
        this.leftSprite2 = leftSprite2;
    }

    public int getRightSpriteId() {
        return rightSpriteId;
    }

    public void setRightSpriteId(int rightSpriteId) {
        this.rightSpriteId = rightSpriteId;
    }

    public int getFontType() {
        return fontType;
    }

    public void setFontType(int fontType) {
        this.fontType = fontType;
    }

    public int getBackgroundSprite() {
        return backgroundSprite;
    }

    public void setBackgroundSprite(int backgroundSprite) {
        this.backgroundSprite = backgroundSprite;
    }

    public int getVarpID() {
        return varpID;
    }

    public void setVarpID(int varpID) {
        this.varpID = varpID;
    }

    public int getUseDamage() {
        return useDamage;
    }

    public void setUseDamage(int useDamage) {
        this.useDamage = useDamage;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getDisplayCycles() {
        return displayCycles;
    }

    public void setDisplayCycles(int displayCycles) {
        this.displayCycles = displayCycles;
    }

    public int[] getMultihitsplats() {
        return multihitsplats;
    }

    public void setMultihitsplats(int[] multihitsplats) {
        this.multihitsplats = multihitsplats;
    }

    public int getScrollToOffsetX() {
        return scrollToOffsetX;
    }

    public void setScrollToOffsetX(int scrollToOffsetX) {
        this.scrollToOffsetX = scrollToOffsetX;
    }

    public int getFadeStartCycle() {
        return fadeStartCycle;
    }

    public void setFadeStartCycle(int fadeStartCycle) {
        this.fadeStartCycle = fadeStartCycle;
    }

    public int getScrollToOffsetY() {
        return scrollToOffsetY;
    }

    public void setScrollToOffsetY(int scrollToOffsetY) {
        this.scrollToOffsetY = scrollToOffsetY;
    }

    public int getTextOffsetY() {
        return textOffsetY;
    }

    public void setTextOffsetY(int textOffsetY) {
        this.textOffsetY = textOffsetY;
    }
}
