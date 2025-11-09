package yuuria.accelsticks;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;
import yuuria.accelsticks.items.ItemsRegister;
import yuuria.accelsticks.items.StickDataComponent;
import yuuria.accelsticks.network.AccelSticksNetwork;

@Mod(AccelSticks.MODID)
public class AccelSticks {
    public static final String MODID = "stickaccel";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AccelSticks(IEventBus modEventBus, ModContainer modContainer) {
        StickDataComponent.register(modEventBus);
        ItemsRegister.ITEMS.register(modEventBus);
        modEventBus.addListener(AccelSticks::payloadRegister);
    }

    public static void payloadRegister(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(MODID);
        registrar.playToServer(
                AccelSticksNetwork.TYPE,
                AccelSticksNetwork.STREAM_CODEC,
                AccelSticksNetwork::serverHandler
        );
    }
}
