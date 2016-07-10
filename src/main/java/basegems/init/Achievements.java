package basegems.init;

import basegems.BaseGems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

/** initializer for achievements */
public abstract class Achievements {

	public static AchievementPage page;

	public static Achievement template_maker; // make blend

	private static boolean initDone = false;

	/**
	 * 
	 */
	public static void init() {
		if(initDone) return;

		page = new AchievementPage(BaseGems.NAME);
		AchievementPage.registerAchievementPage(page);

		template_maker = makeAchievement("template_maker", cyano.basemetals.init.Achievements.metallurgy, 0, 0, Items.template_ingot);

		initDone = true;
	}

	private static Achievement makeAchievement(String baseName, Achievement requirement, int x, int y, Item icon) {
		return makeAchievement(baseName, requirement, x, y, new ItemStack(icon));
	}

	private static Achievement makeAchievement(String baseName, Achievement requirement, int x, int y, Block icon) {
		return makeAchievement(baseName, requirement, x, y, new ItemStack(icon));
	}

	private static Achievement makeAchievement(String baseName, Achievement requirement, int x, int y, ItemStack icon) {
		Achievement a = new Achievement(baseName, baseName, x, y, icon, requirement);
		a.registerStat();
		page.getAchievements().add(a);
		return a;
	}
}
