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
    private static String cachePath = "./data/cache2102/";

    private final int idToDump = -1;

    public static void main(String[] args) {
        new AnimationExtractor();
    }

    public AnimationExtractor() {
        cache = Cache.openCache(cachePath);

        loadSequenceDefinitions(cache);

        //checkForItem(27644);
        dumpGroup(3293);
        /*dumpAnimation(9846); //?
        dumpAnimation(9847); //standing idle
        dumpAnimation(9848); //throwing card
        dumpAnimation(9849); //walking
        dumpAnimation(9850); //running
        dumpAnimation(9851); //turn counter-clockwise
        dumpAnimation(9852); //turn 180
        dumpAnimation(9853); //block stance
        dumpAnimation(9854); //?*/
        //dumpAnimation(9960);
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

        final String dumpBaseOutput = "./data/dumps/2102/animations/" + id;

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
        System.out.println("Frame group = " + frameGroup);
        Group skeleGroup = skeletonArchive.findGroupByID(frameGroup);
        int baseId = -1;
        for (int i = 0; i < ids.length; i++) {
            int fileId = ids[i] & 0xFFFF;
            byte[] frameBuffer = skeleGroup.findFileByID(fileId).getData().getBuffer();
            baseId = (frameBuffer[0] & 255) << 8 | frameBuffer[1] & 255;
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
        sequenceManager.exportToJson(def, new File(dumpBaseOutput + "/"));
    }

    private void dumpGroup(int id) {
        final String dumpBaseOutput = "./data/dumps/2102/animations/";
        final Archive skeletonArchive = cache.getArchive(ArchiveType.SKELETONS);
        final Group skeletonGroup = skeletonArchive.findGroupByID(id);
        final tech.lucidsoft.cache.filesystem.File[] skeletonFiles = skeletonGroup.getFiles();

        int i = 0;
        for (tech.lucidsoft.cache.filesystem.File f : skeletonFiles) {
            if (f == null) {
                System.out.println("Group " + id + ", file " + i + " is null");
                continue;
            }
            byte[] fileBuffer = f.getData().getBuffer();
            DefUtil.dumpData(fileBuffer, dumpBaseOutput + "/groups/" + id + "/", f.getID() + "");
            i++;
        }
    }

    public void checkForItem(int id) {
        for (SequenceDefinition def : sequenceManager.getDefinitions().values()) {
            if (def == null)
                continue;

            if (def.getLeftHandItem() >= id) {
                System.out.println("Animation " + def.getId() + " uses " + def.getLeftHandItem() + " as left-hand item.");
            }

            if (def.getRightHandItem() >= id) {
                System.out.println("Animation " + def.getId() + " uses " + def.getRightHandItem() + " as right-hand item.");
            }
        }
    }

}