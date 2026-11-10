# Building Base Gems for Minecraft 1.12.2

Use Java 17 to run Gradle 9.6.1, Java 25 for ForgeGradle's Mavenizer, and
Java 8 for compilation and Minecraft. Put Java 17 on your command path and
make the other JDKs available to Gradle's toolchain detection. Python 3
stages the pinned mod dependencies and checks their SHA-256 hashes.

From the inner Git checkout on Windows:

```powershell
python gradle/stage-legacy-dependencies.py .gradle/legacy-dependencies
.\gradlew.bat check build verifyReleaseArtifacts writeReleaseChecksums prepareEclipse
```

On Linux or macOS, use `python3` and `./gradlew`. The game jar is
`build/libs/BaseGems-2.5.0.112021.jar`. Gradle builds the ore models from
the tracked textures during resource processing.

`prepareEclipse` generates the Eclipse project and Java 8 launches with
processed resources and the pinned runtime dependencies. Use the same
Gradle cache for Eclipse and command-line builds.

Use a fresh test world when checking the OreSpawn defaults. In Low
Fantasy, try mining and trading a gem, then check that storage blocks are
still craftable and equipment recipes have gone. See [ore generation](ORE_GENERATION.md)
for the placement rules and config overrides.
