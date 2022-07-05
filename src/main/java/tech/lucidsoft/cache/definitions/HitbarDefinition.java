package tech.lucidsoft.cache.definitions;

public class HitbarDefinition {

    private int id;
    private int unknownInt1 = 255;
    private int unknownInt2 = 255;
    private int cycles = -1;
    private int unknownInt3 = 70;
    private int healthBarFrontSpriteId = -1;
    private int healthBarBackSpriteId = -1;
    private int healthScale = 30;
    private int healthBarPadding = 0;

    public HitbarDefinition() {
        setDefaults();
    }

    public HitbarDefinition(int id) {
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

    public int getUnknownInt1() {
        return unknownInt1;
    }

    public void setUnknownInt1(int unknownInt1) {
        this.unknownInt1 = unknownInt1;
    }

    public int getUnknownInt2() {
        return unknownInt2;
    }

    public void setUnknownInt2(int unknownInt2) {
        this.unknownInt2 = unknownInt2;
    }

    public int getUnknownInt3() {
        return unknownInt3;
    }

    public void setUnknownInt3(int unknownInt3) {
        this.unknownInt3 = unknownInt3;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public int getHealthBarFrontSpriteId() {
        return healthBarFrontSpriteId;
    }

    public void setHealthBarFrontSpriteId(int healthBarFrontSpriteId) {
        this.healthBarFrontSpriteId = healthBarFrontSpriteId;
    }

    public int getHealthBarBackSpriteId() {
        return healthBarBackSpriteId;
    }

    public void setHealthBarBackSpriteId(int healthBarBackSpriteId) {
        this.healthBarBackSpriteId = healthBarBackSpriteId;
    }

    public int getHealthScale() {
        return healthScale;
    }

    public void setHealthScale(int healthScale) {
        this.healthScale = healthScale;
    }

    public int getHealthBarPadding() {
        return healthBarPadding;
    }

    public void setHealthBarPadding(int healthBarPadding) {
        this.healthBarPadding = healthBarPadding;
    }
}
