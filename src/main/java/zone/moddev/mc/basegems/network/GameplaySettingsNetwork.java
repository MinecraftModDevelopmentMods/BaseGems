package zone.moddev.mc.basegems.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import org.apache.commons.lang3.tuple.Pair;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.FMLHandshakeHandler;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import zone.moddev.mc.basegems.config.GemConfig;
import zone.moddev.mc.basegems.data.Gem;

/** Keeps the client recipe book on the same effective policy as its server. */
public final class GameplaySettingsNetwork {
    private static SimpleChannel channel;
    private GameplaySettingsNetwork() {}

    public static void register() {
        channel = NetworkRegistry.newSimpleChannel(new ResourceLocation("basegems", "gameplay_settings"),
                () -> "1", "1"::equals, "1"::equals);
        channel.messageBuilder(Settings.class, 0)
                .encoder((message, buffer) -> { buffer.writeString(message.mode); buffer.writeLong(message.gems); })
                .decoder(buffer -> new Settings(buffer.readString(32), buffer.readLong()))
                .loginIndex(message -> message.loginIndex, (message, index) -> message.loginIndex = index)
                .buildLoginPacketList(local -> Collections.singletonList(
                        Pair.of("Base Gems gameplay settings", new Settings(GemConfig.activeMode(), enabledMask()))))
                .consumer(GameplaySettingsNetwork::receive).add();
        channel.messageBuilder(Acknowledgement.class, 1)
                .encoder((message, buffer) -> {}).decoder(buffer -> new Acknowledgement())
                .loginIndex(message -> message.loginIndex, (message, index) -> message.loginIndex = index)
                .consumer(FMLHandshakeHandler.indexFirst((handshake, message, context) ->
                        context.get().setPacketHandled(true))).add();
    }

    public static long enabledMask() {
        long enabled = 0;
        for (int index = 0; index < Gem.ALL.size(); index++) {
            if (GemConfig.enabled(Gem.ALL.get(index).name())) enabled |= 1L << index;
        }
        return enabled;
    }

    /** Bit order follows Gem.ALL; a changed order needs a new network protocol version. */
    public static String requiredChanges(String serverMode, long serverGems, String clientMode, long clientGems) {
        List<String> changes = new ArrayList<>();
        if (!serverMode.equals(clientMode)) changes.add("general.contentMode=" + serverMode);
        for (int index = 0; index < Gem.ALL.size(); index++) {
            long bit = 1L << index;
            if (((serverGems ^ clientGems) & bit) != 0) {
                changes.add("gems." + Gem.ALL.get(index).configKey() + "=" + ((serverGems & bit) != 0));
            }
        }
        return String.join(", ", changes);
    }

    private static void receive(Settings settings, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.setPacketHandled(true);
        if (context.getDirection() != NetworkDirection.LOGIN_TO_CLIENT) {
            throw new IllegalStateException("Base Gems settings received outside the client login handshake");
        }
        String changes = requiredChanges(settings.mode, settings.gems, GemConfig.activeMode(), enabledMask());
        if (!changes.isEmpty()) {
            context.getNetworkManager().closeChannel(new TranslationTextComponent("config.basegems.connection.mismatch", changes));
            return;
        }
        channel.reply(new Acknowledgement(), context);
    }

    private static final class Settings implements IntSupplier {
        private final String mode;
        private final long gems;
        private int loginIndex;
        private Settings(String mode, long gems) {
            if ((!"high_fantasy".equals(mode) && !"low_fantasy".equals(mode)) || (gems >>> Gem.ALL.size()) != 0) {
                throw new IllegalArgumentException("Unknown Base Gems gameplay settings");
            }
            this.mode = mode; this.gems = gems;
        }
        @Override public int getAsInt() { return loginIndex; }
    }
    private static final class Acknowledgement implements IntSupplier {
        private int loginIndex;
        @Override public int getAsInt() { return loginIndex; }
    }
}
