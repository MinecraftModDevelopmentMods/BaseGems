# Releasing Base Gems for Minecraft 1.21.1

Releases follow the MMD mod template. Version numbers are
`major.minor.patch.<Minecraft version without dots><loader>`, where the
loader digit is 2 for NeoForge: Base Gems 4.0.0 for 1.21.1 is `4.0.0.121012`.

1. Update `mod_version` in `gradle.properties`, `update.json` and
   `CHANGELOG.txt`. Check that `./gradlew clean check build` passes and test
   the jar in a client and on a server.
2. Merge to `master-1.21.1-neo`. The **Build and Test** workflow builds every
   push and pull request.
3. Push a tag named after the version, such as `4.0.0.121012`. The
   **Upload Release Artifacts** workflow builds that tag with the tag as the
   version, then publishes the jars to MMD Maven, a GitHub release and
   CurseForge (project `curseforge_id` in `gradle.properties`).

Maven and CurseForge publication need the `MAVEN_UPLOAD_URL`,
`MAVEN_UPLOAD_USERNAME`, `MAVEN_UPLOAD_PASSWORD` and `CURSEFORGE_TOKEN`
secrets; each step is skipped when its secret is missing. MMDLib and OreSpawn
are required dependencies, so their 1.21.1 releases must already be public.
