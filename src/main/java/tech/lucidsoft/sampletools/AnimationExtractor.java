package tech.lucidsoft.sampletools;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.definitions.SequenceDefinition;
import tech.lucidsoft.cache.definitions.managers.SequenceManager;
import tech.lucidsoft.cache.filesystem.Archive;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.Group;
import tech.lucidsoft.cache.util.DefUtil;
import java.io.File;

public class AnimationExtractor {

    private Cache cache;
    private SequenceManager sequenceManager;
    private static String cachePath = "./data/cache208/";

    private final int idToDump = -1;

    public static void main(String[] args) {
        new AnimationExtractor();
    }

    public AnimationExtractor() {
        cache = Cache.openCache(cachePath);

        loadSequenceDefinitions(cache);

        dumpAnimation(idToDump);
        dumpAnimation(9811);
    }

    public void loadSequenceDefinitions(Cache cache) {
        sequenceManager = new SequenceManager(cache);
        sequenceManager.setVerbose(true);
        sequenceManager.load();
    }

    //Need to extract: animation config + related base + frames + sound
    public void dumpAnimation(int id) {

        final Archive baseArchive = cache.getArchive(ArchiveType.BASES);
        final Archive skeletonArchive = cache.getArchive(ArchiveType.SKELETONS);
        final Archive soundArchive = cache.getArchive(ArchiveType.SYNTHS);

        final String dumpBaseOutput = "./data/dumps/208/animations/" + id;

        if (sequenceManager == null) {
            System.out.println("Sequence manager isn't initialized!");
            return;
        }

        SequenceDefinition def = sequenceManager.getSequenceDef(id);
        int ids[] = def.getFrameIds();

        if (ids == null || ids.length == 0) {
            System.out.println("Animation " + id + " doesn't contain any frames.");
            return;
        }

        // Exports the frames
        int frameGroup = ids[0] >> 16;
        Group skeleGroup = skeletonArchive.findGroupByID(frameGroup);
        int baseId = -1;
        for (int i = 0; i < ids.length; i++) {
            int fileId = ids[i] & 0xFFFF;
            byte[] frameBuffer = skeleGroup.findFileByID(fileId).getData().getBuffer();
            baseId = (frameBuffer[0] & 255) << 8 | frameBuffer[1] & 255;;
            DefUtil.dumpData(frameBuffer,  dumpBaseOutput + "/frames/", fileId + "");
        }


        // Exports the sounds
        if (def.getFrameSounds() != null) {
            for (int soundId : def.getFrameSounds()) {
                if(soundId != 0) {
                    int soundEffectId = soundId >> 8;
                    byte[] soundBuffer = soundArchive.findGroupByID(soundEffectId).getFiles()[0].getData().getBuffer();
                    DefUtil.dumpData(soundBuffer, dumpBaseOutput + "/sounds/", soundEffectId + "");
                }
            }
        }

        // Exports the animation base
        if (baseId != -1) {
            byte[] baseBuffer = baseArchive.findGroupByID(baseId).getFiles()[0].getData().getBuffer();
            DefUtil.dumpData(baseBuffer, dumpBaseOutput + "/base/", baseId + "");
        }

        // Exports the animation definition
        sequenceManager.exportToJson(idToDump, new File(dumpBaseOutput + "/"));
    }

}