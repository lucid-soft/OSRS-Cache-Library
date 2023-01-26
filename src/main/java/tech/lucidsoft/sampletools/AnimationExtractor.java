package tech.lucidsoft.sampletools;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.definitions.SequenceDefinition;
import tech.lucidsoft.cache.definitions.managers.SequenceManager;
import tech.lucidsoft.cache.filesystem.Archive;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.Group;
import tech.lucidsoft.cache.util.DefUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AnimationExtractor {

    private Cache cache;
    private SequenceManager sequenceManager;
    private static String cachePath = "./data/zencache/";

    private final int idToDump = -1;

    public static void main(String[] args) {
        new AnimationExtractor();
    }

    public AnimationExtractor() {
        cache = Cache.openCache(cachePath);

        loadSequenceDefinitions(cache);

        //checkForItem(27644);
        //dumpGroup(3293);
        dumpAnimation(15094);
        dumpAnimation(15093);
        dumpAnimation(15084);
        dumpAnimation(15018);
        dumpAnimation(15023);
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
        dumpGroup(frameGroup, def.getId());

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
        byte[] frameBuffer = skeletonArchive.findGroupByID(frameGroup).getFiles()[0].getData().getBuffer();
        int baseId = (frameBuffer[0] & 255) << 8 | frameBuffer[1] & 255;

        if (baseId != -1) {
            byte[] baseBuffer = baseArchive.findGroupByID(baseId).getFiles()[0].getData().getBuffer();
            DefUtil.dumpData(baseBuffer, dumpBaseOutput + "/base/", baseId + "");
        }

        // Exports the animation definition
        sequenceManager.exportToToml(def, new File(dumpBaseOutput + "/"));
        //sequenceManager.exportToJson(def, new File(dumpBaseOutput + "/"));
    }

    private void dumpGroup(int id, int animid) {
        final String dumpBaseOutput = "./data/dumps/2102/animations/" + animid + "/frames/";
        final Archive skeletonArchive = cache.getArchive(ArchiveType.SKELETONS);
        final Group skeletonGroup = skeletonArchive.findGroupByID(id);
        final tech.lucidsoft.cache.filesystem.File[] skeletonFiles = skeletonGroup.getFiles();

        List<Integer> used = new ArrayList<>();
        for (int i = 0; i < skeletonGroup.getHighestFileId(); i++) {
            if (skeletonGroup.findFileByID(i) == null) {
                continue;
            }
            used.add(i);
            byte[] fileBuffer = skeletonGroup.findFileByID(i).getData().getBuffer();
            DefUtil.dumpData(fileBuffer, dumpBaseOutput, i + "");
        }
        System.out.print("final int[] group" + id + "Ids = { ");
        for(int i = 0; i < used.size(); i++) {
            if(i < used.size() - 1) {
                System.out.print(used.get(i) + ", ");
            } else {
                System.out.print(used.get(i));
            }
        }
        System.out.println(" };");

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