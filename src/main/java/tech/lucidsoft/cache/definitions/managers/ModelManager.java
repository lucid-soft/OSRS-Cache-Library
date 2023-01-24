package tech.lucidsoft.cache.definitions.managers;

import tech.lucidsoft.cache.ArchiveType;
import tech.lucidsoft.cache.definitions.ItemDefinition;
import tech.lucidsoft.cache.definitions.managers.model.Model;
import tech.lucidsoft.cache.filesystem.Archive;
import tech.lucidsoft.cache.filesystem.Cache;
import tech.lucidsoft.cache.filesystem.Group;
import tech.lucidsoft.cache.definitions.NpcDefinition;
import tech.lucidsoft.cache.definitions.ObjectDefinition;
import tech.lucidsoft.cache.util.DefUtil;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ModelManager {

    private final Cache cache;
    private Model[] models;
    private static boolean verbose = false;

    public ModelManager(final Cache cache) {
        this.cache = cache;
    }

    public void load() {
        if(isVerbose()) {
            System.out.println("Loading models...");
        }
        Archive modelArchive = cache.getArchive(ArchiveType.MODELS);
        models = new Model[modelArchive.getHighestGroupId()];
        for(int i = 0; i < modelArchive.getHighestGroupId(); i++) {
            Group model = modelArchive.findGroupByID(i);
            if(model != null) {
                models[i] = new Model(i, model.findFileByID(0).getData().getBuffer());
            }
        }
    }

    public void dumpObjectModels(ObjectDefinition def, String directory) {
        if(def == null) {
            return;
        }
        if(def.getModels() != null && def.getModels().length > 0) {
            for(int i = 0; i < def.getModels().length; i++) {
                    dumpModel(def.getModels()[i], new File(directory + def.getId() + "_" + DefUtil.cleanseName(def.getName()) + System.getProperty("file.separator")), def.getModels()[i] + "");
            }
        }
    }

    public void dumpNpcModels(NpcDefinition def, String directory) {
        if (def == null) {
            return;
        }
        if (def.getModels() != null && def.getModels().length > 0) {
            for(int i = 0; i < def.getModels().length; i++) {
                dumpModel(def.getModels()[i], new File(directory + def.getId() + "_" + DefUtil.cleanseName(def.getName()) + System.getProperty("file.separator")), def.getModels()[i] + "");
            }
        }
        if (def.getChatModels() != null && def.getChatModels().length > 0) {
            for(int i = 0; i < def.getChatModels().length; i++) {
                dumpModel(def.getChatModels()[i], new File(directory + def.getId() + "_" + DefUtil.cleanseName(def.getName()) + System.getProperty("file.separator")), def.getChatModels()[i] + "-chat");
            }
        }
    }

    public void dumpItemModels(ItemManager manager, ItemDefinition def, String directory) {
        if (def == null) {
            return;
        }
        String cleansedName = DefUtil.cleanseItemName(def.getName(), def.getName().equals("null") && def.getNotedId() != -1 ? manager.getItemDef(def.getNotedId()).getName() : def.getName(),
                def.getName().equals("null") && def.getPlaceholderId() != -1 ? manager.getItemDef(def.getPlaceholderId()).getName() : "null");
        File fullDirectory = new File(directory + def.getId() + "_" + cleansedName + System.getProperty("file.separator"));
        if (def.getInventoryModelId() > 0) {
            dumpModel(def.getInventoryModelId(), fullDirectory, def.getInventoryModelId() + "-inventory");
        }
        if (def.getPrimaryMaleModel() > 0) {
            dumpModel(def.getPrimaryMaleModel(), fullDirectory, def.getPrimaryMaleModel() + "-primarymale");
        }
        if (def.getPrimaryFemaleModel() > 0) {
            dumpModel(def.getPrimaryFemaleModel(), fullDirectory, def.getPrimaryFemaleModel() + "-primaryfemale");
        }
        if (def.getSecondaryMaleModel() > 0) {
            dumpModel(def.getSecondaryMaleModel(), fullDirectory, def.getSecondaryMaleModel() + "-secondarymale");
        }
        if (def.getSecondaryFemaleModel() > 0) {
            dumpModel(def.getSecondaryFemaleModel(), fullDirectory, def.getSecondaryFemaleModel() + "-secondaryfemale");
        }
        if (def.getTertiaryMaleModel() > 0) {
            dumpModel(def.getTertiaryMaleModel(), fullDirectory, def.getTertiaryMaleModel() + "-tertiarymale");
        }
        if (def.getTertiaryFemaleModel() > 0) {
            dumpModel(def.getTertiaryFemaleModel(), fullDirectory, def.getTertiaryFemaleModel() + "-tertiaryfemale");
        }
        if (def.getPrimaryMaleHeadModelId() > 0) {
            dumpModel(def.getPrimaryMaleHeadModelId(), fullDirectory, def.getPrimaryMaleHeadModelId() + "-primarymalehead");
        }
        if (def.getPrimaryMaleHeadModelId() > 0) {
            dumpModel(def.getPrimaryMaleHeadModelId(), fullDirectory, def.getPrimaryMaleHeadModelId() + "-primarymalehead");
        }
        if (def.getPrimaryFemaleHeadModelId() > 0) {
            dumpModel(def.getPrimaryFemaleHeadModelId(), fullDirectory, def.getPrimaryFemaleHeadModelId() + "-primaryfemalehead");
        }
        if (def.getSecondaryMaleHeadModelId() > 0) {
            dumpModel(def.getSecondaryMaleHeadModelId(), fullDirectory, def.getSecondaryMaleHeadModelId() + "-secondarymalehead");
        }
        if (def.getSecondaryFemaleHeadModelId() > 0) {
            dumpModel(def.getSecondaryFemaleHeadModelId(), fullDirectory, def.getSecondaryFemaleHeadModelId() + "-secondaryfemalehead");
        }
        if (def.getStackIds() != null && def.getStackIds().length > 0) {
            for(int i = 0; i < def.getStackIds().length; i++) {
                dumpModel(def.getStackIds()[i], fullDirectory, def.getStackIds()[i] + "-stack" + i);
            }
        }
    }

    public void dumpModel(int id, File directory, String filename) {
        java.io.File datFile = null;
        try {
            if(!directory.exists()) {
                directory.mkdirs();
            }
            if(models[id] == null) {
                //System.err.println("Error, no model for id: " + id);
                return;
            }
            datFile = new java.io.File(directory, filename + ".dat");
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(datFile));
            dos.write(models[id].getData());
        } catch (Exception e) {
            System.err.println("Error while writing file: " + (datFile != null ? datFile.getPath() : "null file"));
            e.printStackTrace();
            return;
        }
    }

    public static boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        ModelManager.verbose = verbose;
    }

    public Model[] getModels() {
        return this.models;
    }
}
