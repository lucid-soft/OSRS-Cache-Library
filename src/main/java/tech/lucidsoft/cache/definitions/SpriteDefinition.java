package tech.lucidsoft.cache.definitions;

public class SpriteDefinition {

    private int id;
    private int frame;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;
    private int[] pixels;
    private int maxWidth;
    private int maxHeight;

    public transient byte[] pixelIdx;
    public transient int[] palette;

    public void normalize()
    {
        if (this.width != this.maxWidth || this.height != this.maxHeight)
        {
            byte[] var1 = new byte[this.maxWidth * this.maxHeight];
            int var2 = 0;

            for (int var3 = 0; var3 < this.height; ++var3)
            {
                for (int var4 = 0; var4 < this.width; ++var4)
                {
                    var1[var4 + (var3 + this.offsetY) * this.maxWidth + this.offsetX] = this.pixelIdx[var2++];
                }
            }

            this.pixelIdx = var1;
            this.width = this.maxWidth;
            this.height = this.maxHeight;
            this.offsetX = 0;
            this.offsetY = 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public byte[] getPixelIdx() {
        return pixelIdx;
    }

    public void setPixelIdx(byte[] pixelIdx) {
        this.pixelIdx = pixelIdx;
    }

    public int[] getPalette() {
        return palette;
    }

    public void setPalette(int[] palette) {
        this.palette = palette;
    }
}
