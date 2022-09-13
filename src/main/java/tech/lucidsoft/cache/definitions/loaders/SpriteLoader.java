package tech.lucidsoft.cache.definitions.loaders;

import tech.lucidsoft.cache.definitions.SpriteDefinition;
import tech.lucidsoft.cache.io.ByteBuffer;

public class SpriteLoader {

    public static final int FLAG_VERTICAL = 0b01;
    public static final int FLAG_ALPHA    = 0b10;

    public SpriteDefinition[] load(int id, ByteBuffer data) {
        ByteBuffer is = data;

        is.setPosition(is.limit() - 2);
        int spriteCount = is.readUnsignedShort();

        SpriteDefinition[] sprites = new SpriteDefinition[spriteCount];

        // 2 for size
        // 5 for width, height, palette length
        // + 8 bytes per sprite for offset x/y, width, and height
        is.setPosition(is.limit() - 7 - spriteCount * 8);

        // max width and height
        int width = is.readUnsignedShort();
        int height = is.readUnsignedShort();
        int paletteLength = is.readUnsignedByte() + 1;

        for (int i = 0; i < spriteCount; ++i)
        {
            sprites[i] = new SpriteDefinition();
            sprites[i].setId(id);
            sprites[i].setFrame(i);
            sprites[i].setMaxWidth(width);
            sprites[i].setMaxHeight(height);
        }

        for (int i = 0; i < spriteCount; ++i)
        {
            sprites[i].setOffsetX(is.readUnsignedShort());
        }

        for (int i = 0; i < spriteCount; ++i)
        {
            sprites[i].setOffsetY(is.readUnsignedShort());
        }

        for (int i = 0; i < spriteCount; ++i)
        {
            sprites[i].setWidth(is.readUnsignedShort());
        }

        for (int i = 0; i < spriteCount; ++i)
        {
            sprites[i].setHeight(is.readUnsignedShort());
        }

        // same as above + 3 bytes for each palette entry, except for the first one (which is transparent)
        is.setPosition(is.limit() - 7 - spriteCount * 8 - (paletteLength - 1) * 3);
        int[] palette = new int[paletteLength];

        for (int i = 1; i < paletteLength; ++i)
        {
            palette[i] = is.readUnsignedMedInt();

            if (palette[i] == 0)
            {
                palette[i] = 1;
            }
        }

        is.setPosition(0);

        for (int i = 0; i < spriteCount; ++i)
        {
            SpriteDefinition def = sprites[i];
            int spriteWidth = def.getWidth();
            int spriteHeight = def.getHeight();
            int dimension = spriteWidth * spriteHeight;
            byte[] pixelPaletteIndicies = new byte[dimension];
            byte[] pixelAlphas = new byte[dimension];
            def.pixelIdx = pixelPaletteIndicies;
            def.palette = palette;

            int flags = is.readUnsignedByte();

            if ((flags & FLAG_VERTICAL) == 0)
            {
                // read horizontally
                for (int j = 0; j < dimension; ++j)
                {
                    pixelPaletteIndicies[j] = is.readByte();
                }
            }
            else
            {
                // read vertically
                for (int j = 0; j < spriteWidth; ++j)
                {
                    for (int k = 0; k < spriteHeight; ++k)
                    {
                        pixelPaletteIndicies[spriteWidth * k + j] = is.readByte();
                    }
                }
            }

            // read alphas
            if ((flags & FLAG_ALPHA) != 0)
            {
                if ((flags & FLAG_VERTICAL) == 0)
                {
                    // read horizontally
                    for (int j = 0; j < dimension; ++j)
                    {
                        pixelAlphas[j] = is.readByte();
                    }
                }
                else
                {
                    // read vertically
                    for (int j = 0; j < spriteWidth; ++j)
                    {
                        for (int k = 0; k < spriteHeight; ++k)
                        {
                            pixelAlphas[spriteWidth * k + j] = is.readByte();
                        }
                    }
                }
            }
            else
            {
                // everything non-zero is opaque
                for (int j = 0; j < dimension; ++j)
                {
                    int index = pixelPaletteIndicies[j];

                    if (index != 0)
                        pixelAlphas[j] = (byte) 0xFF;
                }
            }

            int[] pixels = new int[dimension];

            // build argb pixels from palette/alphas
            for (int j = 0; j < dimension; ++j)
            {
                int index = pixelPaletteIndicies[j] & 0xFF;

                pixels[j] = palette[index] | (pixelAlphas[j] << 24);
            }

            def.setPixels(pixels);
        }

        return sprites;
    }
}
