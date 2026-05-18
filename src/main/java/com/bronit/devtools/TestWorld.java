package com.bronit.devtools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class TestWorld {

    private static final Logger LOGGER = DevTools.getLogger(TestWorld.class.getSimpleName());

    public static final int TEST_WORLD_BUTTON_ID = DevConfig.testWorldCategory.testWorldButtonPosition == 2 ? 14 : DevConfig.testWorldCategory.testWorldButtonId;

    @SubscribeEvent
    public static void initGuiEvent(GuiScreenEvent.InitGuiEvent.Post event) {
        if (!(event.getGui() instanceof GuiMainMenu)) return;

        int newButtonWidth = 98;
        String testButtonLabel = "Create Test World";

        if (DevConfig.testWorldCategory.testWorldButtonPosition == 0) {
            GuiButton singleplayerButton = null;
            for (GuiButton button : event.getButtonList()) {
                if (button.id == 1) {
                    singleplayerButton = button;
                    break;
                }
            }

            if (singleplayerButton == null) {
                LOGGER.error("Singleplayer Button could not be found by default id 1");
                return;
            }
            singleplayerButton.width = newButtonWidth;

            GuiButton testWorldButton = new GuiButton(
                    TEST_WORLD_BUTTON_ID,
                    singleplayerButton.x + 102,
                    singleplayerButton.y,
                    newButtonWidth,
                    singleplayerButton.height,
                    testButtonLabel);
            event.getButtonList().add(testWorldButton);
        } else if (DevConfig.testWorldCategory.testWorldButtonPosition == 1) {
            GuiButton multiplayerButton = null;
            for (GuiButton button : event.getButtonList()) {
                if (button.id == 2) {
                    multiplayerButton = button;
                    break;
                }
            }

            if (multiplayerButton == null) {
                LOGGER.error("Multiplayer Button could not be found by default id 2");
                return;
            }
            multiplayerButton.width = newButtonWidth;

            GuiButton testWorldButton = new GuiButton(
                    TEST_WORLD_BUTTON_ID,
                    multiplayerButton.x + 102,
                    multiplayerButton.y,
                    newButtonWidth,
                    multiplayerButton.height,
                    testButtonLabel);
            event.getButtonList().add(testWorldButton);
        } else if (DevConfig.testWorldCategory.testWorldButtonPosition == 2) {
            boolean flag = true;
            for (GuiButton button : event.getButtonList()) {
                if (button.id == 14) {
                    button.displayString = testButtonLabel;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                LOGGER.error("Realms Button could not be found by default id 14");
            }
        }
    }

    @SubscribeEvent
    public static void onRealmsButtonPressed(GuiScreenEvent.ActionPerformedEvent.Pre event) {
        if (event.getGui() instanceof GuiMainMenu && event.getButton().id == TEST_WORLD_BUTTON_ID) {
            event.setCanceled(true);
            createAndLaunchTestWorld();
        }
    }

    private static void createAndLaunchTestWorld() {
        Minecraft mc = Minecraft.getMinecraft();
        ISaveFormat saveLoader = mc.getSaveLoader();

        String baseName = DevConfig.testWorldCategory.testWorldName;

        String worldName = baseName;
        String saveName = baseName.replaceAll("[\\\\/:*?\"<>|]", "_");

        int counter = 1;
        while (saveLoader.getWorldInfo(saveName) != null) {
            worldName = baseName + " (" + counter + ")";
            saveName = baseName.replaceAll("[\\\\/:*?\"<>|]", "_") + " (" + counter + ")";
            counter++;
        }

        WorldInfo worldInfo = getWorldInfo(worldName);
        WorldSettings worldSettings = new WorldSettings(worldInfo).enableCommands();

        ISaveHandler saveHandler = saveLoader.getSaveLoader(saveName, false);
        saveHandler.saveWorldInfo(worldInfo);
        saveLoader.flushCache();

        mc.launchIntegratedServer(saveName, worldName, worldSettings);
    }

    private static WorldInfo getWorldInfo(String worldName) {
        NBTTagCompound worldData = new NBTTagCompound();
        worldData.setLong("RandomSeed", (new Random()).nextLong());
        worldData.setString("generatorName", "flat");
        worldData.setString("generatorOptions", FlatGeneratorInfo.getDefaultFlatGenerator().toString());
        worldData.setInteger("generatorVersion", 0);
        worldData.setInteger("GameType", GameType.CREATIVE.getID());
        worldData.setBoolean("MapFeatures", false);
        worldData.setBoolean("hardcore", false);
        worldData.setBoolean("allowCommands", true);
        worldData.setBoolean("initialized", true);
        worldData.setString("LevelName", worldName);

        worldData.setLong("Time", 6000L);
        worldData.setLong("DayTime", 6000L);
        worldData.setInteger("SpawnX", 0);
        worldData.setInteger("SpawnY", 4);
        worldData.setInteger("SpawnZ", 0);
        worldData.setInteger("clearWeatherTime", Integer.MAX_VALUE);
        worldData.setInteger("rainTime", 0);
        worldData.setBoolean("raining", false);
        worldData.setInteger("thunderTime", 0);
        worldData.setBoolean("thundering", false);

        NBTTagCompound gameRules = new NBTTagCompound();
        gameRules.setString("doDaylightCycle", "false");
        gameRules.setString("doMobSpawning", "false");
        gameRules.setString("doWeatherCycle", "false");
        worldData.setTag("GameRules", gameRules);

        return new WorldInfo(worldData);
    }

}
