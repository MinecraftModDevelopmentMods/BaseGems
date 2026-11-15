# Building Base Gems for Minecraft 1.13.2

Use Java 17 to run Gradle 9.6.1, Java 25 for ForgeGradle's Mavenizer, and
Java 8 for compilation and Minecraft. Put Java 17 on your command path and
make the other JDKs available to Gradle's toolchain detection. Python 3
stages the pinned OreSpawn and optional Base Metals jars, checks their SHA-256
and generates the resource pack and data pack.

From the inner Git checkout on Windows:

```powershell
python gradle/stage-legacy-dependencies.py .gradle/legacy-dependencies
.\gradlew.bat check build verify113ReleaseArtifacts write113ReleaseChecksums prepareEclipse
```

On Linux or macOS, use `python3` and `./gradlew`. The game jar is
`build/libs/BaseGems-2.5.0.113021.jar`. The build uses Forge 25.0.223 and
MCP snapshot 20180921-1.13. Model, recipe, tag, language and advancement data
is generated during resource processing.

`prepareEclipse` generates the Eclipse project and Java 8 launches with
processed resources and the pinned runtime dependencies. Use the same
Gradle cache for Eclipse and command-line builds.
The optional Base Metals integration is pinned to its public MMD Maven release
3.0.3.113021. Run `prepareEclipse` again after changing sources or resources.

Use a fresh test world when checking the OreSpawn defaults. In Low
Fantasy, try mining and trading a gem, then check that storage blocks are
still craftable and equipment recipes have gone. See [ore generation](ORE_GENERATION.md)
for the placement rules and config overrides.
