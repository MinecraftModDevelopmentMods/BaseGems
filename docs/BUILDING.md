# Building Base Gems for Minecraft 1.14.4

Use Java 17 to run Gradle 9.6.1, Java 25 for ForgeGradle's Mavenizer, and
Java 8 for compilation and Minecraft. Put Java 17 on your command path and
make the other JDKs available to Gradle's toolchain detection. Python 3
stages the pinned OreSpawn, Base Metals and Modern Metals jars, checks their SHA-256
and generates the resource pack and data pack.

Use Python 3.10 or later. If Eclipse cannot find it on its PATH, create an
ignored `local-build.properties` in the project root:

```properties
pythonExecutable=C:/path/to/python.exe
```

That setting applies to both Gradle refreshes in Eclipse and terminal builds.
An explicit `-PpythonExecutable=...` still takes precedence.

From the inner Git checkout on Windows:

```powershell
python gradle/stage-legacy-dependencies.py .gradle/legacy-dependencies
.\gradlew.bat check build verify114ReleaseArtifacts write114ReleaseChecksums prepareEclipse
```

On Linux or macOS, use `python3` and `./gradlew`. The game jar is
`build/libs/BaseGems-2.5.0.114041.jar`. The build uses Forge 28.2.26 and
MCP snapshot 20190719-1.14.3. Model, recipe, tag, language and advancement data
is generated during resource processing.

`prepareEclipse` generates the Eclipse project and Java 8 launches with
processed resources and the pinned runtime dependencies. Use the same
Gradle cache for Eclipse and command-line builds.
The optional metal integrations use the published Base Metals 3.0.2.114041 and
Modern Metals 3.0.0.114041 releases. Run `prepareEclipse` again after changing
sources or resources.

Use a fresh test world when checking the OreSpawn defaults. In Low
Fantasy, try mining and trading a gem, then check that storage blocks are
still craftable and equipment recipes have gone. See [ore generation](ORE_GENERATION.md)
for the placement rules and config overrides.

`prepareEclipse` generates four isolated clients: `runClient.launch`,
`runClientWithBaseMetals.launch`, `runClientWithModernMetals.launch` and
`runClientWithBaseAndModern.launch`. Each has its own run folder. Replaced
companion jars are retained under `.gradle/retired-compatibility-jars`; saves,
config and unrelated mods are preserved.

To test a different local Modern Metals build, supply its mapped development
jar explicitly. Release builds always use the published dependency:

```powershell
.\gradlew.bat prepareEclipse "-PmodernMetalsDevelopmentJar=C:/path/ModernMetals-3.0.0.114041-deobf.jar"
```

`runProbeServer` and `runProbeClient` are explicit automated checks. Use a
new `-PprobeDirectory=run/verification/<case>` for each scenario and keep
their receipts. Ordinary launches exclude these test mods.
See [manual testing](MANUAL_TESTING.md) and [porting notes](PORTING_114.md).
