package com.mcmoddev.basegems.init;

/** initializer for achievements */
public class Achievements extends com.mcmoddev.lib.init.Achievements {

	private static boolean initDone = false;

	private Achievements() {
		throw new IllegalAccessError("Not a instantiable class");
	}

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		// No Achievements yet
/*
		if (com.mcmoddev.basemetals.util.Config.Options.enableAchievements) {
			AchievementPage page = new AchievementPage(Loader.instance().activeModContainer().getModId());
			AchievementPage.registerAchievementPage(page);
		}
*/

		initDone = true;
	}
}
