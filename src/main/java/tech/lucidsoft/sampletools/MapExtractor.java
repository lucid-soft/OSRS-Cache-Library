package tech.lucidsoft.sampletools;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.entity.object.WorldObject;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.Group;
import tech.lucidsoft.cache.io.ByteBuffer;
import tech.lucidsoft.cache.util.DefUtil;
import tech.lucidsoft.cache.util.XTEALoader;

import java.io.*;
import java.util.*;

public class MapExtractor {

    private Cache cache;
    private static String cachePath;
    private static String dumpPath;

    private final int[] idsToDump = {15256};

    public static void main(String[] args) {
        new MapExtractor(DefUtil.MAP_EXTRACTOR_210_ARGS);
    }

    private MapExtractor(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient argument length. Arguments are: [cache input path], [dump output path]");
        }
        cachePath = args[0];
        dumpPath = args[1];
        cache = Cache.openCache(cachePath);

        try {
            XTEALoader.load();
        } catch (FileNotFoundException e) { }

        for (int id : idsToDump) {
            final int regionX = id >> 8;
            final int regionY = id & 0xFF;
            final int[] xteas = XTEALoader.getXTEAKeys(id);

            final Group mapGroup = cache.getArchive(ArchiveType.MAPS.getId()).findGroupByName("m" + regionX + "_" + regionY);
            final Group landscapeGroup = cache.getArchive(ArchiveType.MAPS.getId()).findGroupByName("l" + regionX + "_" + regionY, xteas);

            Collection<WorldObject> mapObjects = new ArrayList<>();
            List<Integer> usedIds = new ArrayList<>();

            if (mapGroup != null) {
                if (mapGroup.getFiles()[0] != null) {
                    DefUtil.dumpData(mapGroup.getFiles()[0].getData().getBuffer(), dumpPath + id + "/", "m" + regionX + "_" + regionY);
                }
            }

            if (landscapeGroup != null) {
                if (landscapeGroup.getFiles()[0] != null) {
                    DefUtil.dumpData(landscapeGroup.getFiles()[0].getData().getBuffer(), dumpPath + id + "/", "l" + regionX + "_" + regionY);
                    mapObjects = decode(landscapeGroup.getFiles()[0].getData());
                }
            }

            if (mapObjects != null && mapObjects.size() > 0) {
                // Add only unique ID's
                for (WorldObject worldObject : mapObjects) {
                    if (!usedIds.contains(worldObject.getId())) {
                        usedIds.add(worldObject.getId());
                    }
                }

                System.out.println("Total Objects: " + mapObjects.size() + " Unique: " + usedIds.size());
                usedIds.sort(Comparator.comparingInt(i -> i));
                for (int i : usedIds) {
                    System.out.println(i);
                }
            } else {
                if(mapObjects.size() == 0) {
                    System.out.println("No map objects in region: " + id);
                }
            }
        }

    }

    public static final Collection<WorldObject> decode(final ByteBuffer buffer) {
        final ArrayList<WorldObject> collection = new ArrayList<>();
        int objectId = -1;
        int incr;
        buffer.setPosition(0);
        while ((incr = buffer.readHugeSmart()) != 0) {
            objectId += incr;
            int location = 0;
            int incr2;
            while ((incr2 = buffer.readUnsignedSmart()) != 0) {
                location += incr2 - 1;
                final int localX = (location >> 6 & 0x3f);
                final int localY = (location & 0x3f);
                final int plane = location >> 12;
                final int objectData = buffer.readUnsignedByte();
                final int type = objectData >> 2;
                final int rotation = objectData & 0x3;
                collection.add(new WorldObject(objectId, type, rotation, localX, localY, plane));
            }
        }
        return collection;
    }

}