package com.bronit.devtools;

import net.minecraftforge.common.config.Config;

@Config(modid = Tags.MOD_ID)
public class DevConfig {



    @Config.Name("test_world")
    public static final TestWorldCategory testWorldCategory = new TestWorldCategory();

    public static class TestWorldCategory {
        @Config.Comment("If true, a 'Create Test World' button will appear in the main menu")
        public boolean enableTestWorldButton = true;

        @Config.Comment("If true, the mod will only work in a deobfuscated environment (inside an IDE / Gradle workspace).")
        public boolean devEnvironmentOnly = true;

        @Config.Comment({
                "The position of the 'Create Test World' button in the main menu:",
                "0 - Next to the 'Singleplayer' button (splits the width in half)",
                "1 - Next to the 'Multiplayer' button (splits the width in half)",
                "2 - Replaces the vanilla Realms button"
        })
        @Config.RangeInt(min = 0, max = 2)
        public byte testWorldButtonPosition = 2;

        @Config.Comment("The ID for the created button. Used only for positions 0 and 1. Do not use standard vanilla IDs (0, 1, 2, 4, 5, 6, 14).")
        public int testWorldButtonId = 7;

        @Config.Comment("The base name for the generated test world. If a folder with this name already exists, a counter like (1), (2), etc., will be automatically appended.")
        public String testWorldName = "Test World";
    }

}
