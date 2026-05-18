package com.bronit.devtools;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        clientSideOnly = true
)
@Mod.EventBusSubscriber(Side.CLIENT)
public class DevTools {

//    private static final Logger LOGGER = getLogger("");

    public static Logger getLogger(String name) {
        String loggerName = name.isEmpty() ? Tags.MOD_NAME : Tags.MOD_NAME + name;
        return LogManager.getLogger(loggerName);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (DevConfig.testWorldCategory.enableTestWorldButton &&
                (!DevConfig.testWorldCategory.devEnvironmentOnly || (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment")))
            MinecraftForge.EVENT_BUS.register(TestWorld.class);
    }

}
