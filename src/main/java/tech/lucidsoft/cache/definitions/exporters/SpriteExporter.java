package tech.lucidsoft.cache.definitions.exporters;

import tech.lucidsoft.cache.definitions.SpriteDefinition;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class SpriteExporter
{
    private final SpriteDefinition sprite;

    public SpriteExporter(SpriteDefinition sprite)
    {
        this.sprite = sprite;
    }

    public BufferedImage export()
    {
        BufferedImage bi = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
        bi.setRGB(0, 0, sprite.getWidth(), sprite.getHeight(), sprite.getPixels(), 0, sprite.getWidth());
        return bi;
    }

    public void exportTo(File file) throws IOException
    {
        BufferedImage image = export();
        ImageIO.write(image, "png", file);
    }
}