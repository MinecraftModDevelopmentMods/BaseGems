package com.mcmoddev.basegems.init;
import com.mcmoddev.basegems.util.Config.Options;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.common.Loader;

/** initializer for achievements */
public class Achievements extends com.mcmoddev.lib.init.Achievements {

	/** make Agate Blend */
	public static Achievement agate_maker;

	private static boolean initDone = false;

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		if (com.mcmoddev.basemetals.util.Config.Options.enableAchievements) {
			AchievementPage page = new AchievementPage(Loader.instance().activeModContainer().getModId());
			AchievementPage.registerAchievementPage(page);

			if (Options.ENABLE_AGATE) {
				agate_maker = makeAchievement("agate_maker", com.mcmoddev.basemetals.init.Achievements.metallurgy, 0, 0, Materials.agate.ingot, page);
			}
		}

		initDone = true;
	}
}
