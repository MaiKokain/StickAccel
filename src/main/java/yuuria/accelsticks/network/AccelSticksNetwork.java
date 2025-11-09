package yuuria.accelsticks.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import yuuria.accelsticks.AccelSticks;
import yuuria.accelsticks.items.StickDataComponent;
import yuuria.accelsticks.items.sticks.BaseAccelSticks;

public record AccelSticksNetwork(Integer speed) implements CustomPacketPayload {
    public static final AccelSticksNetwork.Type<AccelSticksNetwork> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(AccelSticks.MODID, "sticks_speed_data"));

    public static final StreamCodec<ByteBuf, AccelSticksNetwork> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            AccelSticksNetwork::speed,
            AccelSticksNetwork::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void serverHandler(final AccelSticksNetwork data, final IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                ItemStack stack = context.player().getMainHandItem();
                BaseAccelSticks baseAccelSticks = (BaseAccelSticks) stack.getItem();
                int speedToSet = Math.min(Math.max(data.speed(), 1), baseAccelSticks.getMaxSpeed());
                stack.set(StickDataComponent.SPEED.get(), speedToSet);
            }
        });
    }
}
