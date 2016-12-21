package mmd.basegems.init;

import cyano.basemetals.util.Config.Options;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.common.Loader;

/** initializer for achievements */
public class Achievements extends cyano.basemetals.init.Achievements {

	public static Achievement agate_maker; // make blend

	private static boolean initDone = false;

	/**
	 *
	 */
	public static void init() {
		if (initDone) {
			return;
		}

		if (Options.ENABLE_ACHIEVEMENTS) {
			AchievementPage page = new AchievementPage(Loader.instance().activeModContainer().getModId());
			AchievementPage.registerAchievementPage(page);

			agate_maker = makeAchievement("agate_maker", cyano.basemetals.init.Achievements.metallurgy, 0, 0, Items.agate_ingot, page);
		}

		initDone = true;
	}
}
