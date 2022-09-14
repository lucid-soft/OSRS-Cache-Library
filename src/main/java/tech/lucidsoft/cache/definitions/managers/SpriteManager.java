package tech.lucidsoft.cache.definitions.managers;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.definitions.SpriteDefinition;
import tech.lucidsoft.cache.definitions.exporters.SpriteExporter;
import tech.lucidsoft.cache.definitions.loaders.SpriteLoader;
import tech.lucidsoft.cache.filesystem.Archive;
import tech.lucidsoft.cache.filesystem.Cache;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteManager {

    private final Archive spriteArchive;
    private final Multimap<Integer, SpriteDefinition> sprites = LinkedListMultimap.create();
    private static boolean verbose = false;

    public SpriteManager(Cache cache) {
        this.spriteArchive = cache.getArchive(ArchiveType.SPRITES);
    }

    public void load() {
        for (int i = 0; i < spriteArchive.getGroups().length; i++) {
            SpriteLoader spriteLoader = new SpriteLoader();
            if (spriteArchive.findGroupByID(i) != null) {
                SpriteDefinition[] definition = spriteLoader.load(i, spriteArchive.findGroupByID(i).getFiles()[0].getData());

                for (SpriteDefinition sprite : definition) {
                    sprites.put(sprite.getId(), sprite);
                }
            }

        }

        if (isVerbose()) {
            System.out.println("Loaded " + String.format( "%,d", sprites.size()) + " Sprites.");
        }
    }

    public BufferedImage getSpriteImage(SpriteDefinition sprite)
    {
        BufferedImage image = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
        image.setRGB(0, 0, sprite.getWidth(), sprite.getHeight(), sprite.getPixels(), 0, sprite.getWidth());
        return image;
    }

    public void export(File outDir)
    {
        for (SpriteDefinition sprite : sprites.values())
        {
            if (sprite.getHeight() <= 0 || sprite.getWidth() <= 0)
            {
                continue;
            }

            SpriteExporter exporter = new SpriteExporter(sprite);

            File png = new File(outDir, sprite.getId() + "-" + sprite.getFrame() + ".png");
            png.mkdirs();

            try {
                exporter.exportTo(png);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setVerbose(boolean verbose) {
        SpriteManager.verbose = verbose;
    }

    public static boolean isVerbose() {
        return verbose;
    }
}
