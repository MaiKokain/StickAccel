package yuuria.accelstick.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import yuuria.accelstick.AccelStick;
import yuuria.accelstick.gui.AccelStickScreen;

public class AccelStickPacketHandler {
    private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
            new ResourceLocation(AccelStick.MODID,"main"))
            .serverAcceptedVersions(s -> true)
            .clientAcceptedVersions(s -> true)
            .networkProtocolVersion(() -> "1")
            .simpleChannel();

    public static void register()
    {
        int id = 0;
        INSTANCE.messageBuilder(AccelStickPacket.class, id++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(AccelStickPacket::encode)
                .decoder(AccelStickPacket::new)
                .consumerMainThread(AccelStickPacket::handle)
                .add();
    }

    public static void sendToServer(int speed)
    {
        INSTANCE.sendToServer(new AccelStickPacket(speed));
    }
}
