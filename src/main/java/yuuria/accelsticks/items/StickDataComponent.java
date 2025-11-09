package yuuria.accelsticks.items;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import yuuria.accelsticks.AccelSticks;

public class StickDataComponent {
    public static final DeferredRegister.DataComponents REGISTER = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, AccelSticks.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> SPEED = REGISTER.registerComponentType("accelstick_speed", b -> b.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    public static void register(IEventBus bus) {
        REGISTER.register(bus);
    }
}

