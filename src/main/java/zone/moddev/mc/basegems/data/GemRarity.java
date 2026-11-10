package zone.moddev.mc.basegems.data;

/** Reward tiers reflect the default ore distribution. */
public enum GemRarity {
    COMMON(1, 2, 1, 12, 3),
    RARE(2, 4, 3, 4, 2),
    BLACK_DIAMOND(4, 6, 4, 3, 2);

    public final int minXp;
    public final int maxXp;
    public final int tradeLevel;
    public final int gemsPerTrade;
    public final int emeraldsToBuy;

    GemRarity(int minXp, int maxXp, int tradeLevel, int gemsPerTrade, int emeraldsToBuy) {
        this.minXp = minXp;
        this.maxXp = maxXp;
        this.tradeLevel = tradeLevel;
        this.gemsPerTrade = gemsPerTrade;
        this.emeraldsToBuy = emeraldsToBuy;
    }

    public static GemRarity of(String gem) {
        switch (gem) {
            case "blackdiamond":
                return BLACK_DIAMOND;
            case "alexandrite":
            case "ametrine":
            case "bluetopaz":
            case "goldenberyl":
            case "heliodor":
            case "indicolite":
            case "iolite":
            case "moldavite":
            case "morganite":
            case "ruby":
            case "sapphire":
            case "spinel":
            case "tanzanite":
            case "violetsapphire":
                return RARE;
            default:
                return COMMON;
        }
    }
}
