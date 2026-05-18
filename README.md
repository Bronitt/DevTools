# DevTools

A utility mod for **Minecraft 1.12.2** primarily focused on enhancing the development environment workflow for mod developers, though it is not strictly limited to it. While its features are tailored around a modder's routine, anyone-including modpack creators and players looking for a quick testing canvas-can freely and effectively use it.

Inspired by [WinDanesz/DevTools](https://github.com/WinDanesz/DevTools), this project was built from scratch to offer a cleaner, more flexible, and robust implementation that seamlessly integrates into the main menu.

---

## Current Features

### One-Click Test World Creation
The core feature of the mod (currently) is a highly customizable main menu button that instantly generates and launches a perfectly optimized testing world.

* **Instant Dev-Ready Environment:** The created world is pre-configured with the settings for debugging and testing:
    * **Game Mode:** Creative with Cheats/Commands enabled.
    * **World Type:** Superflat (`FLAT`) for a clean testing slate.
    * **Game Rules:** * `doDaylightCycle = false` (Locked at noon / time 6000)
        * `doMobSpawning = false` (No annoying mobs interfering with tests)
        * `doWeatherCycle = false` (Permanently clear weather)


### Configuration

The mod provides a highly flexible configuration file (`devtools.cfg`) with full in-game GUI config support:

```java
// If true, the mod will only work in a deobfuscated environment (inside an IDE / Gradle workspace).
public boolean devEnvironmentOnly = true;

// The position of the 'Create Test World' button in the main menu:
// 0 - Next to the 'Singleplayer' button (splits the width in half)
// 1 - Next to the 'Multiplayer' button (splits the width in half)
// 2 - Replaces the vanilla Realms button
public byte testWorldButtonPosition = 0;

// The ID for the created button. Used only for positions 0 and 1.
// Do not use standard vanilla IDs (0, 1, 2, 4, 5, 6, 14).
public int testWorldButtonId = 7;

// The base name for the generated test world.
// Automatically appends a counter if duplicates are found.
public String testWorldName = "Test World";
```

---

## Future Plans & Contributing

Although this project was created primarily to implement the Test World feature, DevTools is designed to be highly expandable. If there is a specific feature you need to speed up your routine or an environment tool you want to see: 
- Feel free to suggest your own functionality or report bugs by opening an Issue.
- Pull Requests are highly welcome if you want to contribute code or new dev utilities.

## Credits & Inspiration

- **WinDanesz:** Thanks to [WinDanesz](https://github.com/WinDanesz) for pushing me toward creating this mod (even though I had been thinking about it for quite a while). This mod serves as an alternative implementation; however, he is more than welcome to freely borrow from my source code, just as I borrowed a few ideas from his.
- **Modern Minecraft Versions:** Credit also goes to the official developer tools found in newer vanilla versions of Minecraft (where a similar quick dev test world feature is built-in). Part of the logic and workflow inspiration was drawn from studying how modern versions handle rapid dev-environment testing.