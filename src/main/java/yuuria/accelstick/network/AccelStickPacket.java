package yuuria.accelstick.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import yuuria.accelstick.items.sticks.BaseAccelSticks;

import java.util.function.Supplier;

public class AccelStickPacket {
    private final int speed;
    public AccelStickPacket(int speed)
    {
        this.speed = speed;
    }

    public void encode(FriendlyByteBuf buffer)
    {
        buffer.writeInt(speed);
    }

    public AccelStickPacket(FriendlyByteBuf buffer)
    {
        this(buffer.readInt());
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        ServerPlayer player = context.get().getSender();
        if (player == null) return;

        ItemStack itemStack = player.getMainHandItem();
        BaseAccelSticks item = (BaseAccelSticks) itemStack.getItem();
        int speedToSet = Math.min(Math.max(speed, 1), item.getMaxSpeed());
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("current_speed", speedToSet);

        itemStack.setTag(compoundTag);
    }
}
